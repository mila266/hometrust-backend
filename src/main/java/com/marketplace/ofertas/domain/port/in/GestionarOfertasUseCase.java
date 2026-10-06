package com.marketplace.ofertas.domain.port.in;

import com.marketplace.ofertas.domain.model.Oferta;
import java.util.List;

/**
 * Puerto de ENTRADA del subsistema Ofertas (flujo de emergencia).
 */
public interface GestionarOfertasUseCase {

    /** Un tecnico envia una oferta ante una solicitud de emergencia. */
    Oferta enviar(Long idSolicitud, Long idProfesional, double tarifaPropuesta,
                  int tiempoEstimadoLlegada, String ubicacionActual);

    /** Ofertas recibidas por una solicitud (el cliente las compara). */
    List<Oferta> listarPorSolicitud(Long idSolicitud);

    /** Ofertas que un tecnico ha enviado (para ver su estado). */
    List<Oferta> listarPorProfesional(Long idProfesional);

    /**
     * El cliente elige una oferta: esa queda ACEPTADA, las demas RECHAZADAS, y la
     * solicitud pasa a ACEPTADA con ese tecnico y su tarifa (via Matching).
     */
    Oferta aceptar(Long idOferta);
}
