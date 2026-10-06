package com.marketplace.matching;

import com.marketplace.matching.domain.strategy.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba del patron Strategy de emparejamiento (dominio puro).
 */
class EmparejamientoTest {

    // a: lejos pero muy bien calificado | b: cerca pero menor reputacion
    private final CandidatoProfesional a = new CandidatoProfesional(1L, "Carlos", 4.9, 60, 8.0);
    private final CandidatoProfesional b = new CandidatoProfesional(2L, "Lucia", 4.2, 20, 1.5);

    @Test
    void normalPriorizaReputacion() {
        EstrategiaEmparejamiento estrategia = new EmparejamientoPorReputacion();
        List<CandidatoProfesional> orden = estrategia.ordenar(List.of(b, a));
        assertEquals(1L, orden.get(0).idProfesional(), "El mejor calificado debe ir primero");
    }

    @Test
    void emergenciaPriorizaCercania() {
        EstrategiaEmparejamiento estrategia = new EmparejamientoPorCercania();
        List<CandidatoProfesional> orden = estrategia.ordenar(List.of(a, b));
        assertEquals(2L, orden.get(0).idProfesional(), "El mas cercano debe ir primero");
    }
}
