package com.marketplace.reputacion;

import com.marketplace.reputacion.domain.model.Reputacion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba del dominio Reputacion (dominio puro): el promedio se recalcula
 * correctamente al agregar calificaciones (RF-19).
 */
class ReputacionTest {

    @Test
    void promedioInicialEsCero() {
        Reputacion r = Reputacion.inicial(1L);
        assertEquals(0.0, r.getCalificacionPromedio());
        assertEquals(0, r.getTotalServicios());
    }

    @Test
    void recalculaElPromedioAlAgregarCalificaciones() {
        Reputacion r = Reputacion.inicial(1L);
        r.agregarCalificacion(5);
        r.agregarCalificacion(4);
        r.agregarCalificacion(3);
        assertEquals(3, r.getTotalServicios());
        assertEquals(4.0, r.getCalificacionPromedio()); // (5+4+3)/3
    }

    @Test
    void rechazaPuntajeFueraDeRango() {
        Reputacion r = Reputacion.inicial(1L);
        assertThrows(IllegalArgumentException.class, () -> r.agregarCalificacion(6));
    }
}
