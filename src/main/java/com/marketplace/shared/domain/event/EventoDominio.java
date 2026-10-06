package com.marketplace.shared.domain.event;

import java.time.LocalDateTime;

/**
 * Marca comun de todos los eventos de dominio (patron Observer).
 *
 * Un evento de dominio representa "algo relevante que ya ocurrio" en el nucleo
 * del negocio (una solicitud fue aceptada, un servicio finalizo). Los subsistemas
 * interesados (Notificaciones, Pagos, Reputacion) se suscriben a estos eventos en
 * lugar de ser invocados directamente por Matching. Asi Matching NO conoce a sus
 * consumidores: se cumple bajo acoplamiento y el principio Abierto/Cerrado
 * (agregar un nuevo consumidor no obliga a tocar Matching).
 */
public interface EventoDominio {
    /** Momento en que ocurrio el hecho de negocio. */
    LocalDateTime ocurridoEn();
}
