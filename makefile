make run:
	mvn -Pct clean package -DskipTests=true docker:build docker:run

make build:
	mvn -Pct clean package -DskipTests=true docker:build docker:build

make boot:
	mvn spring-boot:run