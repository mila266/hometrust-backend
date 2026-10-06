package com.marketplace.matching.domain.port.in;

import com.marketplace.matching.domain.model.Solicitud;
import com.marketplace.matching.domain.strategy.CandidatoProfesional;
import java.util.List;

/**
 * Puerto de ENTRADA del subsistema Matching: casos de uso que ofrece al exterior.
 * Cubre el ciclo de vida de la solicitud (RF-09 a RF-17) y el emparejamiento.
 */
public interface SolicitarServicioUseCase {

    /** RF-09 Solicitar servicio / RF-13 Solicitar emergencia (segun 'tipo'). */
    Solicitud crear(Long idCliente, String descripcion, String ubicacion, String tipo);

    /**
     * Via "Profesional de confianza": el cliente envia la solicitud DIRECTO a un
     * favorito. Se crea y queda ACEPTADA en un solo paso (Creada -> Aceptada del
     * diagrama de estados), sin pasar por el panel ni por ofertas.
     */
    Solicitud solicitarDirecto(Long idCliente, String descripcion, String ubicacion,
                               Long idProfesional, double tarifaAcordada);

    /** Publica la solicitud para que los tecnicos la vean. */
    Solicitud publicar(Long idSolicitud);

    /** Un tecnico (profesional) acepta la solicitud con una tarifa acordada. */
    Solicitud aceptar(Long idSolicitud, Long idProfesional, double tarifaAcordada);

    /** El tecnico inicia la atencion del servicio. */
    Solicitud iniciar(Long idSolicitud);

    /** El servicio finaliza; dispara el evento que consumen Pagos y Notificaciones. */
    Solicitud finalizar(Long idSolicitud);

    /** Cierra la solicitud al ser calificada (lo dispara el evento ServicioCalificado). */
    Solicitud marcarCalificada(Long idSolicitud);

    /** El cliente cancela la solicitud (si aun no se inicio). */
    Solicitud cancelar(Long idSolicitud);

    Solicitud obtener(Long idSolicitud);

    List<Solicitud> listarPorCliente(Long idCliente);

    /** Trabajos asignados a un tecnico (aceptados, en curso, finalizados). */
    List<Solicitud> listarPorProfesional(Long idProfesional);

    /** Solicitudes abiertas (CREADA/PUBLICADA) que los tecnicos pueden tomar. */
    List<Solicitud> listarPendientes();

    /**
     * Ordena una lista de tecnicos candidatos aplicando la estrategia que
     * corresponda al tipo de la solicitud (Strategy): reputacion si es NORMAL,
     * cercania si es EMERGENCIA.
     */
    List<CandidatoProfesional> emparejar(Long idSolicitud, List<CandidatoProfesional> candidatos);
}
