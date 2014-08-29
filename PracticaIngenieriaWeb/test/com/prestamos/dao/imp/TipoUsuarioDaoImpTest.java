/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao.imp;

import com.prestamos.dao.TipoUsuarioDao;
import com.prestamos.dto.TipoUsuario;
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
 * @author esteban.salazar1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:WebContent/WEB-INF/applicationContext.xml")
public class TipoUsuarioDaoImpTest {
    
    @Autowired
    TipoUsuarioDao instance;


    /**
     * Test of insertar method, of class TipoUsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testInsertar() throws DaoException {
        System.out.println("insertar");
        TipoUsuario tipoUsuario = new TipoUsuario("Estudiante");
        instance.insertar(tipoUsuario);
        assertTrue(true);
    }

    /**
     * Test of buscar method, of class TipoUsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testBuscar() throws DaoException {
        System.out.println("buscar");
        TipoUsuario result = instance.buscar(1);
        assertNotNull(result);
    }

    /**
     * Test of actualizar method, of class TipoUsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testActualizar() throws DaoException {
        System.out.println("actualizar");
        TipoUsuario tipoUsuario = instance.buscar(1);
        tipoUsuario.setTipo("Trabajador");
        instance.actualizar(tipoUsuario);
        tipoUsuario = instance.buscar(1);
        assertEquals("Trabajador", tipoUsuario.getTipo());
    }

    /**
     * Test of eliminar method, of class TipoUsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testEliminar() throws DaoException {
        System.out.println("eliminar");
        TipoUsuario tipoUsuario = instance.buscar(2);
        instance.eliminar(tipoUsuario);
        tipoUsuario = instance.buscar(2);
        assertNull(tipoUsuario);
    }

    /**
     * Test of listar method, of class TipoUsuarioDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testListar() throws DaoException {
        System.out.println("listar");
        List<TipoUsuario> result = instance.listar();
        assertTrue(result.size() > 0);
    }
    
}
