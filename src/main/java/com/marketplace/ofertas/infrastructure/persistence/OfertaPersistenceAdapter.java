package com.marketplace.ofertas.infrastructure.persistence;

import com.marketplace.ofertas.domain.model.Oferta;
import com.marketplace.ofertas.domain.port.out.OfertaRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/** Adaptador de SALIDA: implementa OfertaRepositoryPort con JPA. */
@Component
public class OfertaPersistenceAdapter implements OfertaRepositoryPort {

    private final OfertaJpaRepository jpa;

    public OfertaPersistenceAdapter(OfertaJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Oferta guardar(Oferta o) {
        OfertaEntity entity = new OfertaEntity(
                o.getId(), o.getIdSolicitud(), o.getIdProfesional(), o.getTarifaPropuesta(),
                o.getTiempoEstimadoLlegada(), o.getUbicacionActual(), o.getEstado());
        return aDominio(jpa.save(entity));
    }

    @Override
    public Optional<Oferta> buscarPorId(Long id) {
        return jpa.findById(id).map(this::aDominio);
    }

    @Override
    public List<Oferta> buscarPorSolicitud(Long idSolicitud) {
        return jpa.findByIdSolicitud(idSolicitud).stream().map(this::aDominio).toList();
    }

    @Override
    public List<Oferta> buscarPorProfesional(Long idProfesional) {
        return jpa.findByIdProfesional(idProfesional).stream().map(this::aDominio).toList();
    }

    private Oferta aDominio(OfertaEntity e) {
        return new Oferta(e.getId(), e.getIdSolicitud(), e.getIdProfesional(), e.getTarifaPropuesta(),
                e.getTiempoEstimadoLlegada(), e.getUbicacionActual(), e.getEstado());
    }
}
