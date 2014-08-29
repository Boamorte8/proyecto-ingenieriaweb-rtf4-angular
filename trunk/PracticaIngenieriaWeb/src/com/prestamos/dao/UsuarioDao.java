/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao;

import com.prestamos.dto.Usuario;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prestamos.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que tiene las operaciones de el objeto Usuario en la base de datos.
 * @author Juan Carlos
 */
public class UsuarioDao extends HibernateDaoSupport{
    
	/**
     * Metodo para insertar un nuevo usuario a la base de datos
     * @param usuario es el nuevo usuario a insertar
     * @throws DaoException
     */
    public void insertar(Usuario usuario) throws DaoException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }        
    }

    /**
     * Metodo para buscar un usuario en la base de datos segun su identificacion.
     * @param tipoDoc uno de los parametros para identificar el usuario a buscar
     * @param numDoc el segundo parametro para identificar el usuario a buscar
     * @return el usuario buscado
     * @throws DaoException
     */
    public Usuario buscar(String tipoDoc, String numDoc) throws DaoException{
    	Session session = null;
        Usuario usuario = null;
        try {
            session = getSession();
            Query query = session.createQuery("FROM Usuario "
            		+ "WHERE id.tipoDocumento = :tipoDoc "
            		+ "and id.numeroDocumento = :numDoc");
            query.setString("tipoDoc", tipoDoc);
            query.setString("numDoc", numDoc);
            usuario = (Usuario)query.uniqueResult();
        } catch (ExceptionInInitializerError e) {
            throw new DaoException(e);
        }
        return usuario;
    }

    /**
     * Metodo para actualizar la informacion de un usuario
     * @param usuario es el usuario actualizado
     * @throws DaoException
     */
    public void actualizar(Usuario usuario) throws DaoException{
         Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }

    /**
     * Metodo para eliminar un usuario de la base de datos
     * @param usuario es el usuario a eliminar
     * @throws DaoException
     */
    public void eliminar(Usuario usuario) throws DaoException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.delete(usuario);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }

    /**
     * Lista todos los usuarios que hay en la base de datos
     * @return una lista con todos los usuarios que se encuentran en la base de datos
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
	public List<Usuario> listar() throws DaoException{
        Session session = null;
        List<Usuario> usuarios = new ArrayList<>();
        try {
            session = getSession();
            Criteria criteria = session.createCriteria(Usuario.class);
            usuarios = criteria.list();
        } catch (ExceptionInInitializerError e) {
            throw new DaoException(e);
        }
        return usuarios;
    }
    
}
