# Utiliza una imagen base de OpenJDK 8
FROM openjdk:8-jdk-alpine

# Copia el artefacto JAR de tu aplicación a la imagen
COPY target/springboot2-postgresql-crud-0.0.1-SNAPSHOT.jar /app.jar

# Expone el puerto 8080 en la imagen
EXPOSE 8080

# Ejecuta la aplicación Spring Boot cuando se inicia el contenedor
ENTRYPOINT ["java","-jar","/app.jar"]