package com.marketplace.reputacion.domain.port.out;

import com.marketplace.reputacion.domain.model.Calificacion;
import com.marketplace.reputacion.domain.model.Reputacion;
import java.util.Optional;

/**
 * Puerto de SALIDA del subsistema Reputacion (patron Repository).
 */
public interface ReputacionRepositoryPort {

    Calificacion guardarCalificacion(Calificacion calificacion);

    Optional<Reputacion> buscarPorProfesional(Long idProfesional);

    Reputacion guardarReputacion(Reputacion reputacion);
}
