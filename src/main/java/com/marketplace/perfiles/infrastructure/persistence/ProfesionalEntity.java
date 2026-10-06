package com.marketplace.perfiles.infrastructure.persistence;

import com.marketplace.shared.domain.EstadoVerificacion;
import jakarta.persistence.*;

/**
 * Entidad JPA: mapea la tabla "profesional".
 * Es distinta de la entidad de dominio (Profesional), a proposito:
 * el dominio no debe saber nada de JPA. El adaptador traduce entre ambas.
 */
@Entity
@Table(name = "profesional")
public class ProfesionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesional")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_verificacion", nullable = false)
    private EstadoVerificacion estadoVerificacion;

    @Column(name = "ubicacion_actual")
    private String ubicacionActual;

    protected ProfesionalEntity() {}

    public ProfesionalEntity(Long id, String nombre, EstadoVerificacion estado, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.estadoVerificacion = estado;
        this.ubicacionActual = ubicacion;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public EstadoVerificacion getEstadoVerificacion() { return estadoVerificacion; }
    public String getUbicacionActual() { return ubicacionActual; }
}
