package com.marketplace.perfiles.infrastructure.persistence;

import jakarta.persistence.*;

/** Entidad JPA: mapea la tabla "categoria_servicio". */
@Entity
@Table(name = "categoria_servicio")
public class CategoriaServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String oficio;

    @Column(name = "tarifa_base_referencial", nullable = false)
    private double tarifaBaseReferencial;

    protected CategoriaServicioEntity() {}

    public CategoriaServicioEntity(Long id, String nombre, String oficio, double tarifaBaseReferencial) {
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
