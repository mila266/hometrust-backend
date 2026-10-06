package com.marketplace.perfiles.infrastructure.web;

import com.marketplace.perfiles.domain.model.Documento;
import com.marketplace.perfiles.domain.port.in.GestionarDocumentosUseCase;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** Adaptador de ENTRADA: documentos del profesional (Subir Documentos). */
@RestController
@RequestMapping("/api/profesionales/{idProfesional}/documentos")
public class DocumentoController {

    private final GestionarDocumentosUseCase useCase;

    public DocumentoController(GestionarDocumentosUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public DocumentoResponse subir(@PathVariable Long idProfesional, @RequestBody SubirDocumentoRequest req) {
        return DocumentoResponse.desde(useCase.subir(idProfesional, req.tipo(), req.url()));
    }

    @GetMapping
    public List<DocumentoResponse> listar(@PathVariable Long idProfesional) {
        return useCase.listar(idProfesional).stream().map(DocumentoResponse::desde).toList();
    }
}
