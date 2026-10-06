package com.marketplace.perfiles.infrastructure.persistence;

import com.marketplace.perfiles.domain.model.Profesional;
import com.marketplace.perfiles.domain.port.out.ProfesionalRepositoryPort;
import com.marketplace.shared.domain.EstadoVerificacion;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador de SALIDA: implementa el puerto ProfesionalRepositoryPort usando JPA.
 * Traduce entre la entidad de dominio (Profesional) y la entidad JPA (ProfesionalEntity).
 * Si manana cambiaras de PostgreSQL a otra BD, solo se cambia este adaptador.
 */
@Component
public class ProfesionalPersistenceAdapter implements ProfesionalRepositoryPort {

    private final ProfesionalJpaRepository jpa;

    public ProfesionalPersistenceAdapter(ProfesionalJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Profesional guardar(Profesional p) {
        ProfesionalEntity entity = new ProfesionalEntity(
            p.getId(), p.getNombre(), p.getEstado(), p.getUbicacionActual());
        ProfesionalEntity guardado = jpa.save(entity);
        return aDominio(guardado);
    }

    @Override
    public Optional<Profesional> buscarPorId(Long id) {
        return jpa.findById(id).map(this::aDominio);
    }

    @Override
    public List<Profesional> buscarActivos() {
        return jpa.findByEstadoVerificacion(EstadoVerificacion.ACTIVO)
            .stream().map(this::aDominio).toList();
    }

    @Override
    public List<Profesional> buscarTodos() {
        return jpa.findAll().stream().map(this::aDominio).toList();
    }

    private Profesional aDominio(ProfesionalEntity e) {
        return new Profesional(e.getId(), e.getNombre(),
            e.getUbicacionActual(), e.getEstadoVerificacion());
    }
}
