package com.prestamos.exception;

import org.apache.log4j.Logger;

/**
 * Clase que implementa las excepciones de las clases DAO
 * @author esteban.salazar1
 *
 */
public class DaoException extends Exception {

    private static final Logger log = Logger.getLogger(DaoException.class);
        
    /**
     * Constructor con la excepcion basica
     */
	public DaoException() {
		log.error("Ocurrio un error en el sitema");
	}

	/**
	 * Constructor con un parametro de tipo string
	 * @param arg0 string que ayuda a describir el error
	 */
	public DaoException(String arg0) {
		super(arg0);
                log.error(arg0);
	}

	/**
	 * Constructor con un parametro de tipo Throwable
	 * @param arg0
	 */
	public DaoException(Throwable arg0) {
		super(arg0);
                log.error("Ocurrio un error en el sistema", arg0);
	}

	/**
	 * Constructor con solo 2 parametros
	 * @param arg0
	 * @param arg1
	 */
	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
                log.error("Ocurrio un error en el sistema", arg1);
	}

	/**
	 * Constructor con varios parametros que ayudan a describir mejor el error
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public DaoException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
                log.error("Ocurrio un error en el sistema", arg1);
	}

}
