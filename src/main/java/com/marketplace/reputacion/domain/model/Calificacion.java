package com.marketplace.reputacion.domain.model;

import java.time.LocalDateTime;

/**
 * Entidad de dominio Calificacion: el puntaje que un cliente da a un servicio
 * finalizado (RF-18). Una solicitud solo puede calificarse una vez (regla
 * reforzada por la restriccion UNIQUE de la tabla calificacion).
 */
public class Calificacion {

    private Long id;
    private final Long idSolicitud;
    private final int puntaje;
    private final String comentario;
    private final LocalDateTime fecha;

    public Calificacion(Long id, Long idSolicitud, int puntaje, String comentario, LocalDateTime fecha) {
        this.id = id;
        this.idSolicitud = idSolicitud;
        this.puntaje = puntaje;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    /** Factory Method: valida el puntaje y sella la fecha. */
    public static Calificacion registrar(Long idSolicitud, int puntaje, String comentario) {
        if (puntaje < 1 || puntaje > 5)
            throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5");
        return new Calificacion(null, idSolicitud, puntaje, comentario, LocalDateTime.now());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdSolicitud() { return idSolicitud; }
    public int getPuntaje() { return puntaje; }
    public String getComentario() { return comentario; }
    public LocalDateTime getFecha() { return fecha; }
}
