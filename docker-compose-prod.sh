#!/bin/bash

if [ "$1" == "start" ]; then
  ## https://modulitos.com/blog/lets-deploy-part-1/

  # Remove all containers with the `templatespring_` prefix
  docker rm -f $(docker ps -aq -f name=templatespring_*)

  # Start the production environment
  docker compose -f docker-compose-prod.yml up -d --build --force-recreate --remove-orphans

elif [ "$1" == "stop" ]; then
  # Stop and remove all containers, networks, and volumes defined in docker-compose-prod.yml
  docker compose -f docker-compose-prod.yml down

else
  echo "Usage: $0 {start|stop}"
  exit 1
fi