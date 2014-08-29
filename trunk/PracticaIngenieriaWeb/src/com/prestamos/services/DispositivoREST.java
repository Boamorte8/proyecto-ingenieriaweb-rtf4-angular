package com.prestamos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.prestamos.dao.PrestamoDao;
import com.prestamos.dto.Dispositivo;
import com.prestamos.exception.DaoException;
import com.prestamos.exception.SWException;

/**
 * Clase que implementa los servicios web de la clase dispositivo
 * @author esteban.salazar1
 *
 */
@Path("/Dispositivo")
public class DispositivoREST {

	private PrestamoDao prestamoDao;

	/**
	 * Metodo que lista todos los dispositivos disponibles  teniendo en cuenta los siguientes parametros
	 * @param fecha dia en que se hará el prestamo
	 * @param horaI hora de inicio del prestamo
	 * @param horaF hora de finalizacion del prestamo
	 * @param tipo de dispositivo que se quiere prestar
	 * @return una lista de los dispositivos disponibles segun los criterios anteriores
	 * @throws SWException metodo que manejara los errores
	 */
	@GET
	@Path("/Disponibles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Dispositivo> listarDispositivosDisponibles(
			@QueryParam("fecha") String fecha, 
			@QueryParam("horaInicial") String horaI, 
			@QueryParam("horaFinal") String horaF,
			@QueryParam("tipo") String tipo) throws SWException {
		try {
			fecha = fecha.substring(0, 10);
			horaI = horaI.substring(11, 19);
			horaF = horaF.substring(11, 19);
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formater.setTimeZone(TimeZone.getTimeZone("America/Colombia"));
			return prestamoDao.listarDispositivosDisponibles(formater.parse(fecha+" "+horaI), formater.parse(fecha+" "+horaF), tipo);
		} catch (ParseException | DaoException e) {
			throw new SWException(e);
		}
	}

	/**
	 * Setter
	 * @param prestamoDao es el objeto prestamo
	 */
	public void setPrestamoDao(PrestamoDao prestamoDao) {
		this.prestamoDao = prestamoDao;
	}

}
