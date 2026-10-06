package com.marketplace.matching.infrastructure.persistence;

import com.marketplace.matching.domain.model.Solicitud;
import com.marketplace.matching.domain.port.out.SolicitudRepositoryPort;
import com.marketplace.shared.domain.EstadoSolicitud;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador de SALIDA: implementa SolicitudRepositoryPort con JPA.
 * Traduce entre la entidad de dominio (Solicitud) y la entidad JPA (SolicitudEntity).
 */
@Component
public class SolicitudPersistenceAdapter implements SolicitudRepositoryPort {

    private final SolicitudJpaRepository jpa;

    public SolicitudPersistenceAdapter(SolicitudJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Solicitud guardar(Solicitud s) {
        SolicitudEntity entity = new SolicitudEntity(
                s.getId(), s.getIdCliente(), s.getIdProfesional(), s.getDescripcion(),
                s.getUbicacion(), s.getTipo(), s.getEstado(),
                s.getTarifaAcordada(), s.getFechaCreacion());
        return aDominio(jpa.save(entity));
    }

    @Override
    public Optional<Solicitud> buscarPorId(Long id) {
        return jpa.findById(id).map(this::aDominio);
    }

    @Override
    public List<Solicitud> buscarPorCliente(Long idCliente) {
        return jpa.findByIdCliente(idCliente).stream().map(this::aDominio).toList();
    }

    @Override
    public List<Solicitud> buscarPorProfesional(Long idProfesional) {
        return jpa.findByIdProfesional(idProfesional).stream().map(this::aDominio).toList();
    }

    @Override
    public List<Solicitud> buscarPorEstados(List<EstadoSolicitud> estados) {
        return jpa.findByEstadoIn(estados).stream().map(this::aDominio).toList();
    }

    private Solicitud aDominio(SolicitudEntity e) {
        return new Solicitud(e.getId(), e.getIdCliente(), e.getIdProfesional(),
                e.getDescripcion(), e.getUbicacion(), e.getTipo(), e.getEstado(),
                e.getTarifaAcordada(), e.getFechaCreacion());
    }
}
