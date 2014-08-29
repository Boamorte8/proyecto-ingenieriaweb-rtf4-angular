package com.prestamos.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prestamos.dao.TipoDispositivoDao;
import com.prestamos.dto.TipoDispositivo;
import com.prestamos.exception.DaoException;
import com.prestamos.exception.SWException;

/**
 * Clase que implementa los servicios web de la clase TipoDispositivo
 * @author esteban.salazar1
 *
 */
@Path("/TipoDispositivo")
public class TipoDispositivoREST {
	
	private TipoDispositivoDao tipoDispositivoDao;

	/**
	 * Metodo que se encarga de listar todos los tipos de dispositivos
	 * @return una lista con todos los tipos de dispositivos
	 * @throws SWException clase que maneja los errores de este metodo
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> listarTodos() throws SWException{
		List<String> tipos = new ArrayList<>();
		tipos.add("Todos");
		try{
			for(TipoDispositivo td:tipoDispositivoDao.listar()){
				tipos.add(td.getTipo());
			}
		}catch(DaoException e){
			throw new SWException(e);
		}
		return tipos;
	}

	/**
	 * Setter
	 * @param tipoDispositivoDao que se asigna
	 */
	public void setTipoDispositivoDao(TipoDispositivoDao tipoDispositivoDao) {
		this.tipoDispositivoDao = tipoDispositivoDao;
	}
	
}
