package com.marketplace.favoritos.domain.model;

/**
 * Entidad de dominio Favorito: vincula un Cliente con un Profesional de confianza
 * (relacion "favoritos" del diagrama de clases). Dominio puro.
 */
public class Favorito {

    private Long id;
    private final Long idCliente;
    private final Long idProfesional;

    public Favorito(Long id, Long idCliente, Long idProfesional) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProfesional = idProfesional;
    }

    public static Favorito crear(Long idCliente, Long idProfesional) {
        return new Favorito(null, idCliente, idProfesional);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdCliente() { return idCliente; }
    public Long getIdProfesional() { return idProfesional; }
}
