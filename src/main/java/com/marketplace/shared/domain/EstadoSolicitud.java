package com.marketplace.shared.domain;

/**
 * Estados del ciclo de vida de la Solicitud.
 * Corresponde al Diagrama de Estados de Solicitud (seccion 5.6.4).
 */
public enum EstadoSolicitud {
    CREADA,
    PUBLICADA,
    ACEPTADA,
    EN_CURSO,
    FINALIZADA,
    CALIFICADA,
    CANCELADA
}
