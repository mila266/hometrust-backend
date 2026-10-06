package com.marketplace.perfiles.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/** Repositorio Spring Data JPA para la tabla categoria_servicio. */
public interface CategoriaJpaRepository extends JpaRepository<CategoriaServicioEntity, Long> {
}
