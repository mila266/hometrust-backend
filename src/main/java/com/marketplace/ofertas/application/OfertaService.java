package com.marketplace.ofertas.application;

import com.marketplace.matching.domain.port.in.SolicitarServicioUseCase;
import com.marketplace.ofertas.domain.model.Oferta;
import com.marketplace.ofertas.domain.port.in.GestionarOfertasUseCase;
import com.marketplace.ofertas.domain.port.out.OfertaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Servicio de aplicacion del subsistema Ofertas (flujo de emergencia).
 * Al aceptar una oferta coordina: marcar esa oferta ACEPTADA, rechazar las demas
 * de la misma solicitud, y pedir a Matching que ponga la solicitud en ACEPTADA
 * con el tecnico y la tarifa de la oferta ganadora. Depende del puerto de entrada
 * de Matching, no de su implementacion.
 */
@Service
public class OfertaService implements GestionarOfertasUseCase {

    private final OfertaRepositoryPort repositorio;
    private final SolicitarServicioUseCase matching;

    public OfertaService(OfertaRepositoryPort repositorio, SolicitarServicioUseCase matching) {
        this.repositorio = repositorio;
        this.matching = matching;
    }

    @Override
    public Oferta enviar(Long idSolicitud, Long idProfesional, double tarifaPropuesta,
                         int tiempoEstimadoLlegada, String ubicacionActual) {
        Oferta oferta = Oferta.enviar(idSolicitud, idProfesional, tarifaPropuesta,
                tiempoEstimadoLlegada, ubicacionActual);
        return repositorio.guardar(oferta);
    }

    @Override
    public List<Oferta> listarPorSolicitud(Long idSolicitud) {
        return repositorio.buscarPorSolicitud(idSolicitud);
    }

    @Override
    public List<Oferta> listarPorProfesional(Long idProfesional) {
        return repositorio.buscarPorProfesional(idProfesional);
    }

    @Override
    @Transactional
    public Oferta aceptar(Long idOferta) {
        Oferta elegida = repositorio.buscarPorId(idOferta)
                .orElseThrow(() -> new IllegalArgumentException("Oferta no encontrada: " + idOferta));

        // 1) La solicitud pasa a ACEPTADA con el tecnico y la tarifa de la oferta.
        matching.aceptar(elegida.getIdSolicitud(), elegida.getIdProfesional(), elegida.getTarifaPropuesta());

        // 2) Esta oferta queda ACEPTADA; las demas de la misma solicitud, RECHAZADAS.
        elegida.aceptar();
        repositorio.guardar(elegida);
        for (Oferta otra : repositorio.buscarPorSolicitud(elegida.getIdSolicitud())) {
            if (!otra.getId().equals(elegida.getId())) {
                otra.rechazar();
                repositorio.guardar(otra);
            }
        }
        return elegida;
    }
}
