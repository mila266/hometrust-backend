package com.marketplace.pagos.infrastructure;

import com.marketplace.pagos.domain.port.out.PagoPort;
import com.marketplace.shared.domain.event.ServicioFinalizado;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Consumidor de eventos de dominio (patron Observer).
 * El subsistema Pagos cobra el servicio cuando Matching publica que finalizo,
 * usando la fachada de la pasarela (PagoPort). Matching no invoca a Pagos
 * directamente: solo publica el hecho.
 */
@Component
public class ListenerPagos {

    private final PagoPort pasarela;

    public ListenerPagos(PagoPort pasarela) {
        this.pasarela = pasarela;
    }

    @EventListener
    public void alFinalizarServicio(ServicioFinalizado evento) {
        pasarela.procesarPago(evento.idSolicitud(), evento.monto());
    }
}
