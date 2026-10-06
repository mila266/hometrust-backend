package com.marketplace.notificaciones.infrastructure;

import com.marketplace.notificaciones.domain.port.out.NotificadorPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Adaptador de SALIDA del puerto NotificadorPort.
 * Implementacion simulada que escribe la notificacion en el log. Para produccion
 * se reemplazaria por un adaptador real (correo SMTP, SMS, push) sin tocar el
 * resto del sistema: esa es la ventaja de depender del puerto y no de la clase.
 */
@Component
public class NotificadorLogAdapter implements NotificadorPort {

    private static final Logger log = LoggerFactory.getLogger(NotificadorLogAdapter.class);

    @Override
    public void enviar(String destinatario, String mensaje) {
        log.info("[NOTIFICACION] para {} -> {}", destinatario, mensaje);
    }
}
