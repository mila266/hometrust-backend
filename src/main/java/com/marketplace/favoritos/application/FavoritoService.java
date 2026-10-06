package com.marketplace.favoritos.application;

import com.marketplace.favoritos.domain.model.Favorito;
import com.marketplace.favoritos.domain.port.in.GestionarFavoritosUseCase;
import com.marketplace.favoritos.domain.port.out.FavoritoRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;

/** Servicio de aplicacion del subsistema Favoritos. */
@Service
public class FavoritoService implements GestionarFavoritosUseCase {

    private final FavoritoRepositoryPort repositorio;

    public FavoritoService(FavoritoRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Long> listar(Long idCliente) {
        return repositorio.buscarPorCliente(idCliente).stream().map(Favorito::getIdProfesional).toList();
    }

    @Override
    public void agregar(Long idCliente, Long idProfesional) {
        if (!repositorio.existe(idCliente, idProfesional)) {
            repositorio.guardar(Favorito.crear(idCliente, idProfesional));
        }
    }

    @Override
    public void quitar(Long idCliente, Long idProfesional) {
        repositorio.eliminar(idCliente, idProfesional);
    }
}
