/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao.imp;

import com.prestamos.dao.DispositivoDao;
import com.prestamos.dto.Dispositivo;
import com.prestamos.exception.DaoException;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Carlos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "file:WebContent/WEB-INF/applicationContext.xml")
public class DispositivoDaoImpTest {
    
    @Autowired
    DispositivoDao instance;
    
    public DispositivoDaoImpTest() {
    }
    /**
     * Test of insertar method, of class DispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testInsertar() throws DaoException{
        System.out.println("insertar");
        Dispositivo dispositivo = instance.buscar(3);
        dispositivo = new Dispositivo(dispositivo.getTipoDispositivo(), "samsung", "activo");
        instance.insertar(dispositivo);
        assertTrue(true);
    }

    /**
     * Test of buscar method, of class DispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testBuscar() throws DaoException{
        System.out.println("buscar");
        Dispositivo result = instance.buscar(3);
        assertNotNull(result);
    }

    /**
     * Test of actualizar method, of class DispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testActualizar() throws DaoException{
        System.out.println("actualizar");
        Dispositivo dispositivo = instance.buscar(3);
        dispositivo.setDescripcion("lenovo");
        instance.actualizar(dispositivo);
        dispositivo = instance.buscar(3);
        assertEquals("lenovo", dispositivo.getDescripcion());
    }

    /**
     * Test of eliminar method, of class DispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testEliminar() throws DaoException{
        System.out.println("eliminar");
        Dispositivo dispositivo = instance.buscar(4);
        instance.eliminar(dispositivo);
        dispositivo = instance.buscar(4);
        assertNull(dispositivo);
    }

    /**
     * Test of listar method, of class DispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testListar() throws DaoException{
        System.out.println("listar");
        List<Dispositivo> result = instance.listar();
        assertTrue(result.size() > 0);
    }
    
}
