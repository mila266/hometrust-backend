package com.marketplace.matching.domain.strategy;

import java.util.Comparator;
import java.util.List;

/**
 * Strategy para EMERGENCIAS: prioriza el tiempo de llegada.
 * Ordena por distancia (asc) y, a igualdad, por reputacion (desc) para no
 * sacrificar del todo la confianza. Responde al segundo pilar del problema:
 * acceso rapido ante urgencias.
 */
public class EmparejamientoPorCercania implements EstrategiaEmparejamiento {

    @Override
    public List<CandidatoProfesional> ordenar(List<CandidatoProfesional> candidatos) {
        return candidatos.stream()
                .sorted(Comparator
                        .comparingDouble(CandidatoProfesional::distanciaKm)
                        .thenComparing(Comparator.comparingDouble(CandidatoProfesional::reputacionPromedio).reversed()))
                .toList();
    }
}
