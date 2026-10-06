package com.marketplace.perfiles.application;

import com.marketplace.perfiles.domain.model.Documento;
import com.marketplace.perfiles.domain.port.in.GestionarDocumentosUseCase;
import com.marketplace.perfiles.domain.port.out.DocumentoRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicio de aplicacion para los documentos del profesional. */
@Service
public class DocumentoService implements GestionarDocumentosUseCase {

    private final DocumentoRepositoryPort repositorio;

    public DocumentoService(DocumentoRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Documento subir(Long idProfesional, String tipo, String url) {
        return repositorio.guardar(Documento.registrar(idProfesional, tipo, url));
    }

    @Override
    public List<Documento> listar(Long idProfesional) {
        return repositorio.buscarPorProfesional(idProfesional);
    }
}
