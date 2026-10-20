
package com.maquinaenvios.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Entidad que representa un envío dentro del sistema
 * Máquina de Autoservicio para Gestión de Envíos.
 *
 * Hibernate utiliza esta clase para relacionar los
 * objetos Java con la tabla envio de la base de datos.
 *
 * @author Edwin Bohórquez
 */
@Entity
@Table(name = "envio")
public class Envio {

    /** Identificador principal del envío. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private long idEnvio;

    /** Número único de guía del envío. */
    @Column(
        name = "numero_guia",
        nullable = false,
        unique = true,
        length = 30
    )
    private String numeroGuia;

    /** Usuario que registra el envío. */
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    /** Identificador del contacto remitente. */
    @Column(name = "id_remitente", nullable = false)
    private int idRemitente;

    /** Identificador del contacto destinatario. */
    @Column(name = "id_destinatario", nullable = false)
    private int idDestinatario;

    /** Ciudad de origen del envío. */
    @Column(name = "id_ciudad_origen", nullable = false)
    private int idCiudadOrigen;

    /** Ciudad de destino del envío. */
    @Column(name = "id_ciudad_destino", nullable = false)
    private int idCiudadDestino;

    /** Servicio utilizado para realizar el envío. */
    @Column(name = "id_servicio", nullable = false)
    private int idServicio;

    /** Estado actual del envío. */
    @Column(name = "id_estado", nullable = false)
    private int idEstado;

    /**
     * Fecha y hora de creación.
     * El valor es generado por la base de datos.
     */
    @Column(
        name = "fecha_creacion",
        insertable = false,
        updatable = false
    )
    private Timestamp fechaCreacion;

    /** Valor monetario total del envío. */
    @Column(
        name = "valor_total",
        nullable = false,
        precision = 12,
        scale = 2
    )
    private BigDecimal valorTotal;

    /** Observaciones adicionales del envío. */
    @Column(name = "observaciones", length = 300)
    private String observaciones;

    /**
     * Constructor vacío requerido por Hibernate.
     */
    public Envio() {
    }

    public long getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(long idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(int idRemitente) {
        this.idRemitente = idRemitente;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public int getIdCiudadOrigen() {
        return idCiudadOrigen;
    }

    public void setIdCiudadOrigen(int idCiudadOrigen) {
        this.idCiudadOrigen = idCiudadOrigen;
    }

    public int getIdCiudadDestino() {
        return idCiudadDestino;
    }

    public void setIdCiudadDestino(int idCiudadDestino) {
        this.idCiudadDestino = idCiudadDestino;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
