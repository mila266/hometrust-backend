package com.marketplace.favoritos.infrastructure.persistence;

import jakarta.persistence.*;

/** Entidad JPA: mapea la tabla "cliente_favorito" (con clave surrogate id_favorito). */
@Entity
@Table(name = "cliente_favorito")
public class FavoritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito")
    private Long id;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    protected FavoritoEntity() {}

    public FavoritoEntity(Long id, Long idCliente, Long idProfesional) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProfesional = idProfesional;
    }

    public Long getId() { return id; }
    public Long getIdCliente() { return idCliente; }
    public Long getIdProfesional() { return idProfesional; }
}
