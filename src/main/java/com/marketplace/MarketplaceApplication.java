package com.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de arranque de la aplicacion.
 * Marketplace de Servicios Profesionales a Domicilio.
 * Arquitectura Hexagonal (Ports and Adapters), organizada por subsistemas.
 */
@SpringBootApplication
public class MarketplaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketplaceApplication.class, args);
    }
}
