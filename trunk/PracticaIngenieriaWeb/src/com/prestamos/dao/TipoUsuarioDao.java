/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao;

import com.prestamos.dto.TipoUsuario;
import com.prestamos.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Clase que implementa todas las operaciones del objeto TipoUsuario
 * @author Juan Carlos
 */
public class TipoUsuarioDao extends HibernateDaoSupport{
    
    public void insertar(TipoUsuario tipoUsuario) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(tipoUsuario);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }  
    }

    /**
     * Metodo que busca un tipo usuario segun la identificacion del tipousuario
     * @param codigo es la identificacion del tipoUsuario a buscar
     * @return un tipoUsuario que este identificado por el codigo ingresado
     * @throws DaoException
     */
    public TipoUsuario buscar(int codigo) throws DaoException {
        Session session = null;
        TipoUsuario tipoUsuario = null;
        try {
            session = getSession();
            tipoUsuario = (TipoUsuario)session.get(TipoUsuario.class, codigo);
        } catch (ExceptionInInitializerError e) {
            throw new DaoException(e);
        }
        return tipoUsuario;    
    }

    /**
     * Metodo para actualizar informacion de un tipoUsuario
     * @param tipoUsuario es el tipoUsuario actualizado
     * @throws DaoException
     */
    public void actualizar(TipoUsuario tipoUsuario) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.update(tipoUsuario);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }

    /**
     * Metodo para eliminar un tipoUsuario de la base de datos.
     * @param tipoUsuario es el tipoUsuario a eliminar
     * @throws DaoException
     */
    public void eliminar(TipoUsuario tipoUsuario) throws DaoException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.delete(tipoUsuario);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }  
    }

    /**
     * Metodo para listar todos los tipoUsuario que hay en la base de datos.
     * @return una lista con todos los tipoUsuario que hay en la lista
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
	public List<TipoUsuario> listar() throws DaoException {
        Session session = null;
        List<TipoUsuario> tipoDispositivos = new ArrayList<>();
        try {
            session = getSession();
            Criteria criteria = session.createCriteria(TipoUsuario.class);
            tipoDispositivos = criteria.list();
        } catch (ExceptionInInitializerError e) {
            throw new DaoException(e);
        }
        return tipoDispositivos;    
    }
    
}
