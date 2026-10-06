package com.marketplace.perfiles.domain.model;

/** Entidad de dominio Documento (certificaciones / identidad del profesional). */
public class Documento {
    private Long id;
    private Long idProfesional;
    private String tipo;
    private String url;
    private String estado; // PENDIENTE, APROBADO, RECHAZADO

    public Documento(Long id, Long idProfesional, String tipo, String url, String estado) {
        this.id = id;
        this.idProfesional = idProfesional;
        this.tipo = tipo;
        this.url = url;
        this.estado = estado;
    }

    /** Factory Method: el profesional registra un documento (queda PENDIENTE). */
    public static Documento registrar(Long idProfesional, String tipo, String url) {
        if (tipo == null || tipo.isBlank()) throw new IllegalArgumentException("El tipo de documento es obligatorio");
        if (url == null || url.isBlank()) throw new IllegalArgumentException("La url del documento es obligatoria");
        return new Documento(null, idProfesional, tipo, url, "PENDIENTE");
    }

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }
    public Long getIdProfesional() { return idProfesional; }
    public String getTipo() { return tipo; }
    public String getUrl() { return url; }
    public String getEstado() { return estado; }
}
