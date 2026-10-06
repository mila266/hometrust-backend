package com.marketplace.ofertas.infrastructure.web;

import com.marketplace.ofertas.domain.port.in.GestionarOfertasUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Adaptador de ENTRADA: expone el subsistema Ofertas (flujo de emergencia) como API REST.
 */
@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    private final GestionarOfertasUseCase useCase;

    public OfertaController(GestionarOfertasUseCase useCase) {
        this.useCase = useCase;
    }

    // POST /api/ofertas -> un tecnico envia una oferta ante una emergencia
    @PostMapping
    public ResponseEntity<OfertaResponse> enviar(@RequestBody EnviarOfertaRequest req) {
        var o = useCase.enviar(req.idSolicitud(), req.idProfesional(), req.tarifaPropuesta(),
                req.tiempoEstimadoLlegada(), req.ubicacionActual());
        return ResponseEntity.ok(OfertaResponse.desde(o));
    }

    // GET /api/ofertas/solicitud/{idSolicitud} -> ofertas que el cliente compara
    @GetMapping("/solicitud/{idSolicitud}")
    public List<OfertaResponse> porSolicitud(@PathVariable Long idSolicitud) {
        return useCase.listarPorSolicitud(idSolicitud).stream().map(OfertaResponse::desde).toList();
    }

    // GET /api/ofertas/profesional/{idProfesional} -> ofertas enviadas por un tecnico
    @GetMapping("/profesional/{idProfesional}")
    public List<OfertaResponse> porProfesional(@PathVariable Long idProfesional) {
        return useCase.listarPorProfesional(idProfesional).stream().map(OfertaResponse::desde).toList();
    }

    // POST /api/ofertas/{id}/aceptar -> el cliente elige una oferta
    @PostMapping("/{id}/aceptar")
    public OfertaResponse aceptar(@PathVariable Long id) {
        return OfertaResponse.desde(useCase.aceptar(id));
    }
}
