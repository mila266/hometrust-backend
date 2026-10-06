package com.marketplace.matching.domain.model;

import com.marketplace.shared.domain.EstadoSolicitud;
import java.time.LocalDateTime;

/**
 * Entidad de dominio Solicitud con su maquina de estados (patron State).
 * Corresponde al Diagrama de Estados de Solicitud (seccion 5.6.4).
 *
 * Las transiciones validas se encapsulan en los metodos del propio objeto: no es
 * posible llevar la solicitud a un estado invalido desde fuera. Es dominio puro:
 * no conoce Spring, JPA ni la base de datos (nucleo hexagonal).
 */
public class Solicitud {

    private Long id;
    private final Long idCliente;
    private Long idProfesional;
    private final String descripcion;
    private final String ubicacion;
    private final String tipo;          // NORMAL | EMERGENCIA
    private EstadoSolicitud estado;
    private double tarifaAcordada;      // se fija al aceptar la solicitud
    private final LocalDateTime fechaCreacion;

    /** Constructor completo (lo usa el adaptador de persistencia al reconstruir). */
    public Solicitud(Long id, Long idCliente, Long idProfesional, String descripcion,
                     String ubicacion, String tipo, EstadoSolicitud estado,
                     double tarifaAcordada, LocalDateTime fechaCreacion) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProfesional = idProfesional;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.estado = estado;
        this.tarifaAcordada = tarifaAcordada;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Factory Method: crea una solicitud nueva en estado CREADA.
     * Centraliza la regla de arranque (estado y fecha iniciales) en un solo lugar.
     */
    public static Solicitud crear(Long idCliente, String descripcion, String ubicacion, String tipo) {
        String t = (tipo == null || tipo.isBlank()) ? "NORMAL" : tipo.trim().toUpperCase();
        return new Solicitud(null, idCliente, null, descripcion, ubicacion, t,
                EstadoSolicitud.CREADA, 0.0, LocalDateTime.now());
    }

    // ---- transiciones de la maquina de estados ----

    /** El cliente publica la solicitud para que los tecnicos la vean. */
    public void publicar() {
        transicion(EstadoSolicitud.CREADA, EstadoSolicitud.PUBLICADA);
    }

    /** Un tecnico acepta la solicitud y se acuerda la tarifa. */
    public void aceptar(Long idProfesional, double tarifaAcordada) {
        if (estado != EstadoSolicitud.CREADA && estado != EstadoSolicitud.PUBLICADA)
            throw new IllegalStateException("No se puede aceptar una solicitud en estado " + estado);
        if (tarifaAcordada <= 0)
            throw new IllegalArgumentException("La tarifa acordada debe ser mayor que cero");
        this.idProfesional = idProfesional;
        this.tarifaAcordada = tarifaAcordada;
        this.estado = EstadoSolicitud.ACEPTADA;
    }

    public void iniciar()   { transicion(EstadoSolicitud.ACEPTADA, EstadoSolicitud.EN_CURSO); }
    public void finalizar() { transicion(EstadoSolicitud.EN_CURSO, EstadoSolicitud.FINALIZADA); }
    public void marcarCalificada() { transicion(EstadoSolicitud.FINALIZADA, EstadoSolicitud.CALIFICADA); }

    /** Se puede cancelar mientras no se haya iniciado el servicio. */
    public void cancelar() {
        if (estado == EstadoSolicitud.EN_CURSO || estado == EstadoSolicitud.FINALIZADA
                || estado == EstadoSolicitud.CALIFICADA)
            throw new IllegalStateException("No se puede cancelar una solicitud en estado " + estado);
        this.estado = EstadoSolicitud.CANCELADA;
    }

    private void transicion(EstadoSolicitud desde, EstadoSolicitud hacia) {
        if (estado != desde)
            throw new IllegalStateException("Transicion invalida: se esperaba " + desde + " pero esta en " + estado);
        this.estado = hacia;
    }

    /** Regla de negocio: una emergencia se prioriza por cercania, no por reputacion. */
    public boolean esEmergencia() {
        return "EMERGENCIA".equals(tipo);
    }

    // ---- getters / soporte de persistencia ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdCliente() { return idCliente; }
    public Long getIdProfesional() { return idProfesional; }
    public String getDescripcion() { return descripcion; }
    public String getUbicacion() { return ubicacion; }
    public String getTipo() { return tipo; }
    public EstadoSolicitud getEstado() { return estado; }
    public double getTarifaAcordada() { return tarifaAcordada; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
}
