package com.marketplace.ofertas.infrastructure.web;

import com.marketplace.ofertas.domain.model.Oferta;

/** DTO de salida con los datos de una oferta. */
public record OfertaResponse(
        Long id, Long idSolicitud, Long idProfesional, double tarifaPropuesta,
        int tiempoEstimadoLlegada, String ubicacionActual, String estado) {

    public static OfertaResponse desde(Oferta o) {
        return new OfertaResponse(o.getId(), o.getIdSolicitud(), o.getIdProfesional(),
                o.getTarifaPropuesta(), o.getTiempoEstimadoLlegada(), o.getUbicacionActual(), o.getEstado().name());
    }
}
