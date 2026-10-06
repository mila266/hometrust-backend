package com.marketplace.reputacion.infrastructure.web;

/** DTO de entrada para calificar un servicio finalizado. */
public record CalificarRequest(Long idSolicitud, Long idProfesional, int puntaje, String comentario) {}
