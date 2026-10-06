package com.marketplace.shared.domain.event;

import java.time.LocalDateTime;

/**
 * Evento: el cliente califico un servicio.
 * Lo publica Reputacion y lo consume Matching para cerrar la solicitud
 * (pasarla a CALIFICADA), sin que Reputacion conozca a Matching (Observer).
 */
public record ServicioCalificado(
        Long idSolicitud,
        Long idProfesional,
        int puntaje,
        LocalDateTime ocurridoEn
) implements EventoDominio {

    public static ServicioCalificado de(Long idSolicitud, Long idProfesional, int puntaje) {
        return new ServicioCalificado(idSolicitud, idProfesional, puntaje, LocalDateTime.now());
    }
}
