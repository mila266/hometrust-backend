package com.marketplace.ofertas.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/** Repositorio Spring Data JPA para la tabla oferta. */
public interface OfertaJpaRepository extends JpaRepository<OfertaEntity, Long> {
    List<OfertaEntity> findByIdSolicitud(Long idSolicitud);
    List<OfertaEntity> findByIdProfesional(Long idProfesional);
}
