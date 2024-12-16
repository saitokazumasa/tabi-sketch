#!/bin/bash

# Create Tables
psql -U postgres -d tabisketch -f .run/sql/CreateUsersTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreatePasswordResetTokensTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreateMailAddressAuthTokensTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreatePlansTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreateDaysTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreateGooglePlacesTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreateGooglePlaceTypesTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreateGoogleTypeAssociationsTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreateTransportationsType.sql
psql -U postgres -d tabisketch -f .run/sql/CreatePlacesTable.sql
psql -U postgres -d tabisketch -f .run/sql/CreatePackingsTable.sql
