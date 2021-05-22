FROM maven:3.8.1-openjdk-15 AS build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
WORKDIR /usr/src/app

RUN mvn dependency:go-offline -B

RUN mvn package

FROM openjdk:15-alpine as launch

ARG JAR_FILE=usr/src/app/target/*.jar
COPY --from=build $JAR_FILE /usr/app/app.jar

ENTRYPOINT exec java -jar /usr/app/app.jar
EXPOSE 8080