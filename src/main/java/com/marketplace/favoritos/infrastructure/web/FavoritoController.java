package com.marketplace.favoritos.infrastructure.web;

import com.marketplace.favoritos.domain.port.in.GestionarFavoritosUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** Adaptador de ENTRADA: favoritos del cliente. */
@RestController
@RequestMapping("/api/clientes/{idCliente}/favoritos")
public class FavoritoController {

    private final GestionarFavoritosUseCase useCase;

    public FavoritoController(GestionarFavoritosUseCase useCase) {
        this.useCase = useCase;
    }

    // GET -> ids de los tecnicos favoritos del cliente
    @GetMapping
    public List<Long> listar(@PathVariable Long idCliente) {
        return useCase.listar(idCliente);
    }

    @PostMapping("/{idProfesional}")
    public ResponseEntity<Void> agregar(@PathVariable Long idCliente, @PathVariable Long idProfesional) {
        useCase.agregar(idCliente, idProfesional);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idProfesional}")
    public ResponseEntity<Void> quitar(@PathVariable Long idCliente, @PathVariable Long idProfesional) {
        useCase.quitar(idCliente, idProfesional);
        return ResponseEntity.noContent().build();
    }
}
