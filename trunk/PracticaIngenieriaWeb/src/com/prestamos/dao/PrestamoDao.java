/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.dao;

import com.prestamos.dto.Dispositivo;
import com.prestamos.dto.Prestamo;
import com.prestamos.dto.UsuarioId;
import com.prestamos.exception.DaoException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Clase DAO que ejecuta las operaciones de el objeto PrestamoDao sobre la base de datos
 * @author Juan Carlos
 */
public class PrestamoDao extends HibernateDaoSupport{

	/**
     * Implementacion del metodo que lista los dispositivos disponibles
     * @param fecha es la fecha en la que se busca que los dispositivos esten disponibles
     * @return una lista con todos los dispositivos disponibles
     * @throws DaoException 
     */
    @SuppressWarnings("unchecked")
	public List<Dispositivo> listarDispositivosDisponibles(Date fechaI, Date fechaF, String tipo) throws DaoException{
        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery("FROM Prestamo "
            		+ "WHERE (estado = 'activo' "
            		+ "or estado = 'solicitado' "
            		+ "or estado = 'aprobado')");
           List<Prestamo> prestamos = query.list();
           query = session.createQuery("FROM Dispositivo WHERE estado = 'activo'");
           List<Dispositivo> dispositivos = query.list();
           SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           for(Prestamo p:prestamos){
        	   String fi = formater.format(fechaI);
        	   String ff = formater.format(fechaF);
        	   String fip = formater.format(p.getFechaInicio());
        	   String ffp = formater.format(p.getFechaFin());
        	   int a = fi.compareTo(fip);
        	   int b = fi.compareTo(ffp);
        	   int c = ff.compareTo(fip);
        	   int d = ff.compareTo(ffp);
        	   System.out.println(a+" "+b+" "+c+" "+d);
        	   if((0 <= a && b < 0) || (0 < c && d <= 0)){
        		   dispositivos.remove(p.getDispositivo());
        	   }
           }
           for(int i = 0; i < dispositivos.size(); i++){
        	   Dispositivo d = dispositivos.get(i);
        	   d.setPrestamos(null);
        	   d.getTipoDispositivo().setDispositivos(null);
        	   if(!tipo.equalsIgnoreCase("Todos")){
	        	   if(!d.getTipoDispositivo().getTipo().equalsIgnoreCase(tipo)){
	        		   dispositivos.remove(i);
	        		   i--;
	        	   }
        	   }
           }
           return dispositivos;
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }
    /**
     * Implementacion del metodo que solicita prestamos
     * @param prestamo es el nuevo prestamos que se va a ingresar 
     * @throws DaoException 
     */
    public void solicitarPrestamo(Prestamo prestamo) throws DaoException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }
    /**
     * Implementa el metodo de actualizacion de prestamo
     * @param prestamo es el prestamo que se actualiza
     * @throws DaoException 
     */
    public void actualizar(Prestamo prestamo) throws DaoException{
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session.update(prestamo);
            transaction.commit();//EXITO
        } catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();//ERROR 
            }
            throw new DaoException(e);
        }
    }
    /**
     * Busca un prestamo por el numero de identificación
     * @param consecutivo es el codigo de identificacion de un prestamo
     * @return el prestamo que se busca identificado por el consecutivo
     * @throws DaoException
     */
    public Prestamo buscar(long consecutivo) throws DaoException{
        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery("FROM Prestamo WHERE consecutivo = :consecutivo");
            query.setLong("consecutivo", consecutivo);
            return (Prestamo)query.uniqueResult();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }
    /**
     * Implementa el metodo de consultar los prestamos hecho por un investigador
     * @param id es el id del investigador al que se le consultan los prestamos
     * @return retorna todos los prestamos realizados por el investigador
     * @throws DaoException 
     */
    @SuppressWarnings("unchecked")
	public List<Prestamo> consultarPrestamosInvestigador(UsuarioId id) throws DaoException{
        Session session = null;
        List<Prestamo> lista = null;
        try {
            session = getSession();
            Query query = session.createQuery("FROM Prestamo WHERE investigador.id = :id");
            query.setParameter("id", id);
            lista = query.list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return lista;
    }
    /**
     * Implementa el metodo de consulta de prestamos activos
     * @return una lista con todos los metodos que estan activos en el momento de la consulta
     * @throws DaoException 
     */
    @SuppressWarnings("unchecked")
	public List<Prestamo> consultarPrestamosActivos() throws DaoException{
        Session session = null;
        List<Prestamo> lista;
        try {
            session = getSession();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fecha = new Date();
            lista = session.createQuery("FROM Prestamo WHERE ('"+formater.format(fecha)+"' between fechaInicio "
                    + "and fechaFin) and (estado = 'activo' or estado = 'aprobado')").list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return lista;
    }
    /**
     * Metodo que lista todas las solicitudes hechas por el sistema y que estan no han sido respondidas
     * @return una lista de todas las solicitudes hechas que estan sin responder
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
	public List<Prestamo> listarSolicitudes() throws DaoException {
    	Session session = null;
        List<Prestamo> lista;
        try {
            session = getSession();
            lista = session.createQuery("FROM Prestamo WHERE estado = 'solicitado'").list();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
        return lista;
	}
}
