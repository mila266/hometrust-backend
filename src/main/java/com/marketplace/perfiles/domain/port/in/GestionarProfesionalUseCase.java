package com.marketplace.perfiles.domain.port.in;

import com.marketplace.perfiles.domain.model.Profesional;
import java.util.List;

/**
 * Puerto de ENTRADA del subsistema Perfiles.
 * Define QUE puede hacer el sistema, sin decir COMO.
 * Corresponde a los casos de uso: Registrar Profesional, Verificar Documentos.
 */
public interface GestionarProfesionalUseCase {
    Profesional registrar(String nombre, String ubicacion);
    Profesional enviarDocumentos(Long idProfesional);
    Profesional tomarEnRevision(Long idProfesional);
    Profesional aprobar(Long idProfesional);
    Profesional rechazar(Long idProfesional);
    Profesional marcarDocumentacionIncompleta(Long idProfesional);
    Profesional suspender(Long idProfesional);
    Profesional reactivar(Long idProfesional);
    List<Profesional> listarActivos();
    /** Todos los tecnicos, en cualquier estado de verificacion (panel de administracion). */
    List<Profesional> listarTodos();
    Profesional obtener(Long idProfesional);
}
