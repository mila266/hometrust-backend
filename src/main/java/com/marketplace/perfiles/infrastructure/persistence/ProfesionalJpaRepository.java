package com.marketplace.perfiles.infrastructure.persistence;

import com.marketplace.shared.domain.EstadoVerificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/** Repositorio Spring Data JPA (infraestructura). */
public interface ProfesionalJpaRepository extends JpaRepository<ProfesionalEntity, Long> {
    List<ProfesionalEntity> findByEstadoVerificacion(EstadoVerificacion estado);
}
