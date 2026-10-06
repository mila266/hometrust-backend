package com.marketplace.perfiles.application;

import com.marketplace.perfiles.domain.model.CategoriaServicio;
import com.marketplace.perfiles.domain.model.PerfilProfesional;
import com.marketplace.perfiles.domain.port.in.GestionarPerfilUseCase;
import com.marketplace.perfiles.domain.port.out.CategoriaRepositoryPort;
import com.marketplace.perfiles.domain.port.out.PerfilRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio de aplicacion del perfil del tecnico y sus categorias.
 */
@Service
public class PerfilService implements GestionarPerfilUseCase {

    private final PerfilRepositoryPort perfiles;
    private final CategoriaRepositoryPort categorias;

    public PerfilService(PerfilRepositoryPort perfiles, CategoriaRepositoryPort categorias) {
        this.perfiles = perfiles;
        this.categorias = categorias;
    }

    @Override
    public PerfilProfesional obtenerPerfil(Long idProfesional) {
        return perfiles.buscarPorProfesional(idProfesional)
                .orElseGet(() -> PerfilProfesional.vacio(idProfesional));
    }

    @Override
    public PerfilProfesional actualizarPerfil(Long idProfesional, String descripcion,
                                              boolean destacado, List<Long> categoriaIds) {
        List<CategoriaServicio> elegidas = categorias.buscarPorIds(categoriaIds);
        PerfilProfesional actual = obtenerPerfil(idProfesional);
        PerfilProfesional nuevo = new PerfilProfesional(
                actual.getId(), idProfesional, descripcion, destacado, elegidas);
        return perfiles.guardar(nuevo);
    }

    @Override
    public List<CategoriaServicio> listarCategorias() {
        return categorias.buscarTodas();
    }
}
