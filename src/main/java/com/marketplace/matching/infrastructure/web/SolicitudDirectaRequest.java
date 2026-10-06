package com.marketplace.matching.infrastructure.web;

/** DTO de entrada para la via "Profesional de confianza" (envio directo a un favorito). */
public record SolicitudDirectaRequest(
        Long idCliente, String descripcion, String ubicacion, Long idProfesional, double tarifaAcordada) {}
