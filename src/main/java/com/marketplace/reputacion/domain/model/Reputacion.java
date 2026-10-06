package com.marketplace.reputacion.domain.model;

/**
 * Entidad de dominio Reputacion. Mantiene el promedio de calificaciones de un
 * tecnico y lo recalcula al recibir una nueva (RF-19). Dominio puro.
 */
public class Reputacion {

    private Long id;
    private final Long idProfesional;
    private double calificacionPromedio;
    private int totalServicios;

    public Reputacion(Long id, Long idProfesional, double promedio, int total) {
        this.id = id;
        this.idProfesional = idProfesional;
        this.calificacionPromedio = promedio;
        this.totalServicios = total;
    }

    /** Factory Method: reputacion inicial de un tecnico recien aprobado. */
    public static Reputacion inicial(Long idProfesional) {
        return new Reputacion(null, idProfesional, 0.0, 0);
    }

    /** Regla de negocio: recalcular el promedio al agregar una nueva calificacion. */
    public void agregarCalificacion(int puntaje) {
        if (puntaje < 1 || puntaje > 5)
            throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5");
        double suma = calificacionPromedio * totalServicios + puntaje;
        totalServicios++;
        calificacionPromedio = Math.round((suma / totalServicios) * 100.0) / 100.0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdProfesional() { return idProfesional; }
    public double getCalificacionPromedio() { return calificacionPromedio; }
    public int getTotalServicios() { return totalServicios; }
}
