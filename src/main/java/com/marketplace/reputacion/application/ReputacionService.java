package com.marketplace.reputacion.application;

import com.marketplace.reputacion.domain.model.Calificacion;
import com.marketplace.reputacion.domain.model.Reputacion;
import com.marketplace.reputacion.domain.port.in.CalificarServicioUseCase;
import com.marketplace.reputacion.domain.port.out.ReputacionRepositoryPort;
import com.marketplace.shared.application.port.out.PublicadorEventosPort;
import com.marketplace.shared.domain.event.ServicioCalificado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de aplicacion del subsistema Reputacion.
 * Coordina dos pasos dentro de una misma transaccion: registrar la calificacion
 * y recalcular el promedio del tecnico. Si el tecnico aun no tiene reputacion,
 * se crea una inicial (Factory Method del dominio). Al terminar publica el evento
 * ServicioCalificado para que Matching cierre la solicitud (Observer).
 */
@Service
public class ReputacionService implements CalificarServicioUseCase {

    private final ReputacionRepositoryPort repositorio;
    private final PublicadorEventosPort eventos;

    public ReputacionService(ReputacionRepositoryPort repositorio, PublicadorEventosPort eventos) {
        this.repositorio = repositorio;
        this.eventos = eventos;
    }

    @Override
    @Transactional
    public Reputacion calificar(Long idSolicitud, Long idProfesional, int puntaje, String comentario) {
        // 1) Persistir la calificacion del servicio (RF-18).
        Calificacion calificacion = Calificacion.registrar(idSolicitud, puntaje, comentario);
        repositorio.guardarCalificacion(calificacion);

        // 2) Recalcular la reputacion del tecnico (RF-19).
        Reputacion reputacion = repositorio.buscarPorProfesional(idProfesional)
                .orElseGet(() -> Reputacion.inicial(idProfesional));
        reputacion.agregarCalificacion(puntaje);
        Reputacion guardada = repositorio.guardarReputacion(reputacion);

        // 3) Avisar a Matching que cierre la solicitud (Observer).
        eventos.publicar(ServicioCalificado.de(idSolicitud, idProfesional, puntaje));
        return guardada;
    }

    @Override
    public Reputacion consultar(Long idProfesional) {
        return repositorio.buscarPorProfesional(idProfesional)
                .orElseGet(() -> Reputacion.inicial(idProfesional));
    }
}
