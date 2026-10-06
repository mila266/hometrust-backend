package com.marketplace.favoritos.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/** Repositorio Spring Data JPA para la tabla cliente_favorito. */
public interface FavoritoJpaRepository extends JpaRepository<FavoritoEntity, Long> {
    List<FavoritoEntity> findByIdCliente(Long idCliente);
    Optional<FavoritoEntity> findByIdClienteAndIdProfesional(Long idCliente, Long idProfesional);
    boolean existsByIdClienteAndIdProfesional(Long idCliente, Long idProfesional);
}
