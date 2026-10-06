package com.marketplace.ofertas.infrastructure.persistence;

import com.marketplace.shared.domain.EstadoOferta;
import jakarta.persistence.*;

/** Entidad JPA: mapea la tabla "oferta". */
@Entity
@Table(name = "oferta")
public class OfertaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferta")
    private Long id;

    @Column(name = "id_solicitud", nullable = false)
    private Long idSolicitud;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(name = "tarifa_propuesta", nullable = false)
    private double tarifaPropuesta;

    @Column(name = "tiempo_estimado_llegada", nullable = false)
    private int tiempoEstimadoLlegada;

    @Column(name = "ubicacion_actual")
    private String ubicacionActual;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoOferta estado;

    protected OfertaEntity() {}

    public OfertaEntity(Long id, Long idSolicitud, Long idProfesional, double tarifaPropuesta,
                        int tiempoEstimadoLlegada, String ubicacionActual, EstadoOferta estado) {
        this.id = id;
        this.idSolicitud = idSolicitud;
        this.idProfesional = idProfesional;
        this.tarifaPropuesta = tarifaPropuesta;
        this.tiempoEstimadoLlegada = tiempoEstimadoLlegada;
        this.ubicacionActual = ubicacionActual;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public Long getIdSolicitud() { return idSolicitud; }
    public Long getIdProfesional() { return idProfesional; }
    public double getTarifaPropuesta() { return tarifaPropuesta; }
    public int getTiempoEstimadoLlegada() { return tiempoEstimadoLlegada; }
    public String getUbicacionActual() { return ubicacionActual; }
    public EstadoOferta getEstado() { return estado; }
}
