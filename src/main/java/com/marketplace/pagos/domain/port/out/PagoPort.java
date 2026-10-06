package com.marketplace.pagos.domain.port.out;

/**
 * Puerto de salida hacia la pasarela de pago externa (patron Facade).
 * Adaptador actual: PasarelaPagoFacadeAdapter (simulado, agrupa los pasos).
 * Siguiente sprint: conectar la pasarela real (Culqui, Mercado Pago, etc.).
 */
public interface PagoPort {
    void procesarPago(Long idSolicitud, double monto);
}
