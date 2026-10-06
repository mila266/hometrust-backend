package com.marketplace.perfiles.infrastructure.web;

import com.marketplace.perfiles.domain.model.Documento;

/** DTO de salida de un documento. */
public record DocumentoResponse(Long id, Long idProfesional, String tipo, String url, String estado) {
    public static DocumentoResponse desde(Documento d) {
        return new DocumentoResponse(d.getId(), d.getIdProfesional(), d.getTipo(), d.getUrl(), d.getEstado());
    }
}
