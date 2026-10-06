package com.marketplace.notificaciones.domain.port.out;

/**
 * Puerto de salida hacia el servicio de notificaciones (Email/SMS).
 * Adaptador actual: NotificadorLogAdapter (simulado, escribe en el log).
 * Siguiente sprint: adaptador real de correo/SMS sin tocar el resto del sistema.
 */
public interface NotificadorPort {
    void enviar(String destinatario, String mensaje);
}
