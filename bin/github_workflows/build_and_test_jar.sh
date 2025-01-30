#!/bin/bash

# Set env
set -o allexport
source .env
set +o allexport

# Run test
./mvnw test
