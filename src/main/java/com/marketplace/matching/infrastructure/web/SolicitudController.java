package com.marketplace.matching.infrastructure.web;

import com.marketplace.matching.domain.port.in.SolicitarServicioUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Adaptador de ENTRADA: expone el subsistema Matching como API REST.
 * Depende del puerto de entrada (SolicitarServicioUseCase), no del servicio concreto.
 */
@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitarServicioUseCase useCase;

    public SolicitudController(SolicitarServicioUseCase useCase) {
        this.useCase = useCase;
    }

    // POST /api/solicitudes -> Solicitar servicio / emergencia (RF-09 / RF-13)
    @PostMapping
    public ResponseEntity<SolicitudResponse> crear(@RequestBody CrearSolicitudRequest req) {
        var s = useCase.crear(req.idCliente(), req.descripcion(), req.ubicacion(), req.tipo());
        return ResponseEntity.ok(SolicitudResponse.desde(s));
    }

    // POST /api/solicitudes/directa -> via Profesional de confianza (envio directo a favorito)
    @PostMapping("/directa")
    public ResponseEntity<SolicitudResponse> solicitarDirecto(@RequestBody SolicitudDirectaRequest req) {
        var s = useCase.solicitarDirecto(req.idCliente(), req.descripcion(), req.ubicacion(),
                req.idProfesional(), req.tarifaAcordada());
        return ResponseEntity.ok(SolicitudResponse.desde(s));
    }

    // GET /api/solicitudes/pendientes -> solicitudes abiertas que un tecnico puede tomar
    @GetMapping("/pendientes")
    public List<SolicitudResponse> pendientes() {
        return useCase.listarPendientes().stream().map(SolicitudResponse::desde).toList();
    }

    // GET /api/solicitudes/cliente/{idCliente} -> historial del cliente
    @GetMapping("/cliente/{idCliente}")
    public List<SolicitudResponse> porCliente(@PathVariable Long idCliente) {
        return useCase.listarPorCliente(idCliente).stream().map(SolicitudResponse::desde).toList();
    }

    // GET /api/solicitudes/profesional/{idProfesional} -> trabajos del tecnico
    @GetMapping("/profesional/{idProfesional}")
    public List<SolicitudResponse> porProfesional(@PathVariable Long idProfesional) {
        return useCase.listarPorProfesional(idProfesional).stream().map(SolicitudResponse::desde).toList();
    }

    @GetMapping("/{id}")
    public SolicitudResponse obtener(@PathVariable Long id) {
        return SolicitudResponse.desde(useCase.obtener(id));
    }

    // ---- transiciones de la maquina de estados (patron State) ----

    @PostMapping("/{id}/publicar")
    public SolicitudResponse publicar(@PathVariable Long id) {
        return SolicitudResponse.desde(useCase.publicar(id));
    }

    @PostMapping("/{id}/aceptar")
    public SolicitudResponse aceptar(@PathVariable Long id, @RequestBody AceptarSolicitudRequest req) {
        return SolicitudResponse.desde(useCase.aceptar(id, req.idProfesional(), req.tarifaAcordada()));
    }

    @PostMapping("/{id}/iniciar")
    public SolicitudResponse iniciar(@PathVariable Long id) {
        return SolicitudResponse.desde(useCase.iniciar(id));
    }

    @PostMapping("/{id}/finalizar")
    public SolicitudResponse finalizar(@PathVariable Long id) {
        return SolicitudResponse.desde(useCase.finalizar(id));
    }

    @PostMapping("/{id}/cancelar")
    public SolicitudResponse cancelar(@PathVariable Long id) {
        return SolicitudResponse.desde(useCase.cancelar(id));
    }
}
