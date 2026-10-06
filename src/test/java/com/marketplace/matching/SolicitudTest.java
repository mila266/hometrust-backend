package com.marketplace.matching;

import com.marketplace.matching.domain.model.Solicitud;
import com.marketplace.shared.domain.EstadoSolicitud;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba del dominio Solicitud SIN base de datos ni Spring (RNF-07).
 * Verifica la maquina de estados (patron State) y sus guardas.
 */
class SolicitudTest {

    @Test
    void flujoCompletoCreadaHastaCalificada() {
        Solicitud s = Solicitud.crear(1L, "Fuga de agua en cocina", "San Miguel", "NORMAL");
        assertEquals(EstadoSolicitud.CREADA, s.getEstado());

        s.publicar();
        assertEquals(EstadoSolicitud.PUBLICADA, s.getEstado());

        s.aceptar(10L, 80.0);
        assertEquals(EstadoSolicitud.ACEPTADA, s.getEstado());
        assertEquals(10L, s.getIdProfesional());
        assertEquals(80.0, s.getTarifaAcordada());

        s.iniciar();
        assertEquals(EstadoSolicitud.EN_CURSO, s.getEstado());

        s.finalizar();
        assertEquals(EstadoSolicitud.FINALIZADA, s.getEstado());

        s.marcarCalificada();
        assertEquals(EstadoSolicitud.CALIFICADA, s.getEstado());
    }

    @Test
    void noSePuedeIniciarSinAceptar() {
        Solicitud s = Solicitud.crear(1L, "Cambio de cerradura", "Magdalena", "NORMAL");
        assertThrows(IllegalStateException.class, s::iniciar);
    }

    @Test
    void noSePuedeAceptarConTarifaInvalida() {
        Solicitud s = Solicitud.crear(1L, "Instalacion electrica", "Brena", "NORMAL");
        assertThrows(IllegalArgumentException.class, () -> s.aceptar(10L, 0));
    }

    @Test
    void noSePuedeCancelarUnServicioEnCurso() {
        Solicitud s = Solicitud.crear(1L, "Reparacion", "Lince", "NORMAL");
        s.aceptar(10L, 50.0);
        s.iniciar();
        assertThrows(IllegalStateException.class, s::cancelar);
    }

    @Test
    void laEmergenciaSeReconocePorElTipo() {
        Solicitud normal = Solicitud.crear(1L, "x", "y", "NORMAL");
        Solicitud urgente = Solicitud.crear(1L, "x", "y", "EMERGENCIA");
        assertFalse(normal.esEmergencia());
        assertTrue(urgente.esEmergencia());
    }
}
