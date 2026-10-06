package com.marketplace.perfiles.domain.port.out;

import com.marketplace.perfiles.domain.model.Documento;
import java.util.List;

/** Puerto de SALIDA para los documentos del profesional. */
public interface DocumentoRepositoryPort {
    Documento guardar(Documento documento);
    List<Documento> buscarPorProfesional(Long idProfesional);
}
