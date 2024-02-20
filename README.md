# CRUD Tareas
Este proyecto es una aplicación de Spring Boot que proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) relacionado con tareas. Utiliza una base de datos PostgreSQL para almacenar los datos y metodos REST para interactuar con la aplicación.

## Descripción
Utiliza el framework Spring Boot para la capa de backend y PostgreSQL como base de datos para almacenar la información de las tareas.

## Características
Gestión completa de tareas: Crear, Leer, Actualizar y Eliminar tareas.
Seguridad integrada con Spring Security.
Documentación de API con Swagger.

## Prerequisitos
Contar con una BD PostgreSQL y ejecutar el siguiente script:

```
CREATE TABLE tarea (
identificador SERIAL PRIMARY KEY,
descripcion VARCHAR(255) NOT NULL,
fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
vigente BOOLEAN
);
```


## Instalación y Uso
Para ejecutar la aplicación localmente, sigue estos pasos:

Clona el repositorio en tu máquina local.

```
https://github.com/DiegoYC/evertec_backend.git
```

Asegúrate de tener Maven y Java instalados en tu sistema.
Configura una base de datos PostgreSQL y actualiza la configuración en el archivo application.properties.
Compila el proyecto usando Maven: mvn clean install.
Ejecuta la aplicación: java -jar target/springboot2-postgresql-crud-0.0.1-SNAPSHOT.jar.
Accede a la aplicación desde tu navegador web en http://localhost:8080.

## Docker
En el repositorio tambien se incluye el dockerfile.

## Tecnologías Utilizadas
Spring Boot
Spring Data JPA
Spring Security
PostgreSQL
Maven
Swagger (https://stingray-app-ik4ek.ondigitalocean.app/swagger-ui.html)