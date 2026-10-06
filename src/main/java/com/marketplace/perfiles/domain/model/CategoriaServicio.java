package com.marketplace.perfiles.domain.model;

/**
 * Entidad de dominio CategoriaServicio: un oficio que la plataforma ofrece, con
 * su tarifa base referencial (ej. Gasfiteria, Electricidad). Dominio puro.
 */
public class CategoriaServicio {

    private final Long id;
    private final String nombre;
    private final String oficio;
    private final double tarifaBaseReferencial;

    public CategoriaServicio(Long id, String nombre, String oficio, double tarifaBaseReferencial) {
        this.id = id;
        this.nombre = nombre;
        this.oficio = oficio;
        this.tarifaBaseReferencial = tarifaBaseReferencial;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getOficio() { return oficio; }
    public double getTarifaBaseReferencial() { return tarifaBaseReferencial; }
}
