package com.prestamos.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.prestamos.dao.UsuarioDao;
import com.prestamos.dto.Usuario;

/**
 * Clase que implementa los servicios web del usuario
 * @author esteban.salazar1
 *
 */
@Path("/Usuario")
public class UsuarioREST {

	private UsuarioDao usuarioDao;

	/**
	 * Metodo para autenticar a un usuario
	 * @param tipoDocumento del usuario que intenta autenticarse
	 * @param numeroDocumento del usuario que intenta autentificarse
	 * @param password del usuario que intenta autenticarse
	 * @return un string que dice si se pudo autenticar o no
	 */
	@POST
	@Path("/Autenticar")
	@Consumes(MediaType.APPLICATION_JSON)
	public String autenticarUsuario(
			@QueryParam("tipoDocumento") String tipoDocumento,
			@QueryParam("numeroDocumento") String numeroDocumento,
			@QueryParam("password") String password) {
		try {
			Usuario user = usuarioDao.buscar(tipoDocumento, numeroDocumento);
			if (user != null) {
				if (user.getPassword().equalsIgnoreCase(password)) {
					if(user.getEstado().equalsIgnoreCase("inactivo")){
						return "inac";
					}
					if(user.getTipoUsuario().getTipo().equalsIgnoreCase("administrador")){
						return "admin";
					}else{
						return "user";
					}
				}
			}
		} catch (Exception e) {

		}
		return "false";
	}

	/**
	 * Setter
	 * @param usuarioDao es el usuarioDao que se asigna
	 */
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
