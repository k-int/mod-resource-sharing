# mod-resource-sharing

# GIT Submodule dependencies

Run

    git submodule update --init --recursive

After first checkout to pull dependent submodules


# Database setup

N.B. This project has a database dependency on postgres functionality. The test system requires
the following setup

CREATE USER folio WITH PASSWORD 'folio' SUPERUSER CREATEDB INHERIT LOGIN;

CREATE DATABASE foliodev;
GRANT ALL PRIVILEGES ON DATABASE foliodev to folio;
CREATE DATABASE foliotest;
GRANT ALL PRIVILEGES ON DATABASE foliotest to folio;
CREATE DATABASE folio;
GRANT ALL PRIVILEGES ON DATABASE folio to folio;

## Dockerized postgres

Get a postgres session in dockerized pgsql using

    docker exec -it your_pg_container_name psql -U postgres


# Testing

This module uses the idiomatic grails functional test suite -- run the tests after creating the pgsql database above with 

    grails test-app

The tests will create a tenant using the okapi tenant endpoint, and then issue a number of requests. Finally the tenant DELETE verb is used to destroy the tenant (And drop the schema and all it's tables)

# Database Migrations

When changing the domain model, the migrations for the schema must be generated and added - Here is the command

grails dbm-gorm-diff description-of-change.groovy --add

For info, The full set of database migrations can be re-generated with the command 

grails dbm-generate-gorm-changelog my-new-changelog.groovy

This can be useful when manually crafting deltas. Developers must be careful when creating upgrades of existing systems! gorm-dbm-diff is the way to go normally!


