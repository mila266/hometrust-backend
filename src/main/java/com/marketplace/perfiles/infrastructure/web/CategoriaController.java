package com.marketplace.perfiles.infrastructure.web;

import com.marketplace.perfiles.domain.port.in.GestionarCategoriasUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Adaptador de ENTRADA: catalogo de categorias de servicio.
 * GET es publico (lo usan cliente y tecnico); crear/editar/eliminar es del Admin.
 */
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final GestionarCategoriasUseCase useCase;

    public CategoriaController(GestionarCategoriasUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<CategoriaResponse> listar() {
        return useCase.listar().stream().map(CategoriaResponse::desde).toList();
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crear(@RequestBody CategoriaRequest req) {
        var c = useCase.crear(req.nombre(), req.oficio(), req.tarifaBaseReferencial());
        return ResponseEntity.ok(CategoriaResponse.desde(c));
    }

    @PutMapping("/{id}")
    public CategoriaResponse actualizar(@PathVariable Long id, @RequestBody CategoriaRequest req) {
        return CategoriaResponse.desde(useCase.actualizar(id, req.nombre(), req.oficio(), req.tarifaBaseReferencial()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        useCase.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
