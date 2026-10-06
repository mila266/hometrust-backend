package com.marketplace.perfiles.domain.port.in;

import com.marketplace.perfiles.domain.model.CategoriaServicio;
import java.util.List;

/**
 * Puerto de ENTRADA: el Administrador gestiona el catalogo de categorias
 * (caso de uso "Gestionar Categorias de Servicio").
 */
public interface GestionarCategoriasUseCase {
    List<CategoriaServicio> listar();
    CategoriaServicio crear(String nombre, String oficio, double tarifaBaseReferencial);
    CategoriaServicio actualizar(Long id, String nombre, String oficio, double tarifaBaseReferencial);
    void eliminar(Long id);
}
