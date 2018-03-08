# mod-resource-sharing

# GIT Submodule dependencies

Run

    git submodule update --init --recursive

After first checkout to pull dependent submodules


# Database setup

N.B. This project has a database dependency on postgres functionality. The test system requires
the following setup

CREATE USER folio WITH PASSWORD 'folio' SUPERUSER CREATEDB INHERIT LOGIN;

CREATE DATABASE foliotest;
GRANT ALL PRIVILEGES ON DATABASE foliotest to folio;

## Dockerized postgres

Get a postgres session in dockerized pgsql using

    docker exec -it your_pg_container_name psql -U postgres


# Testing

This module uses the idiomatic grails functional test suite -- run the tests after creating the pgsql database above with 

    grails test-app

The tests will create a tenant using the okapi tenant endpoint, and then issue a number of requests. Finally the tenant DELETE verb is used to destroy the tenant (And drop the schema and all it's tables)
