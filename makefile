# Makefile for Dataverse Hub
PWD := $(shell pwd)

run:
	mvn -Pct clean package -DskipTests=true docker:build docker:run

build:
	mvn -Pct clean package -DskipTests=true docker:build docker:build

boot:
	mvn spring-boot:run

guides:
	docker run -it --rm -v $(PWD):/docs sphinxdoc/sphinx:7.3.6 bash -c "cd docs/sphinx-guides && pip3 install -r requirements.txt && make clean && make html"