package com.marketplace.perfiles.domain.model;

import com.marketplace.shared.domain.EstadoVerificacion;

/**
 * Entidad de dominio Profesional.
 * Contiene la maquina de estados de verificacion (patron State simplificado):
 * las transiciones validas se controlan en los propios metodos, no con if dispersos.
 * NO depende de Spring ni de JPA: es dominio puro (nucleo hexagonal).
 */
public class Profesional {

    private Long id;
    private String nombre;
    private String ubicacionActual;
    private EstadoVerificacion estado;

    public Profesional(Long id, String nombre, String ubicacionActual, EstadoVerificacion estado) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacionActual = ubicacionActual;
        this.estado = estado;
    }

    /** Crea un profesional nuevo en estado REGISTRADO. */
    public static Profesional registrar(String nombre, String ubicacionActual) {
        return new Profesional(null, nombre, ubicacionActual, EstadoVerificacion.REGISTRADO);
    }

    // ---- transiciones de la maquina de estados ----

    public void enviarDocumentos() {
        exigir(EstadoVerificacion.REGISTRADO, EstadoVerificacion.DOCUMENTACION_INCOMPLETA);
        this.estado = EstadoVerificacion.DOCUMENTOS_ENVIADOS;
    }

    public void tomarEnRevision() {
        exigir(EstadoVerificacion.DOCUMENTOS_ENVIADOS);
        this.estado = EstadoVerificacion.EN_REVISION;
    }

    public void aprobar() {
        exigir(EstadoVerificacion.EN_REVISION);
        this.estado = EstadoVerificacion.ACTIVO;
    }

    public void rechazar() {
        exigir(EstadoVerificacion.EN_REVISION);
        this.estado = EstadoVerificacion.RECHAZADO;
    }

    public void marcarDocumentacionIncompleta() {
        exigir(EstadoVerificacion.EN_REVISION);
        this.estado = EstadoVerificacion.DOCUMENTACION_INCOMPLETA;
    }

    public void suspender() {
        exigir(EstadoVerificacion.ACTIVO);
        this.estado = EstadoVerificacion.SUSPENDIDO;
    }

    public void reactivar() {
        exigir(EstadoVerificacion.SUSPENDIDO);
        this.estado = EstadoVerificacion.ACTIVO;
    }

    /** Regla de negocio: solo un profesional ACTIVO aparece en el panel del cliente. */
    public boolean esVisibleEnPanel() {
        return this.estado == EstadoVerificacion.ACTIVO;
    }

    private void exigir(EstadoVerificacion... permitidos) {
        for (EstadoVerificacion e : permitidos) {
            if (this.estado == e) return;
        }
        throw new IllegalStateException(
            "Transicion invalida desde el estado " + this.estado);
    }

    // ---- getters ----
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getUbicacionActual() { return ubicacionActual; }
    public EstadoVerificacion getEstado() { return estado; }
    public void setId(Long id) { this.id = id; }
}
