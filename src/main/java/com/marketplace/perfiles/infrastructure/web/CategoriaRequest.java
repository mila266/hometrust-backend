package com.marketplace.perfiles.infrastructure.web;

/** DTO de entrada para crear o actualizar una categoria de servicio (Admin). */
public record CategoriaRequest(String nombre, String oficio, double tarifaBaseReferencial) {}
