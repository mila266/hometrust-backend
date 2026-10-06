package com.marketplace.perfiles.infrastructure.web;

import com.marketplace.perfiles.domain.model.Profesional;
import com.marketplace.perfiles.domain.port.in.GestionarProfesionalUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Adaptador de ENTRADA: expone el caso de uso como API REST.
 * Depende del puerto de entrada (interfaz), no del servicio concreto.
 */
@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalController {

    private final GestionarProfesionalUseCase useCase;

    public ProfesionalController(GestionarProfesionalUseCase useCase) {
        this.useCase = useCase;
    }

    // POST /api/profesionales  -> Registrar Profesional (RF-01)
    @PostMapping
    public ResponseEntity<ProfesionalResponse> registrar(@RequestBody RegistrarProfesionalRequest req) {
        Profesional p = useCase.registrar(req.nombre(), req.ubicacion());
        return ResponseEntity.ok(ProfesionalResponse.desde(p));
    }

    // GET /api/profesionales/activos -> Panel del cliente (RF-07): solo ACTIVOS
    @GetMapping("/activos")
    public List<ProfesionalResponse> listarActivos() {
        return useCase.listarActivos().stream().map(ProfesionalResponse::desde).toList();
    }

    // GET /api/profesionales -> Panel de administracion: todos los tecnicos y su estado
    @GetMapping
    public List<ProfesionalResponse> listarTodos() {
        return useCase.listarTodos().stream().map(ProfesionalResponse::desde).toList();
    }

    // GET /api/profesionales/{id}
    @GetMapping("/{id}")
    public ProfesionalResponse obtener(@PathVariable Long id) {
        return ProfesionalResponse.desde(useCase.obtener(id));
    }

    // ---- transiciones de verificacion (RF-02, RF-03, RF-04) ----
    @PostMapping("/{id}/enviar-documentos")
    public ProfesionalResponse enviarDocumentos(@PathVariable Long id) {
        return ProfesionalResponse.desde(useCase.enviarDocumentos(id));
    }

    @PostMapping("/{id}/revisar")
    public ProfesionalResponse revisar(@PathVariable Long id) {
        return ProfesionalResponse.desde(useCase.tomarEnRevision(id));
    }

    @PostMapping("/{id}/aprobar")
    public ProfesionalResponse aprobar(@PathVariable Long id) {
        return ProfesionalResponse.desde(useCase.aprobar(id));
    }

    @PostMapping("/{id}/rechazar")
    public ProfesionalResponse rechazar(@PathVariable Long id) {
        return ProfesionalResponse.desde(useCase.rechazar(id));
    }

    // ---- otras transiciones de la maquina de estados ----
    @PostMapping("/{id}/doc-incompleta")
    public ProfesionalResponse docIncompleta(@PathVariable Long id) {
        return ProfesionalResponse.desde(useCase.marcarDocumentacionIncompleta(id));
    }

    @PostMapping("/{id}/suspender")
    public ProfesionalResponse suspender(@PathVariable Long id) {
        return ProfesionalResponse.desde(useCase.suspender(id));
    }

    @PostMapping("/{id}/reactivar")
    public ProfesionalResponse reactivar(@PathVariable Long id) {
        return ProfesionalResponse.desde(useCase.reactivar(id));
    }
}
