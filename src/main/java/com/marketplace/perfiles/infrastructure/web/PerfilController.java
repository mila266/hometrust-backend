package com.marketplace.perfiles.infrastructure.web;

import com.marketplace.perfiles.domain.port.in.GestionarPerfilUseCase;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Adaptador de ENTRADA: perfil del tecnico y catalogo de categorias.
 */
@RestController
public class PerfilController {

    private final GestionarPerfilUseCase useCase;

    public PerfilController(GestionarPerfilUseCase useCase) {
        this.useCase = useCase;
    }

    // GET /api/profesionales/{id}/perfil -> vitrina del tecnico (directorio)
    @GetMapping("/api/profesionales/{id}/perfil")
    public PerfilResponse obtener(@PathVariable Long id) {
        return PerfilResponse.desde(useCase.obtenerPerfil(id));
    }

    // PUT /api/profesionales/{id}/perfil -> Gestionar Perfil y Categorias
    @PutMapping("/api/profesionales/{id}/perfil")
    public PerfilResponse actualizar(@PathVariable Long id, @RequestBody ActualizarPerfilRequest req) {
        return PerfilResponse.desde(useCase.actualizarPerfil(
                id, req.descripcion(), req.destacado(), req.categoriaIds()));
    }
}
