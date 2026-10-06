package com.marketplace.shared.domain;

/**
 * Estados del ciclo de verificacion del Profesional.
 * Corresponde al Diagrama de Estados de Profesional (seccion 5.6.4).
 */
public enum EstadoVerificacion {
    REGISTRADO,
    DOCUMENTOS_ENVIADOS,
    EN_REVISION,
    ACTIVO,
    RECHAZADO,
    DOCUMENTACION_INCOMPLETA,
    SUSPENDIDO
}
