package com.marketplace.perfiles;

import com.marketplace.perfiles.domain.model.Profesional;
import com.marketplace.shared.domain.EstadoVerificacion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba del dominio SIN base de datos ni Spring (RNF-07: testabilidad).
 * Verifica la maquina de estados de verificacion del Profesional.
 */
class ProfesionalTest {

    @Test
    void flujoCompletoDeVerificacionHastaActivo() {
        Profesional p = Profesional.registrar("Carlos Ramirez", "San Miguel");
        assertEquals(EstadoVerificacion.REGISTRADO, p.getEstado());

        p.enviarDocumentos();
        assertEquals(EstadoVerificacion.DOCUMENTOS_ENVIADOS, p.getEstado());

        p.tomarEnRevision();
        p.aprobar();
        assertEquals(EstadoVerificacion.ACTIVO, p.getEstado());
        assertTrue(p.esVisibleEnPanel(), "Un profesional ACTIVO debe verse en el panel");
    }

    @Test
    void noSePuedeAprobarSinEstarEnRevision() {
        Profesional p = Profesional.registrar("Rosa Delgado", "Brena");
        // intenta aprobar directo desde REGISTRADO -> debe fallar
        assertThrows(IllegalStateException.class, p::aprobar);
    }

    @Test
    void profesionalNoActivoNoApareceEnPanel() {
        Profesional p = Profesional.registrar("Rosa Delgado", "Brena");
        assertFalse(p.esVisibleEnPanel());
    }
}
