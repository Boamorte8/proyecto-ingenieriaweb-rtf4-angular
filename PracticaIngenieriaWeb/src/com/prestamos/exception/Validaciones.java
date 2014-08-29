package com.prestamos.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Clase que se encarga de validar campos en los formularios como el correo
 * @author esteban.salazar1
 *
 */
public class Validaciones {

	/**
	 * Valida que el correo electr�nico establecido como par�metro sea un correo electr�nico con formato v�lido
	 * @param correo texto con el correo electr�nico a validar
	 * @return true si el texto tiene un formato de correo electr�nico v�lido, de lo contrario retorna false
	 */
    public static boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        
        if("".equals(correo))
        	return false;
        
        mat = pat.matcher(correo);
        if (mat.find()) {            
            return true;
        }else{
            return false;
        }        
    }

}
