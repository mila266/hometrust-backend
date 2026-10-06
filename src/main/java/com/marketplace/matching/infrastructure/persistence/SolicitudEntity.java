package com.marketplace.matching.infrastructure.persistence;

import com.marketplace.shared.domain.EstadoSolicitud;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad JPA: mapea la tabla "solicitud".
 * Separada de la entidad de dominio (Solicitud): el dominio no conoce JPA.
 */
@Entity
@Table(name = "solicitud")
public class SolicitudEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long id;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_profesional")
    private Long idProfesional;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private String tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado;

    @Column(name = "tarifa_acordada", nullable = false)
    private double tarifaAcordada;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    protected SolicitudEntity() {}

    public SolicitudEntity(Long id, Long idCliente, Long idProfesional, String descripcion,
                           String ubicacion, String tipo, EstadoSolicitud estado,
                           double tarifaAcordada, LocalDateTime fechaCreacion) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProfesional = idProfesional;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.estado = estado;
        this.tarifaAcordada = tarifaAcordada;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public Long getIdCliente() { return idCliente; }
    public Long getIdProfesional() { return idProfesional; }
    public String getDescripcion() { return descripcion; }
    public String getUbicacion() { return ubicacion; }
    public String getTipo() { return tipo; }
    public EstadoSolicitud getEstado() { return estado; }
    public double getTarifaAcordada() { return tarifaAcordada; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}
