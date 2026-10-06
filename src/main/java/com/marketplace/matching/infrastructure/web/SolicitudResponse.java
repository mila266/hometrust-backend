package com.marketplace.matching.infrastructure.web;

import com.marketplace.matching.domain.model.Solicitud;

/** DTO de salida que se envia al frontend. */
public record SolicitudResponse(
        Long id, Long idCliente, Long idProfesional, String descripcion,
        String ubicacion, String tipo, String estado, double tarifaAcordada) {

    public static SolicitudResponse desde(Solicitud s) {
        return new SolicitudResponse(
                s.getId(), s.getIdCliente(), s.getIdProfesional(), s.getDescripcion(),
                s.getUbicacion(), s.getTipo(), s.getEstado().name(), s.getTarifaAcordada());
    }
}
