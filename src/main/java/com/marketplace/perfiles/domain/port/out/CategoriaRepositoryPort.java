package com.marketplace.perfiles.domain.port.out;

import com.marketplace.perfiles.domain.model.CategoriaServicio;
import java.util.List;

/** Puerto de SALIDA para las categorias de servicio. */
public interface CategoriaRepositoryPort {
    List<CategoriaServicio> buscarTodas();
    List<CategoriaServicio> buscarPorIds(List<Long> ids);
    CategoriaServicio guardar(CategoriaServicio categoria);
    void eliminar(Long id);
}
