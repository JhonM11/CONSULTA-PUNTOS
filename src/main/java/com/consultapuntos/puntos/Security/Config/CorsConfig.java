package com.consultapuntos.puntos.Security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {



    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todas las rutas
                        .allowedOrigins("*") // Permite solo el origen de tu frontend
                        .allowedHeaders("Content-Type", "Authorization")// Encabezados específicos
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                        .exposedHeaders("Content-Disposition")
                        .maxAge(3600); // Tiempo de cache del preflight en segundos
            }
        };
    }
}
