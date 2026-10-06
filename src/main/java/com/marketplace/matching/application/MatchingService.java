package com.marketplace.matching.application;

import com.marketplace.matching.domain.model.Solicitud;
import com.marketplace.matching.domain.port.in.SolicitarServicioUseCase;
import com.marketplace.matching.domain.port.out.SolicitudRepositoryPort;
import com.marketplace.matching.domain.strategy.CandidatoProfesional;
import com.marketplace.matching.domain.strategy.EmparejamientoPorCercania;
import com.marketplace.matching.domain.strategy.EmparejamientoPorReputacion;
import com.marketplace.matching.domain.strategy.EstrategiaEmparejamiento;
import com.marketplace.shared.application.port.out.PublicadorEventosPort;
import com.marketplace.shared.domain.EstadoSolicitud;
import com.marketplace.shared.domain.event.ServicioFinalizado;
import com.marketplace.shared.domain.event.SolicitudAceptada;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio de aplicacion del subsistema Matching.
 * Orquesta los casos de uso delegando las reglas al dominio (Solicitud) y la
 * persistencia al puerto de salida. Publica eventos de dominio (Observer) en
 * lugar de llamar directamente a Pagos o Notificaciones: asi Matching no depende
 * de esos subsistemas.
 */
@Service
public class MatchingService implements SolicitarServicioUseCase {

    private final SolicitudRepositoryPort repositorio;
    private final PublicadorEventosPort eventos;

    public MatchingService(SolicitudRepositoryPort repositorio, PublicadorEventosPort eventos) {
        this.repositorio = repositorio;
        this.eventos = eventos;
    }

    @Override
    public Solicitud crear(Long idCliente, String descripcion, String ubicacion, String tipo) {
        Solicitud s = Solicitud.crear(idCliente, descripcion, ubicacion, tipo);
        return repositorio.guardar(s);
    }

    @Override
    public Solicitud solicitarDirecto(Long idCliente, String descripcion, String ubicacion,
                                      Long idProfesional, double tarifaAcordada) {
        Solicitud s = Solicitud.crear(idCliente, descripcion, ubicacion, "NORMAL");
        s.aceptar(idProfesional, tarifaAcordada);   // Creada -> Aceptada (envio directo)
        Solicitud guardada = repositorio.guardar(s);
        eventos.publicar(SolicitudAceptada.de(
                guardada.getId(), guardada.getIdCliente(), guardada.getIdProfesional()));
        return guardada;
    }

    @Override
    public Solicitud publicar(Long idSolicitud) {
        Solicitud s = obtener(idSolicitud);
        s.publicar();
        return repositorio.guardar(s);
    }

    @Override
    public Solicitud aceptar(Long idSolicitud, Long idProfesional, double tarifaAcordada) {
        Solicitud s = obtener(idSolicitud);
        s.aceptar(idProfesional, tarifaAcordada);
        Solicitud guardada = repositorio.guardar(s);
        eventos.publicar(SolicitudAceptada.de(
                guardada.getId(), guardada.getIdCliente(), guardada.getIdProfesional()));
        return guardada;
    }

    @Override
    public Solicitud iniciar(Long idSolicitud) {
        Solicitud s = obtener(idSolicitud);
        s.iniciar();
        return repositorio.guardar(s);
    }

    @Override
    public Solicitud finalizar(Long idSolicitud) {
        Solicitud s = obtener(idSolicitud);
        s.finalizar();
        Solicitud guardada = repositorio.guardar(s);
        eventos.publicar(ServicioFinalizado.de(
                guardada.getId(), guardada.getIdCliente(),
                guardada.getIdProfesional(), guardada.getTarifaAcordada()));
        return guardada;
    }

    @Override
    public Solicitud marcarCalificada(Long idSolicitud) {
        Solicitud s = obtener(idSolicitud);
        s.marcarCalificada();
        return repositorio.guardar(s);
    }

    @Override
    public Solicitud cancelar(Long idSolicitud) {
        Solicitud s = obtener(idSolicitud);
        s.cancelar();
        return repositorio.guardar(s);
    }

    @Override
    public Solicitud obtener(Long idSolicitud) {
        return repositorio.buscarPorId(idSolicitud)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada: " + idSolicitud));
    }

    @Override
    public List<Solicitud> listarPorCliente(Long idCliente) {
        return repositorio.buscarPorCliente(idCliente);
    }

    @Override
    public List<Solicitud> listarPorProfesional(Long idProfesional) {
        return repositorio.buscarPorProfesional(idProfesional);
    }

    @Override
    public List<Solicitud> listarPendientes() {
        return repositorio.buscarPorEstados(List.of(EstadoSolicitud.CREADA, EstadoSolicitud.PUBLICADA));
    }

    @Override
    public List<CandidatoProfesional> emparejar(Long idSolicitud, List<CandidatoProfesional> candidatos) {
        Solicitud s = obtener(idSolicitud);
        EstrategiaEmparejamiento estrategia = s.esEmergencia()
                ? new EmparejamientoPorCercania()
                : new EmparejamientoPorReputacion();
        return estrategia.ordenar(candidatos);
    }
}
