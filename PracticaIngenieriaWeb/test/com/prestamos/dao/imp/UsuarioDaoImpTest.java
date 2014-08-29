/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao.imp;

import com.prestamos.dao.UsuarioDao;
import com.prestamos.dto.Usuario;
import com.prestamos.dto.UsuarioId;
import com.prestamos.exception.DaoException;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mauricio.rojass
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:WebContent/WEB-INF/applicationContext.xml")
public class UsuarioDaoImpTest {
    
    @Autowired
    UsuarioDao instance;
    
    public UsuarioDaoImpTest() {
    }
    

    /**
     * Test of insertar method, of class UsuarioDaoImp.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsertar() throws DaoException {
        System.out.println("insertar");
        Usuario usuario = instance.buscar("cedula", "123");
        usuario = new Usuario(new UsuarioId("cedula", "54689"),usuario.getTipoUsuario() ,"mauricio", "rojas", "aaa@bbb.com", "activo");
        instance.insertar(usuario);
        assertTrue(true);
    }

    /**
     * Test of buscar method, of class UsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testBuscar() throws DaoException {
        System.out.println("buscar");
        Usuario result = instance.buscar("cedula", "123");
        assertNotNull(result);
    }

    /**
     * Test of actualizar method, of class UsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testActualizar() throws DaoException {
        System.out.println("actualizar");
        Usuario usuario = instance.buscar("cedula", "123");
        usuario.setCorreo("lunado@hotmail.com");
        instance.actualizar(usuario);
        usuario= instance.buscar("cedula", "123");
        assertEquals("lunado@hotmail.com", usuario.getCorreo());
    }

    /**
     * Test of eliminar method, of class UsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testEliminar() throws DaoException {
        System.out.println("eliminar");
        Usuario usuario = instance.buscar("cedula", "123");
        instance.eliminar(usuario);
        usuario = instance.buscar("cedula", "123");
        assertNull(usuario);
    }

    /**
     * Test of listar method, of class UsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testListar() throws DaoException {
        System.out.println("listar");
        List<Usuario> result = instance.listar();
        assertTrue(result.size() > 0);
    }
    
}
