package com.marketplace.shared.domain.event;

import java.time.LocalDateTime;

/**
 * Evento: un tecnico (profesional) acepto una solicitud de servicio.
 * Lo consume Notificaciones para avisar al cliente (Observer).
 */
public record SolicitudAceptada(
        Long idSolicitud,
        Long idCliente,
        Long idProfesional,
        LocalDateTime ocurridoEn
) implements EventoDominio {

    public static SolicitudAceptada de(Long idSolicitud, Long idCliente, Long idProfesional) {
        return new SolicitudAceptada(idSolicitud, idCliente, idProfesional, LocalDateTime.now());
    }
}
