#!/bin/bash

set -e

echo "Building gen-commiting project..."
./gradlew clean build

echo "Building the Docker image..."
docker build -t gen-commiting .

echo "Running the Docker container..."
docker run -d -p 8090:8090 --name gen-commiting gen-commiting