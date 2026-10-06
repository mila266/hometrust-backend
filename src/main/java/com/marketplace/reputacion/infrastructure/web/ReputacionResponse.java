package com.marketplace.reputacion.infrastructure.web;

import com.marketplace.reputacion.domain.model.Reputacion;

/** DTO de salida con la reputacion de un tecnico. */
public record ReputacionResponse(Long idProfesional, double calificacionPromedio, int totalServicios) {
    public static ReputacionResponse desde(Reputacion r) {
        return new ReputacionResponse(r.getIdProfesional(), r.getCalificacionPromedio(), r.getTotalServicios());
    }
}
