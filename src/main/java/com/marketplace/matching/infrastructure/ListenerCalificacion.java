package com.marketplace.matching.infrastructure;

import com.marketplace.matching.domain.port.in.SolicitarServicioUseCase;
import com.marketplace.shared.domain.event.ServicioCalificado;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Consumidor de eventos de dominio (patron Observer).
 * Cuando Reputacion publica que un servicio fue calificado, Matching cierra la
 * solicitud (la pasa a CALIFICADA). Reputacion no conoce a Matching: solo publica.
 */
@Component
public class ListenerCalificacion {

    private final SolicitarServicioUseCase matching;

    public ListenerCalificacion(SolicitarServicioUseCase matching) {
        this.matching = matching;
    }

    @EventListener
    public void alCalificarServicio(ServicioCalificado evento) {
        matching.marcarCalificada(evento.idSolicitud());
    }
}
