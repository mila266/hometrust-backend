package com.marketplace.reputacion.domain.port.in;

import com.marketplace.reputacion.domain.model.Reputacion;

/**
 * Puerto de ENTRADA del subsistema Reputacion.
 * Calificar servicio (RF-18) y consultar la reputacion de un tecnico (RF-20).
 */
public interface CalificarServicioUseCase {

    /**
     * Registra la calificacion de una solicitud finalizada y actualiza la
     * reputacion del tecnico. Devuelve la reputacion recalculada.
     */
    Reputacion calificar(Long idSolicitud, Long idProfesional, int puntaje, String comentario);

    /** Consulta la reputacion actual de un tecnico. */
    Reputacion consultar(Long idProfesional);
}
