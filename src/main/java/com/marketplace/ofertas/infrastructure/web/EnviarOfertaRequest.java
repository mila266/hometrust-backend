package com.marketplace.ofertas.infrastructure.web;

/** DTO de entrada: un tecnico envia una oferta ante una emergencia. */
public record EnviarOfertaRequest(
        Long idSolicitud, Long idProfesional, double tarifaPropuesta,
        int tiempoEstimadoLlegada, String ubicacionActual) {}
