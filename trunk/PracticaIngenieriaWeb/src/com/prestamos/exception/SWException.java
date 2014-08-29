/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prestamos.exception;

import org.apache.log4j.Logger;

/**
 * Clase que implementa los metodos de manejo de errores de los servicios web
 * @author Juan Carlos
 */
public class SWException extends Exception{
    
    private static final Logger log = Logger.getLogger(DaoException.class);
        
    /**
     * Constructor que maneja el error de manera basica
     */
    public SWException() {
		log.error("Ocurrio un error en el sitema");
	}

    /**
	 * Constructor con 1 parametro que ayuda a identificar el error
	 * @param arg0
	 */
	public SWException(String arg0) {
		super(arg0);
                log.error(arg0);
	}

	/**
	 * Constructor con 1 parametro que ayuda a identificar el error
	 * @param arg0
	 */
	public SWException(Throwable arg0) {
		super(arg0);
                log.error("Ocurrio un error en el sistema", arg0);
	}

	/**
	 * Constructor con 2 parametros que ayudan a identificar el error
	 * @param arg0
	 * @param arg1
	 */
	public SWException(String arg0, Throwable arg1) {
		super(arg0, arg1);
                log.error("Ocurrio un error en el sistema", arg1);
	}

	/**
	 * Constructor con 4 parametros que ayudan a identificar el error
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public SWException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
                log.error("Ocurrio un error en el sistema", arg1);
	}

}
