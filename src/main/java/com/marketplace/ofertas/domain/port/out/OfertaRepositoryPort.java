package com.marketplace.ofertas.domain.port.out;

import com.marketplace.ofertas.domain.model.Oferta;
import java.util.List;
import java.util.Optional;

/** Puerto de SALIDA del subsistema Ofertas (patron Repository). */
public interface OfertaRepositoryPort {
    Oferta guardar(Oferta oferta);
    Optional<Oferta> buscarPorId(Long id);
    List<Oferta> buscarPorSolicitud(Long idSolicitud);
    List<Oferta> buscarPorProfesional(Long idProfesional);
}
