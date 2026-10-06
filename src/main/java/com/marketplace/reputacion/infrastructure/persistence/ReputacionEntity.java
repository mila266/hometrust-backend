package com.marketplace.reputacion.infrastructure.persistence;

import jakarta.persistence.*;

/** Entidad JPA: mapea la tabla "reputacion". */
@Entity
@Table(name = "reputacion")
public class ReputacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reputacion")
    private Long id;

    @Column(name = "id_profesional", nullable = false, unique = true)
    private Long idProfesional;

    @Column(name = "calificacion_promedio", nullable = false)
    private double calificacionPromedio;

    @Column(name = "total_servicios", nullable = false)
    private int totalServicios;

    protected ReputacionEntity() {}

    public ReputacionEntity(Long id, Long idProfesional, double calificacionPromedio, int totalServicios) {
        this.id = id;
        this.idProfesional = idProfesional;
        this.calificacionPromedio = calificacionPromedio;
        this.totalServicios = totalServicios;
    }

    public Long getId() { return id; }
    public Long getIdProfesional() { return idProfesional; }
    public double getCalificacionPromedio() { return calificacionPromedio; }
    public int getTotalServicios() { return totalServicios; }
}
