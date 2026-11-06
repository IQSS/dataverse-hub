# CLAUDE.md

This file guides Claude Code (claude.ai/code) when it works in this repository.

## What this project is

**Dataverse Hub (DV-Hub)** is a Spring Boot REST service from IQSS that collects metrics from Dataverse installations around the world in one place. It replaces the older, inconsistent metrics sources (dataverse.org/metrics, metrics.dataverse.org, the IQSS dashboards). Scheduled jobs pull data from GitHub and from each installation's public Dataverse API, store it in PostgreSQL, and serve it through a read-only public JSON API documented with OpenAPI/Swagger.

- Published guides: https://dataverse-hub.readthedocs.io/ (source in `docs/Sphinx-guides/`)
- Older background and design notes: `DESIGN.md`

## Tech stack

- Java 21, Spring Boot 3.4 (Web, Data JPA, Security, Cache), Maven (`./mvnw`)
- PostgreSQL 17, with the schema managed by **Flyway** (`spring.jpa.hibernate.ddl-auto=none`)
- Ehcache 3 through JCache (`src/main/resources/ehcache.xml`)
- springdoc-openapi: the spec is at `/openapi` and Swagger UI at `/swagger-ui/index.html`; `/` redirects to Swagger
- JUnit 5 + Spring Boot Test, with JaCoCo coverage reports in `target/site/jacoco`

## Common commands

```bash
make run        # mvn -Pct clean package -DskipTests docker:build docker:run  (Postgres + app in Docker)
make build      # build the Docker images only
make boot       # mvn spring-boot:run  (needs Postgres on localhost:5432)
make guides     # build the Sphinx docs in Docker

./mvnw test                                   # all tests (need a live Postgres, see below)
./mvnw test -Dtest=ScheduledJobServiceTests   # one test class
./mvnw test -Dtest=ScheduledJobServiceTests#methodName
```

Local database settings (`application.properties` and `docker-compose-dev.yaml`): `jdbc:postgresql://localhost:5432/dv_hub_pg`, user `admin`, password `admin`. The `ct` Maven profile runs Postgres from `docker-compose-dev.yaml` (`POSTGRES_VERSION=17`) and passes the app the `SPRING_DATASOURCE_*` environment variables, pointed at the `dv_hub_postgres` container. Postgres data is kept in `docker-dev-volumes/`, which git ignores.

## Architecture

Package root: `src/main/java/edu/harvard/iq/dataverse_hub/`

| Package | Role |
|---|---|
| `controller/api` | REST controllers: `InstallationController` (`/api/installations`, plus `/status`, `/country`, `/metrics`, `/metrics/monthly`) and `DevMetricsController` (`/api/dev`, `/api/dev/releases`). The OpenAPI annotations live in separate `annotations/*Docs` interfaces so the controllers stay uncluttered. Filter query parameters bind to the `request/*FilterParams` beans. |
| `controller/redirects` | `/` goes to Swagger. The legacy `/api/installation/**` path is **forwarded** (not redirected) to `/api/installations/**`. |
| `controller/scheduled` | The `@Scheduled` importers (see below). They sit under `controller` even though they are not web controllers. |
| `service` | Business logic. `InstallationService` holds the `@Cacheable` methods, `RestUtilService` makes the outbound HTTP calls, `ScheduledJobService` handles job gating, and `UserService` checks API tokens. |
| `repository` | Spring Data JPA repositories, named `*Repo` |
| `model` (+ `model/DTO`) | JPA entities and DTOs/projections |
| `security` | `SecurityConfig` and `TokenAuthFilter` |
| `cache` | Ehcache configuration and an event logger |
| `beans` | Shared response beans; `APIPayloadSamples` holds the example payloads used in the OpenAPI docs |

### Scheduled jobs (the core of the app)

Each importer follows the same pattern:

