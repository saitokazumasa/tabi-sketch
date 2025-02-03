#!/usr/bin/env bash
#MISE description="docker compose up"

docker compose -f ./docker/01-docker-compose.yml -f ./docker/02-docker-compose.test.yml up test
