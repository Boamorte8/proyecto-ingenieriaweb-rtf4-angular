/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.prestamos.dao.DispositivoDao;
import com.prestamos.dao.PrestamoDao;
import com.prestamos.dao.UsuarioDao;
import com.prestamos.dto.Dispositivo;
import com.prestamos.dto.Prestamo;
import com.prestamos.dto.Usuario;
import com.prestamos.dto.UsuarioId;
import com.prestamos.exception.DaoException;
import com.prestamos.exception.SWException;

/**
 * Clase que implementa los servicios web de la clase prestamo
 * @author Juan Carlos
 */
@Component
@Path("/Prestamo")
public class PrestamoREST {

	private PrestamoDao prestamoDao;
	private DispositivoDao dispositivoDao;
	private UsuarioDao usuarioDao;

	/**
	 * Metodo que lista todas las solicitudes hechas para realizar prestamos
	 * @return una lista de todas las solicitudes hechas al sistema que esten sin procesar
	 */
	@GET
	@Path("/Solicitudes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Prestamo> listarSolicitudes() {
		try {
			List<Prestamo> solicitudes = prestamoDao.listarSolicitudes();
			if (solicitudes != null) {
				for (Prestamo p : solicitudes) {
					p.getDispositivo().setPrestamos(null);
					p.getDispositivo().getTipoDispositivo().setDispositivos(null);
					p.getInvestigador().getTipoUsuario().setUsuarios(null);
					if(p.getEncargado() != null){
						p.getEncargado().getTipoUsuario().setUsuarios(null);
					}
				}
			}
			return solicitudes;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	/**
	 * @param tipoDoc tipo de documento del usuario
	 * @param numDoc  numero de documento del usuario
	 */
	@GET
	@Path("/Usuario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Prestamo> listarPrestamosUsuario(
			@QueryParam("tipoDocumento") String tipoDoc,
			@QueryParam("numeroDocumento") String numDoc) {
		try {
			List<Prestamo> prestamos = prestamoDao.consultarPrestamosInvestigador(new UsuarioId(tipoDoc, numDoc));
			if (prestamos != null) {
				for (Prestamo p : prestamos) {
					p.getDispositivo().setPrestamos(null);
					p.getDispositivo().getTipoDispositivo().setDispositivos(null);
					p.getInvestigador().getTipoUsuario().setUsuarios(null);
				}
			}
			return prestamos;
		} catch (Exception e) {
			System.out.println("error");
		}
		return null;
	}
	
	/**
	 * Metodo para solicitar un prestamo
	 * @param fecha es la fecha de la solicitud
	 * @param horaI hora de inicio del prestamo
	 * @param horaF hora de finalizacion del prestamo
	 * @param disp dispositivo que se solicita prestar
	 * @param tipoDoc tipo de documento del investigador que esta realizando la solicitud
	 * @param numDoc numero de documento del investigador que realiza la solicitud
	 * @return un texto que dice si el prestamo se realizo o no exitosamente
	 * @throws SWException Clase que manejara los errores
	 */
	@POST
	@Path("/Solicitar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String solicitarPrestamo(
			@QueryParam("fecha") String fecha, 
			@QueryParam("horaI") String horaI,
			@QueryParam("horaF") String horaF,
			@QueryParam("dispositivo") long disp,
			@QueryParam("tipoDocumento") String tipoDoc,
			@QueryParam("numeroDocumento") String numDoc) throws SWException {
		try {
			Dispositivo d = dispositivoDao.buscar(disp);
			if(d == null){
				return "Error el dispositivo "+disp+"no existe";
			}
			Usuario u = usuarioDao.buscar(tipoDoc, numDoc);
			if(u == null){
				return "Error realizando la solicitud";
			}
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formater.setTimeZone(TimeZone.getTimeZone("America/Colombia"));
			fecha = fecha.substring(0, 10);
			horaI = horaI.substring(11, 19);
			horaF = horaF.substring(11, 19);
			Prestamo nuevo = new Prestamo();
			nuevo.setDispositivo(d);
			nuevo.setEstado("solicitado");
			nuevo.setFechaFin(formater.parse(fecha+" "+horaF));
			nuevo.setFechaInicio(formater.parse(fecha+" "+horaI));
			nuevo.setInvestigador(u);
			prestamoDao.solicitarPrestamo(nuevo);
			return "prestamo solicitado exitosamente";
		} catch (ParseException | DaoException ex) {
			throw new SWException(ex);
		}
	}
	
	/**
	 * Metodo para pasar un prestamo al estado aprobado
	 * @param prestamo que se quiere aprobar
	 * @param tipoDoc tipo de documento del usuario
	 * @param numDoc  numero de documento del usuario
	 * @return un texto donde se dice si el texto se aprobo exitosamente
	 * @throws SWException Clase que manejara los errores
	 */
	@PUT
	@Path("/Aprobar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String aprobarPrestamo(
			@QueryParam("tipoDocumento") String tipoDoc,
			@QueryParam("numeroDocumento") String numDoc,
			@QueryParam("prestamo") long prestamo)
			throws SWException {
		try {
			Prestamo p = prestamoDao.buscar(prestamo);
			if(p != null){
				p.setEstado("aprobado");
				p.setEncargado(usuarioDao.buscar(tipoDoc, numDoc));
				prestamoDao.actualizar(p);
				return "El prestamo "+prestamo+" fue aprobado";
			}
			return "El prestamo "+prestamo+" no existe";
		} catch (DaoException ex) {
			throw new SWException(ex);
		}
	}

	/**
	 * Metodo para rechazar una solicitud de prestamo
	 * @param prestamo que se quiere rechazar
	 * @param tipoDoc tipo de documento del usuario
	 * @param numDoc  numero de documento del usuario
	 * @return un texto indicando si el prestamo se rechazo exitosamente o no
	 * @throws SWException Clase que manejara los errores
	 */
	@PUT
	@Path("/Rechazar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String rechazarPrestamo(
			@QueryParam("tipoDocumento") String tipoDoc,
			@QueryParam("numeroDocumento") String numDoc,
			@QueryParam("prestamo") long prestamo)
			throws SWException {
		try {
			Prestamo p = prestamoDao.buscar(prestamo);
			if(p != null){
				p.setEstado("rechazado");
				p.setEncargado(usuarioDao.buscar(tipoDoc, numDoc));
				prestamoDao.actualizar(p);
				return "El prestamo "+prestamo+" fue rechazado";
			}
			return "El prestamo "+prestamo+" no existe";
		} catch (DaoException ex) {
			throw new SWException(ex);
		}
	}

	/**
	 * Setter
	 * @param prestamoDao que se quiere asignar
	 */
	public void setPrestamoDao(PrestamoDao prestamoDao) {
		this.prestamoDao = prestamoDao;
	}

	/**
	 * Setter
	 * @param dispositivoDao que se quiere asignar
	 */
	public void setDispositivoDao(DispositivoDao dispositivoDao) {
		this.dispositivoDao = dispositivoDao;
	}
		
	/**
	 * Setter
	 * @param usuarioDao que se quiere asignar
	 */
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
}
