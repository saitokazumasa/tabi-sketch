#!/bin/bash

# Set env
set -o allexport
source .env
set +o allexport

# Build
./bin/BuildTailwindCss.sh
./bin/BuildJar.sh

# Run
java -jar target/tabisketch-0.0.1-SNAPSHOT.jar
