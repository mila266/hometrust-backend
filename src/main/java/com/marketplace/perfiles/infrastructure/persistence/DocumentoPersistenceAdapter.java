package com.marketplace.perfiles.infrastructure.persistence;

import com.marketplace.perfiles.domain.model.Documento;
import com.marketplace.perfiles.domain.port.out.DocumentoRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;

/** Adaptador de SALIDA: implementa DocumentoRepositoryPort con JPA. */
@Component
public class DocumentoPersistenceAdapter implements DocumentoRepositoryPort {

    private final DocumentoJpaRepository jpa;

    public DocumentoPersistenceAdapter(DocumentoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Documento guardar(Documento d) {
        DocumentoEntity e = new DocumentoEntity(d.getId(), d.getIdProfesional(), d.getTipo(), d.getUrl(), d.getEstado());
        return aDominio(jpa.save(e));
    }

    @Override
    public List<Documento> buscarPorProfesional(Long idProfesional) {
        return jpa.findByIdProfesional(idProfesional).stream().map(this::aDominio).toList();
    }

    private Documento aDominio(DocumentoEntity e) {
        return new Documento(e.getId(), e.getIdProfesional(), e.getTipo(), e.getUrl(), e.getEstado());
    }
}
