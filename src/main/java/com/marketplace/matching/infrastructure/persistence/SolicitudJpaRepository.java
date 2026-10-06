package com.marketplace.matching.infrastructure.persistence;

import com.marketplace.shared.domain.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/** Repositorio Spring Data JPA (infraestructura) para la tabla solicitud. */
public interface SolicitudJpaRepository extends JpaRepository<SolicitudEntity, Long> {

    List<SolicitudEntity> findByIdCliente(Long idCliente);

    List<SolicitudEntity> findByIdProfesional(Long idProfesional);

    List<SolicitudEntity> findByEstadoIn(List<EstadoSolicitud> estados);
}
