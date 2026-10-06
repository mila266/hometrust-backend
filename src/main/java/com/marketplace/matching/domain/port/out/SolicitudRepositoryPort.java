package com.marketplace.matching.domain.port.out;

import com.marketplace.matching.domain.model.Solicitud;
import com.marketplace.shared.domain.EstadoSolicitud;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de SALIDA del subsistema Matching (patron Repository).
 * El nucleo define que necesita persistir; el adaptador JPA decide el como.
 */
public interface SolicitudRepositoryPort {

    Solicitud guardar(Solicitud solicitud);

    Optional<Solicitud> buscarPorId(Long id);

    List<Solicitud> buscarPorCliente(Long idCliente);

    List<Solicitud> buscarPorProfesional(Long idProfesional);

    List<Solicitud> buscarPorEstados(List<EstadoSolicitud> estados);
}
