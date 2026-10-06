package com.marketplace.shared.infrastructure.event;

import com.marketplace.shared.application.port.out.PublicadorEventosPort;
import com.marketplace.shared.domain.event.EventoDominio;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Adaptador de SALIDA del puerto PublicadorEventosPort.
 *
 * Implementa el mecanismo Observer apoyandose en el bus de eventos de Spring
 * (ApplicationEventPublisher). Los consumidores son componentes anotados con
 * @EventListener en los subsistemas Notificaciones y Pagos. Si manana se quisiera
 * usar una cola externa (RabbitMQ, Kafka), solo se reemplaza este adaptador.
 */
@Component
public class PublicadorEventosSpring implements PublicadorEventosPort {

    private final ApplicationEventPublisher publisher;

    public PublicadorEventosSpring(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publicar(EventoDominio evento) {
        publisher.publishEvent(evento);
    }
}
