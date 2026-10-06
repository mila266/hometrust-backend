package com.marketplace.matching.domain.strategy;

import java.util.Comparator;
import java.util.List;

/**
 * Strategy para solicitudes NORMALES: prioriza la confianza.
 * Ordena por reputacion promedio (desc) y, a igualdad, por experiencia
 * (total de servicios, desc). Responde al problema central del proyecto:
 * dar seguridad al cliente mostrando primero a los tecnicos mejor valorados.
 */
public class EmparejamientoPorReputacion implements EstrategiaEmparejamiento {

    @Override
    public List<CandidatoProfesional> ordenar(List<CandidatoProfesional> candidatos) {
        return candidatos.stream()
                .sorted(Comparator
                        .comparingDouble(CandidatoProfesional::reputacionPromedio).reversed()
                        .thenComparing(Comparator.comparingInt(CandidatoProfesional::totalServicios).reversed()))
                .toList();
    }
}
