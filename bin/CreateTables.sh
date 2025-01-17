#!/bin/bash

# Set env
set -o allexport
source .env
PGHOST="$DATABASE_HOST"
PGPORT="$DATABASE_PORT"
PGDATABASE="$DATABASE_NAME"
PGUSER="$DATABASE_USERNAME"
PGPASSWORD="$DATABASE_PASSWORD"
set +o allexport

# Run
psql -f sql/CreateTables.sql
