package com.marketplace.shared.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracion CORS centralizada.
 *
 * En desarrollo, el valor por defecto permite al frontend Angular local
 * (http://localhost:4200). En produccion se define la variable de entorno
 * CORS_ORIGINS con la URL del frontend desplegado (S3/CloudFront), sin tocar
 * el codigo. Acepta varias URLs separadas por coma.
 */
@Configuration
public class ConfiguracionWeb implements WebMvcConfigurer {

    @Value("${app.cors.origenes-permitidos:http://localhost:4200}")
    private String[] origenesPermitidos;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(origenesPermitidos)
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
