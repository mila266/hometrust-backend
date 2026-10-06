package com.marketplace.perfiles.infrastructure.web;

import com.marketplace.perfiles.domain.model.CategoriaServicio;

/** DTO de salida de una categoria de servicio. */
public record CategoriaResponse(Long id, String nombre, String oficio, double tarifaBaseReferencial) {
    public static CategoriaResponse desde(CategoriaServicio c) {
        return new CategoriaResponse(c.getId(), c.getNombre(), c.getOficio(), c.getTarifaBaseReferencial());
    }
}
