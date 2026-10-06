package com.marketplace.perfiles.domain.port.in;

import com.marketplace.perfiles.domain.model.CategoriaServicio;
import com.marketplace.perfiles.domain.model.PerfilProfesional;
import java.util.List;

/**
 * Puerto de ENTRADA del perfil del tecnico (caso de uso "Gestionar Perfil y Categorias").
 */
public interface GestionarPerfilUseCase {

    /** Perfil del tecnico (vacio si aun no lo configuro). */
    PerfilProfesional obtenerPerfil(Long idProfesional);

    /** El tecnico actualiza su descripcion y las categorias que atiende. */
    PerfilProfesional actualizarPerfil(Long idProfesional, String descripcion,
                                       boolean destacado, List<Long> categoriaIds);

    /** Catalogo de categorias disponibles. */
    List<CategoriaServicio> listarCategorias();
}
