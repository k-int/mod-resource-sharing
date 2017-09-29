# mod-resource-sharing


# Database setup

N.B. This project has a database dependency on postgres functionality. The test system requires
the following setup

CREATE USER folio WITH PASSWORD 'folio' SUPERUSER CREATEDB INHERIT LOGIN;

CREATE DATABASE foliotest;
GRANT ALL PRIVILEGES ON DATABASE foliotest to folio;


