/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prestamos.dto.Dispositivo;
import com.prestamos.exception.DaoException;

/**
 * Clase DAO que ejecuta las operaciones de el objeto DispositivoDao sobre la base de datos
 * @author Juan Carlos
 */
public class DispositivoDao extends HibernateDaoSupport{

	/**
	 * Implementacion del metodo insertar
     * @param dispositivo es el dispositivo que se va a insertar
     * @throws DaoException 
	 */
    public void insertar(Dispositivo dispositivo) throws DaoException{
        Session session = null;
        Transaction transaction = null;
        
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(dispositivo);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }
    /**
     * Implementacion del metodo buscar
     * @param codigo es el codigo de identificacion del dispositivo a buscar
     * @return una instancia del dispositivo buscado
     * @throws DaoException  
     */
    public Dispositivo buscar(long codigo) throws DaoException{
        Session session = null;
        Dispositivo dispositivo = null;
        try {
            session = getSession();
            Query query = session.createQuery("FROM Dispositivo WHERE codigo = :codigo");
            query.setLong("codigo", codigo);
            dispositivo = (Dispositivo)query.uniqueResult();
        } catch (ExceptionInInitializerError e) {
            throw new DaoException(e);
        }
        return dispositivo;
    }
    /**
     * Implementacion del metodo actualizar
     * @param dispositivo dispositivo que se van a actualizar los datos
     * @throws DaoException 
     */
    public void actualizar(Dispositivo dispositivo) throws DaoException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.update(dispositivo);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }
    /**
     * Implementacion del metodo eliminar
     * @param dispositivo es el dispositivo que se va a eliminar 
     * @throws DaoException 
     */
    public void eliminar(Dispositivo dispositivo) throws DaoException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.delete(dispositivo);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }
    /**
     * Implementa el metodo de listar los dispositivos 
     * @return una lista con todos los dipositivos disponibles
     * @throws DaoException 
     */
    @SuppressWarnings("unchecked")
	public List<Dispositivo> listar() throws DaoException{
        Session session = null;
        List<Dispositivo> dispositivos = new ArrayList<>();
        try {
            session = getSession();
            Criteria criteria = session.createCriteria(Dispositivo.class);
            dispositivos = criteria.list();
        } catch (ExceptionInInitializerError e) {
            throw new DaoException(e);
        }
        return dispositivos;
    }
    
}
