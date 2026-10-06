package com.marketplace.pagos.infrastructure;

import com.marketplace.pagos.domain.port.out.PagoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Adaptador de SALIDA del puerto PagoPort, implementado como FACADE.
 *
 * Una pasarela de pago real exige varios pasos (validar, autorizar, capturar,
 * registrar). Esta fachada los agrupa tras una unica operacion procesarPago(),
 * de modo que el resto del sistema no conoce la complejidad de la pasarela.
 * Aqui los pasos estan simulados (se registran en el log).
 */
@Component
public class PasarelaPagoFacadeAdapter implements PagoPort {

    private static final Logger log = LoggerFactory.getLogger(PasarelaPagoFacadeAdapter.class);

    @Override
    public void procesarPago(Long idSolicitud, double monto) {
        validar(idSolicitud, monto);
        String token = autorizar(monto);
        capturar(token, monto);
        registrar(idSolicitud, monto);
        log.info("[PAGO] Solicitud #{} cobrada por S/ {} (pasarela simulada)", idSolicitud, monto);
    }

    // ---- pasos internos que la fachada oculta ----
    private void validar(Long idSolicitud, double monto) {
        if (idSolicitud == null || monto <= 0)
            throw new IllegalArgumentException("Datos de pago invalidos para la solicitud " + idSolicitud);
    }

    private String autorizar(double monto) {
        return "AUTH-" + Math.abs(Double.hashCode(monto));
    }

    private void capturar(String token, double monto) {
        log.debug("[PAGO] captura {} por S/ {}", token, monto);
    }

    private void registrar(Long idSolicitud, double monto) {
        log.debug("[PAGO] registro contable de la solicitud #{}", idSolicitud);
    }
}
