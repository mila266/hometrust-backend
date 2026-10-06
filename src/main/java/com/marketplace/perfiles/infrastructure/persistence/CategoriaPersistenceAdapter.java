package com.marketplace.perfiles.infrastructure.persistence;

import com.marketplace.perfiles.domain.model.CategoriaServicio;
import com.marketplace.perfiles.domain.port.out.CategoriaRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;

/** Adaptador de SALIDA: implementa CategoriaRepositoryPort con JPA. */
@Component
public class CategoriaPersistenceAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository jpa;

    public CategoriaPersistenceAdapter(CategoriaJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<CategoriaServicio> buscarTodas() {
        return jpa.findAll().stream().map(this::aDominio).toList();
    }

    @Override
    public List<CategoriaServicio> buscarPorIds(List<Long> ids) {
        return jpa.findAllById(ids).stream().map(this::aDominio).toList();
    }

    @Override
    public CategoriaServicio guardar(CategoriaServicio c) {
        CategoriaServicioEntity e = new CategoriaServicioEntity(
                c.getId(), c.getNombre(), c.getOficio(), c.getTarifaBaseReferencial());
        return aDominio(jpa.save(e));
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    static CategoriaServicio aDominioEstatico(CategoriaServicioEntity e) {
        return new CategoriaServicio(e.getId(), e.getNombre(), e.getOficio(), e.getTarifaBaseReferencial());
    }
    private CategoriaServicio aDominio(CategoriaServicioEntity e) { return aDominioEstatico(e); }
}
