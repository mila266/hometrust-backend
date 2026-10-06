package com.marketplace.notificaciones.infrastructure;

import com.marketplace.notificaciones.domain.port.out.NotificadorPort;
import com.marketplace.shared.domain.event.ServicioFinalizado;
import com.marketplace.shared.domain.event.SolicitudAceptada;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Consumidor de eventos de dominio (patron Observer).
 * El subsistema Notificaciones reacciona a los hechos publicados por Matching
 * sin que Matching lo conozca. Agregar o quitar reacciones no afecta a Matching.
 */
@Component
public class ListenerNotificaciones {

    private final NotificadorPort notificador;

    public ListenerNotificaciones(NotificadorPort notificador) {
        this.notificador = notificador;
    }

    @EventListener
    public void alAceptarSolicitud(SolicitudAceptada evento) {
        notificador.enviar(
                "cliente:" + evento.idCliente(),
                "Un tecnico acepto tu solicitud #" + evento.idSolicitud() + ". Pronto se pondra en contacto.");
    }

    @EventListener
    public void alFinalizarServicio(ServicioFinalizado evento) {
        notificador.enviar(
                "cliente:" + evento.idCliente(),
                "Tu servicio de la solicitud #" + evento.idSolicitud() + " finalizo. Ya puedes calificar al tecnico.");
        notificador.enviar(
                "tecnico:" + evento.idProfesional(),
                "Finalizaste el servicio de la solicitud #" + evento.idSolicitud() + ". El pago sera procesado.");
    }
}
