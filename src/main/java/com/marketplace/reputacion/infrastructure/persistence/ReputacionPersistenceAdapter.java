package com.marketplace.reputacion.infrastructure.persistence;

import com.marketplace.reputacion.domain.model.Calificacion;
import com.marketplace.reputacion.domain.model.Reputacion;
import com.marketplace.reputacion.domain.port.out.ReputacionRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * Adaptador de SALIDA: implementa ReputacionRepositoryPort con JPA.
 * Traduce entre entidades de dominio y entidades JPA de dos tablas
 * (calificacion y reputacion).
 */
@Component
public class ReputacionPersistenceAdapter implements ReputacionRepositoryPort {

    private final ReputacionJpaRepository reputacionJpa;
    private final CalificacionJpaRepository calificacionJpa;

    public ReputacionPersistenceAdapter(ReputacionJpaRepository reputacionJpa,
                                        CalificacionJpaRepository calificacionJpa) {
        this.reputacionJpa = reputacionJpa;
        this.calificacionJpa = calificacionJpa;
    }

    @Override
    public Calificacion guardarCalificacion(Calificacion c) {
        CalificacionEntity entity = new CalificacionEntity(
                c.getId(), c.getIdSolicitud(), c.getPuntaje(), c.getComentario(), c.getFecha());
        CalificacionEntity guardada = calificacionJpa.save(entity);
        return new Calificacion(guardada.getId(), guardada.getIdSolicitud(),
                guardada.getPuntaje(), guardada.getComentario(), guardada.getFecha());
    }

    @Override
    public Optional<Reputacion> buscarPorProfesional(Long idProfesional) {
        return reputacionJpa.findByIdProfesional(idProfesional).map(this::aDominio);
    }

    @Override
    public Reputacion guardarReputacion(Reputacion r) {
        ReputacionEntity entity = new ReputacionEntity(
                r.getId(), r.getIdProfesional(), r.getCalificacionPromedio(), r.getTotalServicios());
        return aDominio(reputacionJpa.save(entity));
    }

    private Reputacion aDominio(ReputacionEntity e) {
        return new Reputacion(e.getId(), e.getIdProfesional(),
                e.getCalificacionPromedio(), e.getTotalServicios());
    }
}
