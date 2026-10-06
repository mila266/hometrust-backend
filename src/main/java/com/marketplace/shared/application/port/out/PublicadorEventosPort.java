package com.marketplace.shared.application.port.out;

import com.marketplace.shared.domain.event.EventoDominio;

/**
 * Puerto de SALIDA para publicar eventos de dominio (patron Observer).
 *
 * La capa de aplicacion de Matching depende de esta interfaz, no de Spring.
 * El adaptador concreto (PublicadorEventosSpring) vive en infraestructura, de
 * modo que el nucleo permanece independiente del mecanismo de mensajeria.
 */
public interface PublicadorEventosPort {
    void publicar(EventoDominio evento);
}
