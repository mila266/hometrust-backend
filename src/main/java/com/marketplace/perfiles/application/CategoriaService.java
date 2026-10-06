package com.marketplace.perfiles.application;

import com.marketplace.perfiles.domain.model.CategoriaServicio;
import com.marketplace.perfiles.domain.port.in.GestionarCategoriasUseCase;
import com.marketplace.perfiles.domain.port.out.CategoriaRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicio de aplicacion para el catalogo de categorias (gestion del Admin). */
@Service
public class CategoriaService implements GestionarCategoriasUseCase {

    private final CategoriaRepositoryPort repositorio;

    public CategoriaService(CategoriaRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<CategoriaServicio> listar() {
        return repositorio.buscarTodas();
    }

    @Override
    public CategoriaServicio crear(String nombre, String oficio, double tarifaBaseReferencial) {
        return repositorio.guardar(new CategoriaServicio(null, nombre, oficio, tarifaBaseReferencial));
    }

    @Override
    public CategoriaServicio actualizar(Long id, String nombre, String oficio, double tarifaBaseReferencial) {
        return repositorio.guardar(new CategoriaServicio(id, nombre, oficio, tarifaBaseReferencial));
    }

    @Override
    public void eliminar(Long id) {
        repositorio.eliminar(id);
    }
}
