package com.marketplace.shared.domain.event;

import java.time.LocalDateTime;

/**
 * Evento: el servicio de una solicitud finalizo.
 * Lo consumen:
 *   - Pagos        -> procesa el cobro a traves de la pasarela (Facade).
 *   - Notificaciones -> avisa a cliente y tecnico que pueden calificar.
 * Matching publica el evento sin saber quien lo escucha (Observer).
 */
public record ServicioFinalizado(
        Long idSolicitud,
        Long idCliente,
        Long idProfesional,
        double monto,
        LocalDateTime ocurridoEn
) implements EventoDominio {

    public static ServicioFinalizado de(Long idSolicitud, Long idCliente, Long idProfesional, double monto) {
        return new ServicioFinalizado(idSolicitud, idCliente, idProfesional, monto, LocalDateTime.now());
    }
}
