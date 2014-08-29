package com.prestamos.services;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.prestamos.dto.Dispositivo;
import com.prestamos.dto.Prestamo;
import com.prestamos.dto.TipoDispositivo;
import com.prestamos.dto.TipoUsuario;
import com.prestamos.dto.Usuario;
import com.prestamos.dto.UsuarioId;
import com.prestamos.exception.SWException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class PrestamoSWTest {
	
	@Test
	public void testListarDispositivosDisponibles() throws SWException{
		Client client;
		try{
			client = Client.create();
			WebResource resource = client.resource("http://localhost:8080/PracticaIngenieriaWeb/rest/PrestamoSW/disponibles/2014-05-15_16:18:00");
			ClientResponse response = (ClientResponse) resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
			if(response.getStatus()== 200){
				List<Dispositivo> dispositivos = response.getEntity(new GenericType<List<Dispositivo>>(){});
				for (Dispositivo dispositivo : dispositivos) {
					System.out.println(dispositivo);
				}
				assertTrue(dispositivos.size() > 0);
			}
		} catch(Exception e){
			throw new SWException(e);
		}
	}
}
