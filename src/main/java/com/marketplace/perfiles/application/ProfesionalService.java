package com.marketplace.perfiles.application;

import com.marketplace.perfiles.domain.model.Profesional;
import com.marketplace.perfiles.domain.port.in.GestionarProfesionalUseCase;
import com.marketplace.perfiles.domain.port.out.ProfesionalRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio de aplicacion: orquesta el caso de uso.
 * Depende SOLO del puerto de salida (interfaz), nunca de la implementacion concreta.
 * Esto permite probarlo con un mock del repositorio (cumple RNF-07: testabilidad).
 */
@Service
public class ProfesionalService implements GestionarProfesionalUseCase {

    private final ProfesionalRepositoryPort repositorio;

    public ProfesionalService(ProfesionalRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Profesional registrar(String nombre, String ubicacion) {
        Profesional p = Profesional.registrar(nombre, ubicacion);
        return repositorio.guardar(p);
    }

    @Override
    public Profesional enviarDocumentos(Long id) {
        Profesional p = obtener(id);
        p.enviarDocumentos();
        return repositorio.guardar(p);
    }

    @Override
    public Profesional tomarEnRevision(Long id) {
        Profesional p = obtener(id);
        p.tomarEnRevision();
        return repositorio.guardar(p);
    }

    @Override
    public Profesional aprobar(Long id) {
        Profesional p = obtener(id);
        p.aprobar();
        return repositorio.guardar(p);
    }

    @Override
    public Profesional rechazar(Long id) {
        Profesional p = obtener(id);
        p.rechazar();
        return repositorio.guardar(p);
    }

    @Override
    public Profesional marcarDocumentacionIncompleta(Long id) {
        Profesional p = obtener(id);
        p.marcarDocumentacionIncompleta();
        return repositorio.guardar(p);
    }

    @Override
    public Profesional suspender(Long id) {
        Profesional p = obtener(id);
        p.suspender();
        return repositorio.guardar(p);
    }

    @Override
    public Profesional reactivar(Long id) {
        Profesional p = obtener(id);
        p.reactivar();
        return repositorio.guardar(p);
    }

    @Override
    public List<Profesional> listarActivos() {
        return repositorio.buscarActivos();
    }

    @Override
    public List<Profesional> listarTodos() {
        return repositorio.buscarTodos();
    }

    @Override
    public Profesional obtener(Long id) {
        return repositorio.buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Profesional no encontrado: " + id));
    }
}
