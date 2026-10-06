package com.marketplace.reputacion.infrastructure.web;

import com.marketplace.reputacion.domain.port.in.CalificarServicioUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Adaptador de ENTRADA: expone el subsistema Reputacion como API REST.
 */
@RestController
@RequestMapping("/api/reputacion")
public class ReputacionController {

    private final CalificarServicioUseCase useCase;

    public ReputacionController(CalificarServicioUseCase useCase) {
        this.useCase = useCase;
    }

    // POST /api/reputacion/calificar -> Calificar servicio (RF-18) y recalcular (RF-19)
    @PostMapping("/calificar")
    public ResponseEntity<ReputacionResponse> calificar(@RequestBody CalificarRequest req) {
        var r = useCase.calificar(req.idSolicitud(), req.idProfesional(), req.puntaje(), req.comentario());
        return ResponseEntity.ok(ReputacionResponse.desde(r));
    }

    // GET /api/reputacion/{idProfesional} -> consultar reputacion (RF-20)
    @GetMapping("/{idProfesional}")
    public ReputacionResponse consultar(@PathVariable Long idProfesional) {
        return ReputacionResponse.desde(useCase.consultar(idProfesional));
    }
}
