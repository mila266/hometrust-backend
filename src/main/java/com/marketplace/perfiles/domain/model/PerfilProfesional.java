package com.marketplace.perfiles.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de dominio PerfilProfesional: la "vitrina" del tecnico (descripcion,
 * si esta destacado) y las categorias que atiende (1..*). Dominio puro.
 */
public class PerfilProfesional {

    private Long id;
    private final Long idProfesional;
    private String descripcion;
    private boolean destacado;
    private final List<CategoriaServicio> categorias;

    public PerfilProfesional(Long id, Long idProfesional, String descripcion,
                             boolean destacado, List<CategoriaServicio> categorias) {
        this.id = id;
        this.idProfesional = idProfesional;
        this.descripcion = descripcion;
        this.destacado = destacado;
        this.categorias = categorias != null ? categorias : new ArrayList<>();
    }

    /** Factory Method: perfil vacio para un tecnico que aun no lo ha configurado. */
    public static PerfilProfesional vacio(Long idProfesional) {
        return new PerfilProfesional(null, idProfesional, "", false, new ArrayList<>());
    }

    /** Oficio principal que se muestra en el directorio (el de la primera categoria). */
    public String oficioPrincipal() {
        return categorias.isEmpty() ? "Sin categoria" : categorias.get(0).getOficio();
    }

    /** Tarifa base mas baja entre las categorias (para mostrar "desde S/ X"). */
    public double tarifaDesde() {
        return categorias.stream().mapToDouble(CategoriaServicio::getTarifaBaseReferencial).min().orElse(0);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdProfesional() { return idProfesional; }
    public String getDescripcion() { return descripcion; }
    public boolean isDestacado() { return destacado; }
    public List<CategoriaServicio> getCategorias() { return categorias; }
}
