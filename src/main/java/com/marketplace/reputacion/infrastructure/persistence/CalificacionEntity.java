package com.marketplace.reputacion.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/** Entidad JPA: mapea la tabla "calificacion". */
@Entity
@Table(name = "calificacion")
public class CalificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Long id;

    @Column(name = "id_solicitud", nullable = false, unique = true)
    private Long idSolicitud;

    @Column(nullable = false)
    private int puntaje;

    @Column
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    protected CalificacionEntity() {}

    public CalificacionEntity(Long id, Long idSolicitud, int puntaje, String comentario, LocalDateTime fecha) {
        this.id = id;
        this.idSolicitud = idSolicitud;
        this.puntaje = puntaje;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Long getId() { return id; }
    public Long getIdSolicitud() { return idSolicitud; }
    public int getPuntaje() { return puntaje; }
    public String getComentario() { return comentario; }
    public LocalDateTime getFecha() { return fecha; }
}
