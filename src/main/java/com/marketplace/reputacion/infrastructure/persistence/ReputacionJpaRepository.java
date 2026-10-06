package com.marketplace.reputacion.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/** Repositorio Spring Data JPA para la tabla reputacion. */
public interface ReputacionJpaRepository extends JpaRepository<ReputacionEntity, Long> {
    Optional<ReputacionEntity> findByIdProfesional(Long idProfesional);
}
