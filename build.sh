#!/bin/bash

# this script is called by Skaffold to build a container image
echo building image $IMAGE
./mvnw clean package -DskipTests -Dquarkus.container-image.build=true -Dquarkus.container-image.image=$IMAGE
