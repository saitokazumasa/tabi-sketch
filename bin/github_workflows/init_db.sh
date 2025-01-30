#!/bin/bash

# Get all .sql files
SQL_DIRECTORY=$1
SQL_LIST=$(find "$SQL_DIRECTORY" -type f -name ".sql")

# Set env
set -o allexport
source .env
PGHOST="$POSTGRES_HOST"
PGPORT="$POSTGRES_PORT"
PGDATABASE="$POSTGRES_DB"
PGUSER="$POSTGRES_USER"
PGPASSWORD="$POSTGRES_PASSWORD"
set +o allexport

# Run
for SQL in $SQL_LIST; do
  psql -f "$SQL"
done

#psql -f docker/postgresql/sql/01-create-users-table.sql
#psql -f docker/postgresql/sql/02-create-password-reset-tokens-table.sql
#psql -f docker/postgresql/sql/03-create-maa-tokens-table.sql
#psql -f docker/postgresql/sql/04-create-plans-table.sql
#psql -f docker/postgresql/sql/05-create-days-table.sql
#psql -f docker/postgresql/sql/06-create-google-places-table.sql
#psql -f docker/postgresql/sql/07-create-google-place-types-table.sql
#psql -f docker/postgresql/sql/08-create-google-type-associations-table.sql
#psql -f docker/postgresql/sql/09-create-transportations-type.sql
#psql -f docker/postgresql/sql/10-create-places-table.sql
#psql -f docker/postgresql/sql/11-create-packings-table.sql
