DROP TABLE IF EXISTS installation;
DROP TABLE IF EXISTS access_token;
DROP TABLE IF EXISTS scheduled_job;
DROP TABLE IF EXISTS scheduled_job_transaction_log;
DROP TABLE IF EXISTS installation_version_info;

CREATE SEQUENCE scheduled_job_transaction_log_seq;
CREATE SEQUENCE installation_version_info_seq;
CREATE SEQUENCE scheduled_job_seq;

CREATE TABLE IF NOT EXISTS installation (
    dv_hub_id varchar NOT NULL PRIMARY KEY,
    name varchar,
    description varchar,
    latitude float,
    longitude float,
    hostname varchar,
    country varchar,
    continent varchar,
    launch_year integer,
    gdcc_member bool,
    doi_authority varchar,
    contact_email varchar
);

CREATE TABLE IF NOT EXISTS access_token (
  token_id varchar NOT NULL PRIMARY KEY,
  user_id integer
);

CREATE TABLE IF NOT EXISTS scheduled_job (
  job_id integer NOT NULL PRIMARY KEY,
  description varchar,
  job_name varchar,
  frequency integer
);

CREATE TABLE IF NOT EXISTS scheduled_job_transaction_log (
  transaction_id bigint NOT NULL PRIMARY KEY,
  job_id integer,
  execution_time timestamp,
  status integer,
  CONSTRAINT fk_scheduled_job FOREIGN KEY (job_id) REFERENCES scheduled_job(job_id)
);

CREATE TABLE IF NOT EXISTS installation_version_info (
  record_id integer NOT NULL PRIMARY KEY,
  dv_hub_id varchar,
  status varchar,
  version varchar,
  build varchar,
  capture_date timestamp,
  CONSTRAINT fk_installation FOREIGN KEY (dv_hub_id) REFERENCES installation(dv_hub_id)
);

INSERT INTO access_token (token_id, user_id) VALUES ('257d4485-173f-4a6d-913d-ee20c9d7bc06', 0);
INSERT INTO scheduled_job (job_id, description, job_name, frequency) VALUES (1, 'Installation importer', 'InstallationGitImporter', 3600000);
INSERT INTO scheduled_job (job_id, description, job_name, frequency) VALUES (2, 'Version Status check', 'VersionDVInstallationCheck', 120000);