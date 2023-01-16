#!/bin/bash

WORKSPACE=/Users/I519082/public-project/cc-springboot-kafka/
JAR_NAME=cc-springboot-poc.jar
IMAGE_NAME=cc-springboot-poc:0.0.1
DOCKER_HUB_REPOSITORY=huangfulibo
DOCKER_HUB_REPOSITORY_PW=Badi12345678!



# 1. build jar
cd $WORKSPACE
./gradlew bootJar
cd "$WORKSPACE"build/libs
cp cc-poc-0.0.1-SNAPSHOT.jar $JAR_NAME

# 2. maker docker images
cp $JAR_NAME "$WORKSPACE"docker

docker build -t $IMAGE_NAME -f "$WORKSPACE"docker/cc-springboot-kafka-Dockerfile .

# 3. push docker images to remote repository
docker login -u $DOCKER_HUB_REPOSITORY -p $DOCKER_HUB_REPOSITORY_PW
docker tag $IMAGE_NAME $DOCKER_HUB_REPOSITORY/$IMAGE_NAME
docker push $DOCKER_HUB_REPOSITORY/$IMAGE_NAME

# docker run -d -p 8080:8080 cc-springboot-poc:0.0.1
