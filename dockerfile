# Primera etapa: construir la aplicación
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app

# Copiar los archivos del proyecto
COPY pom.xml .
COPY src ./src

# Construir el proyecto sin ejecutar las pruebas
RUN mvn clean package -DskipTests

# Segunda etapa: Ejecutar la aplicación
FROM openjdk:8-jdk-alpine

# Copiar el archivo JAR generado en la etapa anterior
COPY --from=build /app/target/springboot2-postgresql-crud-0.0.1-SNAPSHOT.jar /app.jar

# Especificar el comando de inicio
CMD ["java","-jar","/app.jar"]