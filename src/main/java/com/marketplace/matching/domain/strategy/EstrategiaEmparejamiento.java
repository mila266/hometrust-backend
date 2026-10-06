package com.marketplace.matching.domain.strategy;

import java.util.List;

/**
 * Patron Strategy: define como se ordenan los tecnicos candidatos para una
 * solicitud. Permite intercambiar el criterio de emparejamiento sin tocar el
 * servicio de aplicacion (cumple Abierto/Cerrado).
 *
 * Implementaciones:
 *   - EmparejamientoPorReputacion : solicitudes NORMALES (prioriza confianza).
 *   - EmparejamientoPorCercania   : EMERGENCIAS (prioriza tiempo de llegada).
 */
public interface EstrategiaEmparejamiento {

    /** Devuelve los candidatos ordenados del mejor al peor segun el criterio. */
    List<CandidatoProfesional> ordenar(List<CandidatoProfesional> candidatos);
}
