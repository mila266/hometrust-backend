package com.marketplace.perfiles.domain.port.in;

import com.marketplace.perfiles.domain.model.Documento;
import java.util.List;

/**
 * Puerto de ENTRADA: subir y listar documentos del profesional
 * (caso de uso "Subir Documentos", parte de Registrar/Verificar Profesional).
 */
public interface GestionarDocumentosUseCase {
    Documento subir(Long idProfesional, String tipo, String url);
    List<Documento> listar(Long idProfesional);
}
