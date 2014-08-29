/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao;

import com.prestamos.dto.TipoDispositivo;
import com.prestamos.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Clase que implementa todas las operaciones del objeto TIpoDispositivoDao
 * @author Juan Carlos
 */
public class TipoDispositivoDao extends HibernateDaoSupport{

	/**
	 * Metodo que inserta un TipoDispositivo en la base de datos
	 * @param tipoDispositivo es el tipoDispositivo a insertar
	 * @throws DaoException
	 */
    public void insertar(TipoDispositivo tipoDispositivo) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(tipoDispositivo);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }    
    }

    /**
     * Metodo que busca un tipoDispositivo segun el codigo
     * @param codigo es el identificador del TipoDispositivo que se busca
     * @return el tipoDispositivo que se busca y que sea identificado por el codigo 
     * @throws DaoException
     */
    public TipoDispositivo buscar(int codigo) throws DaoException {
        Session session = null;
        TipoDispositivo tipoDispositivo = null;
        try {
            session = getSession();
            tipoDispositivo = (TipoDispositivo)session.get(TipoDispositivo.class, codigo);
        } catch (ExceptionInInitializerError e) {
            throw new DaoException(e);
        }
        return tipoDispositivo;    
    }

    /**
     * Metodo que actualiza la informacion de un tipoDispositivo
     * @param tipoDispositivo es el objeto que se va a actualizar y contiene la informacion ya actualizada
     * @throws DaoException
     */
    public void actualizar(TipoDispositivo tipoDispositivo) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.update(tipoDispositivo);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }

    /**
     * Metodo que elimina un TipoDispositivo de la base de datos
     * @param tipoDispositivo es el tipoDispositivo a eliminar
     * @throws DaoException
     */
    public void eliminar(TipoDispositivo tipoDispositivo) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.delete(tipoDispositivo);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }   
    }

    /**
     * Metodo que lista todos los tipoDispositivos que hay en la base de datos. 
     * @return una lista con todos los tipoDispositivos que se encuentran en la base de datos
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
	public List<TipoDispositivo> listar() throws DaoException {
        Session session = null;
        List<TipoDispositivo> tipoDispositivos = new ArrayList<>();
        try {
            session = getSession();
            Criteria criteria = session.createCriteria(TipoDispositivo.class);
            tipoDispositivos = criteria.list();
        } catch (ExceptionInInitializerError e) {
            throw new DaoException(e);
        }
        return tipoDispositivos;    
    }
    
}
