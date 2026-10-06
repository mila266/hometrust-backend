package com.marketplace.perfiles.infrastructure.persistence;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA: mapea la tabla "perfil_profesional" y la relacion muchos-a-muchos
 * con categoria_servicio a traves de la tabla "perfil_categoria".
 */
@Entity
@Table(name = "perfil_profesional")
public class PerfilProfesionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long id;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private boolean destacado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "perfil_categoria",
            joinColumns = @JoinColumn(name = "id_perfil"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<CategoriaServicioEntity> categorias = new ArrayList<>();

    protected PerfilProfesionalEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdProfesional() { return idProfesional; }
    public void setIdProfesional(Long idProfesional) { this.idProfesional = idProfesional; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isDestacado() { return destacado; }
    public void setDestacado(boolean destacado) { this.destacado = destacado; }
    public List<CategoriaServicioEntity> getCategorias() { return categorias; }
    public void setCategorias(List<CategoriaServicioEntity> categorias) { this.categorias = categorias; }
}
