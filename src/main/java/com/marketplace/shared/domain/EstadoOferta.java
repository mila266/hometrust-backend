package com.marketplace.shared.domain;

/**
 * Estados de una Oferta en el flujo de emergencia (broadcast).
 * El tecnico envia una oferta (ENVIADA); el cliente elige una (ACEPTADA) y las
 * demas quedan RECHAZADA.
 */
public enum EstadoOferta {
    ENVIADA,
    ACEPTADA,
    RECHAZADA
}
