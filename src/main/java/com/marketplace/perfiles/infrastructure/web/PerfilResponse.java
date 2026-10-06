package com.marketplace.perfiles.infrastructure.web;

import com.marketplace.perfiles.domain.model.PerfilProfesional;
import java.util.List;

/** DTO de salida del perfil del tecnico (incluye datos listos para el directorio). */
public record PerfilResponse(
        Long idProfesional, String descripcion, boolean destacado,
        String oficioPrincipal, double tarifaDesde, List<CategoriaResponse> categorias) {

    public static PerfilResponse desde(PerfilProfesional p) {
        return new PerfilResponse(
                p.getIdProfesional(), p.getDescripcion(), p.isDestacado(),
                p.oficioPrincipal(), p.tarifaDesde(),
                p.getCategorias().stream().map(CategoriaResponse::desde).toList());
    }
}
