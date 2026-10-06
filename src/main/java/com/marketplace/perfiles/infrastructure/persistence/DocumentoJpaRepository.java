package com.marketplace.perfiles.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/** Repositorio Spring Data JPA para la tabla documento. */
public interface DocumentoJpaRepository extends JpaRepository<DocumentoEntity, Long> {
    List<DocumentoEntity> findByIdProfesional(Long idProfesional);
}
