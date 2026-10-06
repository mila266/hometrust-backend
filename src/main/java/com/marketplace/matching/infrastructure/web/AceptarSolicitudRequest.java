package com.marketplace.matching.infrastructure.web;

/** DTO de entrada para que un tecnico acepte una solicitud con tarifa acordada. */
public record AceptarSolicitudRequest(Long idProfesional, double tarifaAcordada) {}
