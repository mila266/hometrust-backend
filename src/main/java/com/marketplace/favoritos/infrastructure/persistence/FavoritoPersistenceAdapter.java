package com.marketplace.favoritos.infrastructure.persistence;

import com.marketplace.favoritos.domain.model.Favorito;
import com.marketplace.favoritos.domain.port.out.FavoritoRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/** Adaptador de SALIDA: implementa FavoritoRepositoryPort con JPA. */
@Component
public class FavoritoPersistenceAdapter implements FavoritoRepositoryPort {

    private final FavoritoJpaRepository jpa;

    public FavoritoPersistenceAdapter(FavoritoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public List<Favorito> buscarPorCliente(Long idCliente) {
        return jpa.findByIdCliente(idCliente).stream()
                .map(e -> new Favorito(e.getId(), e.getIdCliente(), e.getIdProfesional()))
                .toList();
    }

    @Override
    public boolean existe(Long idCliente, Long idProfesional) {
        return jpa.existsByIdClienteAndIdProfesional(idCliente, idProfesional);
    }

    @Override
    public Favorito guardar(Favorito f) {
        FavoritoEntity e = new FavoritoEntity(f.getId(), f.getIdCliente(), f.getIdProfesional());
        FavoritoEntity g = jpa.save(e);
        return new Favorito(g.getId(), g.getIdCliente(), g.getIdProfesional());
    }

    @Override
    @Transactional
    public void eliminar(Long idCliente, Long idProfesional) {
        jpa.findByIdClienteAndIdProfesional(idCliente, idProfesional).ifPresent(jpa::delete);
    }
}
