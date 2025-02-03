#!/usr/bin/env bash
#MISE description="docker compose up"

docker compose -f ./docker/01-docker-compose.yml up postgresql app
