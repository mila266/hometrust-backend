package com.marketplace.reputacion.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/** Repositorio Spring Data JPA para la tabla calificacion. */
public interface CalificacionJpaRepository extends JpaRepository<CalificacionEntity, Long> {
}
