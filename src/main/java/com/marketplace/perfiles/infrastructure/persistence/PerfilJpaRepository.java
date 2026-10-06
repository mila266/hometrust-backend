package com.marketplace.perfiles.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/** Repositorio Spring Data JPA para la tabla perfil_profesional. */
public interface PerfilJpaRepository extends JpaRepository<PerfilProfesionalEntity, Long> {
    Optional<PerfilProfesionalEntity> findByIdProfesional(Long idProfesional);
}
