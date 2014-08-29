package com.prestamos.dto;
// Generated 17/07/2014 03:01:05 PM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Clase del objeto Prestamos
 * Prestamo generated by hbm2java
 */
public class Prestamo  implements java.io.Serializable {


     private Long consecutivo;
     private Dispositivo dispositivo;
     private Date fechaInicio;
     private Date fechaFin;
     private String estado;
     private String estadoInicialDispositivo;
     private String estadoFinalDispositivo;
     private Usuario investigador;
     private Usuario encargado;

/**
      * Constructor vacio
      */
    public Prestamo() {
    }

 /**
     * Constructor con todos los parametros 
     * @param dispositivo el dispositivo que se ha prestado
     * @param fechaInicio fecha de Inicio del prestamos
     * @param fechaFin fecha de finalizacion del prestamo
     * @param estado estado estado actual del dispositivo
     * @param estadoInicialDispositivo estado antes del prestamo
     * @param estadoFinalDispositivo estado despues del prestamo
     * @param Investigador persona que hace el prestamo
     * @param Encargado persona que aprueba el prestamo
     */
    public Prestamo(Dispositivo dispositivo, Date fechaInicio, Date fechaFin, String estado, String estadoInicialDispositivo, String estadoFinalDispositivo, Usuario Investigador, Usuario Encargado) {
        this.dispositivo = dispositivo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.estadoInicialDispositivo = estadoInicialDispositivo;
        this.estadoFinalDispositivo = estadoFinalDispositivo;
        this.investigador = Investigador;
        this.encargado = Encargado;
    }


    /**
     * Getter
     * @return el identificador del prestamo
     */
    public Long getConsecutivo() {
        return consecutivo;
    }

    /**
     * Asigna el identificador del prestamo
     * @param consecutivo identificador del prestamos a asignar
     */
    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    /**
     * Getter
     * @return el dispositivo del que se ha hecho prestamo
     */
    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    /**
     * Setter
     * @param dispositivo asigna el dispositivo del que se ha hecho prestamo
     */
    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    /**
     * Getter
     * @return la fecha de inicio del prestamo
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Setter
     * @param fechaInicio que se asigna al prestamo
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Getter
     * @return la fecha de finalizacion del prestamo
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * Setter
     * @param fechaFin fecha de finalizacion del prestamo
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Getter
     * @return el estado actual del dispositivo
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Setter
     * @param estado actual que se asigna al dispositivo
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Getter
     * @return el estado inicial del dispositivo
     */
    public String getEstadoInicialDispositivo() {
        return estadoInicialDispositivo;
    }

    /**
     * Setter
     * @param estadoInicialDispositivo estado del dispositivo antes del prestamo
     */
    public void setEstadoInicialDispositivo(String estadoInicialDispositivo) {
        this.estadoInicialDispositivo = estadoInicialDispositivo;
    }

    /**
     * Getter
     * @return el estado del dispositivo despues del prestamo
     */
    public String getEstadoFinalDispositivo() {
        return estadoFinalDispositivo;
    }

    /**
     * Setter
     * @param estadoFinalDispositivo estado despues del prestamos del dispositivo
     */
    public void setEstadoFinalDispositivo(String estadoFinalDispositivo) {
        this.estadoFinalDispositivo = estadoFinalDispositivo;
    }

    /**
     * Getter
     * @return el investigador que hizo el prestamo
     */
    public Usuario getInvestigador() {
        return investigador;
    }

    /**
     * Setter
     * @param Investigador que se asigna al prestamo
     */
    public void setInvestigador(Usuario Investigador) {
        this.investigador = Investigador;
    }

    /**
     * Getter
     * @return el encargado de aceptar la solicitud de prestamo
     */
    public Usuario getEncargado() {
        return encargado;
    }

    /**
     * Setter
     * @param Encargado la persona que acepta la solicitud de prestamo
     */
    public void setEncargado(Usuario Encargado) {
        this.encargado = Encargado;
    }

    
}
