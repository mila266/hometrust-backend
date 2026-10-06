package com.marketplace.ofertas.domain.model;

import com.marketplace.shared.domain.EstadoOferta;

/**
 * Entidad de dominio Oferta (flujo de EMERGENCIA / broadcast).
 * Un tecnico propone una tarifa y un tiempo de llegada ante una solicitud de
 * emergencia; el cliente elige una. Corresponde a la clase Oferta del diagrama
 * de clases y al caso de uso "Elegir Profesional entre Ofertas". Dominio puro.
 */
public class Oferta {

    private Long id;
    private final Long idSolicitud;
    private final Long idProfesional;
    private final double tarifaPropuesta;
    private final int tiempoEstimadoLlegada;  // en minutos
    private final String ubicacionActual;
    private EstadoOferta estado;

    public Oferta(Long id, Long idSolicitud, Long idProfesional, double tarifaPropuesta,
                  int tiempoEstimadoLlegada, String ubicacionActual, EstadoOferta estado) {
        this.id = id;
        this.idSolicitud = idSolicitud;
        this.idProfesional = idProfesional;
        this.tarifaPropuesta = tarifaPropuesta;
        this.tiempoEstimadoLlegada = tiempoEstimadoLlegada;
        this.ubicacionActual = ubicacionActual;
        this.estado = estado;
    }

    /** Factory Method: un tecnico envia una oferta nueva (estado ENVIADA). */
    public static Oferta enviar(Long idSolicitud, Long idProfesional, double tarifaPropuesta,
                                int tiempoEstimadoLlegada, String ubicacionActual) {
        if (tarifaPropuesta <= 0) throw new IllegalArgumentException("La tarifa propuesta debe ser mayor que cero");
        if (tiempoEstimadoLlegada <= 0) throw new IllegalArgumentException("El tiempo de llegada debe ser mayor que cero");
        return new Oferta(null, idSolicitud, idProfesional, tarifaPropuesta,
                tiempoEstimadoLlegada, ubicacionActual, EstadoOferta.ENVIADA);
    }

    public void aceptar() {
        if (estado != EstadoOferta.ENVIADA) throw new IllegalStateException("La oferta ya no esta disponible");
        this.estado = EstadoOferta.ACEPTADA;
    }

    public void rechazar() {
        if (estado == EstadoOferta.ENVIADA) this.estado = EstadoOferta.RECHAZADA;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdSolicitud() { return idSolicitud; }
    public Long getIdProfesional() { return idProfesional; }
    public double getTarifaPropuesta() { return tarifaPropuesta; }
    public int getTiempoEstimadoLlegada() { return tiempoEstimadoLlegada; }
    public String getUbicacionActual() { return ubicacionActual; }
    public EstadoOferta getEstado() { return estado; }
}
