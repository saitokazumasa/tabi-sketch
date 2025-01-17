#!/bin/bash

# Set env
set -o allexport
source .env
set +o allexport

# Build
./bin/BuildTailwindCss.sh
./bin/BuildJarWithoutTest.sh

# Run
java -jar target/tabisketch-0.0.1-SNAPSHOT.jar
