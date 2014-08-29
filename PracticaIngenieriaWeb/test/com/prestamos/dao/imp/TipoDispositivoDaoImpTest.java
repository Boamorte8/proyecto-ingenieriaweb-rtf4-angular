/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao.imp;

import com.prestamos.dao.TipoDispositivoDao;
import com.prestamos.dto.TipoDispositivo;
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
public class TipoDispositivoDaoImpTest {
    
    @Autowired
    TipoDispositivoDao instance;
    
    /**
     * Test of insertar method, of class TipoDispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testInsertar() throws DaoException {
        System.out.println("insertar");
        TipoDispositivo tipoDispositivo = new TipoDispositivo("Medidor");
        instance.insertar(tipoDispositivo);
        assertTrue(true);
    }

    /**
     * Test of buscar method, of class TipoDispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testBuscar() throws DaoException {
        System.out.println("buscar");
        TipoDispositivo result = instance.buscar(3);
        assertNotNull(result);
    }

    /**
     * Test of actualizar method, of class TipoDispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testActualizar() throws DaoException {
        System.out.println("actualizar");
        TipoDispositivo tipoDispositivo = instance.buscar(2);
        tipoDispositivo.setTipo("Microscopio");
        instance.actualizar(tipoDispositivo);
        tipoDispositivo = instance.buscar(2);
        assertEquals("Microscopio", tipoDispositivo.getTipo());
    }

    /**
     * Test of el
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testEliminar() throws DaoException {
        System.out.println("eliminar");
        TipoDispositivo tipoDispositivo = instance.buscar(3);
        instance.eliminar(tipoDispositivo);
        tipoDispositivo = instance.buscar(3);
        assertNull(tipoDispositivo);
    }

    /**
     * Test of listar method, of class TipoDispositivoDaoImp.
     * @throws com.prestamos.exception.DaoException
     */
    @Test
    public void testListar() throws DaoException {
        System.out.println("listar");
        List<TipoDispositivo> result = instance.listar();
        assertTrue(result.size() > 0);
    }
    
}
