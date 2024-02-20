FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
FROM openjdk:8-jdk-alpine

COPY target/springboot2-postgresql-crud-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java","-jar","/app.jar"]