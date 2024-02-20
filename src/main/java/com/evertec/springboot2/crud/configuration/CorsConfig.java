package com.evertec.springboot2.crud.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000") // Permitir solicitudes desde este origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir estos métodos HTTP
                .allowCredentials(true); // Permitir el uso de credenciales (como cookies)
    }
}