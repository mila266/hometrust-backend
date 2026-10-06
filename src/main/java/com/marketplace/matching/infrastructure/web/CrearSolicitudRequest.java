package com.marketplace.matching.infrastructure.web;

/** DTO de entrada para crear una solicitud. tipo = NORMAL | EMERGENCIA. */
public record CrearSolicitudRequest(Long idCliente, String descripcion, String ubicacion, String tipo) {}
