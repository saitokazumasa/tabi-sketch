#!/bin/bash

# Set env
set -o allexport
source .env
export PGHOST="$DATABASE_HOST"
export PGPORT="$DATABASE_PORT"
export PGDATABASE="$DATABASE_NAME"
export PGUSER="$DATABASE_USERNAME"
export PGPASSWORD="$DATABASE_PASSWORD"

# Run
psql -f sql/DropTables.sql
