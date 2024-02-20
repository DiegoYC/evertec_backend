FROM openjdk:8-jdk-alpine
WORKDIR /app

COPY target/springboot2-postgresql-crud-0.0.1-SNAPSHOT app.jar
EXPOSE 8080

CMD ["java", "-jar", "springboot2-postgresql-crud.jar"]