1. `@Scheduled(fixedRate = …)` calls `runTask()`, which catches and logs every exception.
2. `startTask(Boolean isDue)`: when `isDue` is `null`, it calls `scheduledJobService.isDue(JOB_NAME)`, where `JOB_NAME` is the class's simple name. Tests pass `true` to force a run.
3. `isDue` reads the job's row in the `scheduled_job` table (`frequency` in ms, `recurring`). If the row is missing, it creates one with a 24h default. It then compares that frequency with the newest entry in `scheduled_job_transaction_log`.
4. The import step writes a transaction log entry when it succeeds.

So the `@Scheduled` rate is only how often a job **checks** whether it is due. The real run frequency is set in the database. To change how often a job runs, update `scheduled_job` and leave the annotation alone.

| Job | What it does |
|---|---|
| `InstallationGitImporter` | Sets which installations are tracked. Data comes from `IQSS/dataverse-installations` `data/data.json` on GitHub. |
| `VersionDVInstallationCheck` | Calls each installation's `/api/info/version` and records the version and status. |
| `InstallationMetricsImporter` | Pulls `/api/info/metrics/{datasets,files,downloads,dataverses}` from each installation. |
| `InstallationMetricsMonthlyImporter` | The monthly version of the metrics import. |
| `DevMetricsImporter` | Dataverse development metrics, such as GitHub releases. |

### Security model

- Any `GET /api/**` request is public.
- Every other method on `/api/**` needs the `DVH_ADMIN` authority. `TokenAuthFilter` reads the `api_key` header and looks it up in the `access_token` table through `UserService`. A valid token gets `DVH_ADMIN`; any other request becomes `GUEST` with the `PUBLIC` authority.
- Sessions are stateless and CSRF is disabled.

### Caching

The caches are defined in `ehcache.xml`: `installationsStatus` (TTL 1h) and `installationsMetricsMonthly` (TTL 24h, keyed on the filter params' `hashCode()`). A new `@Cacheable` cache needs its own `<cache>` entry in `ehcache.xml`. Filter param classes used as cache keys must implement `equals`/`hashCode` correctly.

## Database migrations

- Flyway scripts are in `src/main/resources/db/migration/`, named `V<major>_<minor>_<patch>__description.sql` (for example `V1_0_1__installation_status.sql`).
- **Never edit a migration that has already been applied.** Add a new versioned file instead.
- Hibernate does not generate DDL, so entity changes need a matching migration.
- To work on a copy of production data, follow `docs/Sphinx-guides/source/developerGuide/databaseDump.md`.

## Testing notes

- Most tests are `@SpringBootTest` integration tests. They need a **running PostgreSQL** with the credentials above, and Flyway migrates it at startup. There is no H2 or Testcontainers setup.
- Importer tests (`scheduled/*Tests`) call `startTask(true)` with no mocks, so they make **real HTTP calls** to GitHub and to live Dataverse installations. They need network access, can be slow or flaky, and write to your local database.
- `src/test/resources/data.json` is a sample of the installations data.

## Gotchas

- `InstallationController.java` in the **repo root** is a scratch file. It is not under `src/` and is not compiled. Do not confuse it with the real controller in `controller/api/`.
- The `guides` target in `makefile` uses `docs/sphinx-guides` (lowercase), but the directory is `docs/Sphinx-guides`. The paths match on macOS's case-insensitive filesystem but not on Linux.
- `docker-compose-dev.yaml` uses `network:` (singular) under the service.
- `.idea/`, `.vscode/`, `target/`, `*.log` and `docker-dev-volumes/` are local-only and ignored by git.

## Conventions

- Inject dependencies with field `@Autowired`, which matches the existing code.
- Use SLF4J loggers. Importers log the start, success and failure of each job by `JOB_NAME`.
- Put new endpoint documentation in the `*ControllerDocs` interfaces and add sample payloads to `APIPayloadSamples`.
- Keep `GET` endpoints public and read-only. Any endpoint that changes data must be non-GET so the token check applies.
