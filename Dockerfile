# syntax=docker/dockerfile:1

# Make a docker file
# https://docs.docker.com/language/java/build-images/
# Set of instructions to docker to create the image

# parent image
FROM openjdk:8-alpine

# set working directory
WORKDIR /app

# we need to get the Maven wrapper and our pom.xml file into our image
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN dos2unix mvnw

# Avoid permission error, change the permission.
RUN chmod +x mvnw

# Install dependencies on into image
RUN ./mvnw dependency:go-offline
# This will be overriden if defined in docker compose file or during build if passed to do so.
ENV APP_ENV_NAME qat

# copy all of our code into image
#copy src file to app folder inside the image
COPY src ./src

EXPOSE 5000
# run the image
CMD ["./mvnw", "spring-boot:run"]
