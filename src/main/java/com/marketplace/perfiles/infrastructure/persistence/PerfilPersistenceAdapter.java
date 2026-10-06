package com.marketplace.perfiles.infrastructure.persistence;

import com.marketplace.perfiles.domain.model.CategoriaServicio;
import com.marketplace.perfiles.domain.model.PerfilProfesional;
import com.marketplace.perfiles.domain.port.out.PerfilRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/** Adaptador de SALIDA: implementa PerfilRepositoryPort con JPA. */
@Component
public class PerfilPersistenceAdapter implements PerfilRepositoryPort {

    private final PerfilJpaRepository perfilJpa;
    private final CategoriaJpaRepository categoriaJpa;

    public PerfilPersistenceAdapter(PerfilJpaRepository perfilJpa, CategoriaJpaRepository categoriaJpa) {
        this.perfilJpa = perfilJpa;
        this.categoriaJpa = categoriaJpa;
    }

    @Override
    public Optional<PerfilProfesional> buscarPorProfesional(Long idProfesional) {
        return perfilJpa.findByIdProfesional(idProfesional).map(this::aDominio);
    }

    @Override
    public PerfilProfesional guardar(PerfilProfesional p) {
        List<Long> ids = p.getCategorias().stream().map(CategoriaServicio::getId).toList();
        List<CategoriaServicioEntity> cats = categoriaJpa.findAllById(ids);

        PerfilProfesionalEntity e = perfilJpa.findByIdProfesional(p.getIdProfesional())
                .orElseGet(PerfilProfesionalEntity::new);
        e.setIdProfesional(p.getIdProfesional());
        e.setDescripcion(p.getDescripcion());
        e.setDestacado(p.isDestacado());
        e.setCategorias(cats);
        return aDominio(perfilJpa.save(e));
    }

    private PerfilProfesional aDominio(PerfilProfesionalEntity e) {
        List<CategoriaServicio> cats = e.getCategorias().stream()
                .map(CategoriaPersistenceAdapter::aDominioEstatico).toList();
        return new PerfilProfesional(e.getId(), e.getIdProfesional(),
                e.getDescripcion(), e.isDestacado(), cats);
    }
}
