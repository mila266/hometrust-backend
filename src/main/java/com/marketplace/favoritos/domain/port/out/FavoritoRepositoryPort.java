package com.marketplace.favoritos.domain.port.out;

import com.marketplace.favoritos.domain.model.Favorito;
import java.util.List;

/** Puerto de SALIDA del subsistema Favoritos (patron Repository). */
public interface FavoritoRepositoryPort {
    List<Favorito> buscarPorCliente(Long idCliente);
    boolean existe(Long idCliente, Long idProfesional);
    Favorito guardar(Favorito favorito);
    void eliminar(Long idCliente, Long idProfesional);
}
