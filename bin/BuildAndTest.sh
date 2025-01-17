#!/bin/bash

# Set env
set -o allexport
source .env
set +o allexport

# Build
./bin/BuildJar.sh

#Run
./mvnw surefire:test
