package com.marketplace.matching.domain.strategy;

/**
 * Objeto de valor con los datos minimos que el emparejamiento necesita de un
 * tecnico candidato. Evita que el subsistema Matching dependa de las entidades
 * internas de Perfiles o Reputacion (bajo acoplamiento): recibe solo este
 * resumen y devuelve un orden.
 */
public record CandidatoProfesional(
        Long idProfesional,
        String nombre,
        double reputacionPromedio,
        int totalServicios,
        double distanciaKm
) {}
