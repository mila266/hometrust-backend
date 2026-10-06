package com.marketplace.perfiles.infrastructure.web;

import java.util.List;

/** DTO de entrada para que el tecnico actualice su perfil y categorias. */
public record ActualizarPerfilRequest(String descripcion, boolean destacado, List<Long> categoriaIds) {}
