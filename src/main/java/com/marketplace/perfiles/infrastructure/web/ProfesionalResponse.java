package com.marketplace.perfiles.infrastructure.web;

import com.marketplace.perfiles.domain.model.Profesional;

/** DTO de salida que se envia al frontend. */
public record ProfesionalResponse(Long id, String nombre, String ubicacion, String estado) {
    public static ProfesionalResponse desde(Profesional p) {
        return new ProfesionalResponse(
            p.getId(), p.getNombre(), p.getUbicacionActual(), p.getEstado().name());
    }
}
