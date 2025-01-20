#!/bin/bash

# Set env
set -o allexport
source .env
set +o allexport

# Run
psql -f docker/postgreql/sql/01-create-users-table.sql
psql -f docker/postgreql/sql/02-create-password-reset-tokens-table.sql
psql -f docker/postgreql/sql/03-create-maa-tokens-table.sql
psql -f docker/postgreql/sql/04-create-plans-table.sql
psql -f docker/postgreql/sql/05-create-days-table.sql
psql -f docker/postgreql/sql/06-create-google-places-table.sql
psql -f docker/postgreql/sql/07-create-google-place-types-table.sql
psql -f docker/postgreql/sql/08-create-google-type-associations-table.sql
psql -f docker/postgreql/sql/09-create-transportations-type.sql
psql -f docker/postgreql/sql/10-create-places-table.sql
psql -f docker/postgreql/sql/11-create-packings-table.sql
