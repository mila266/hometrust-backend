package com.marketplace.perfiles.infrastructure.persistence;

import jakarta.persistence.*;

/** Entidad JPA: mapea la tabla "documento". */
@Entity
@Table(name = "documento")
public class DocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long id;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String estado;

    protected DocumentoEntity() {}

    public DocumentoEntity(Long id, Long idProfesional, String tipo, String url, String estado) {
        this.id = id;
        this.idProfesional = idProfesional;
        this.tipo = tipo;
        this.url = url;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public Long getIdProfesional() { return idProfesional; }
    public String getTipo() { return tipo; }
    public String getUrl() { return url; }
    public String getEstado() { return estado; }
}
