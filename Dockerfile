FROM maven:3.8.1-openjdk-15 AS build

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean install -DskipTests

FROM openjdk:15-alpine

ARG JAR_FILE=usr/src/app/target/*.jar
COPY --from=build $JAR_FILE /usr/app/app.jar
COPY src/main/resources/static/img /usr/app/static/img

ENTRYPOINT exec java -jar /usr/app/app.jar
EXPOSE 8080