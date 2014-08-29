package com.prestamos.dto;
// Generated 17/07/2014 03:01:05 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Clase que modela el objeto TipoUsuario
 * TipoUsuario generated by hbm2java
 */
public class TipoUsuario  implements java.io.Serializable {


     private int codigo;
     private String tipo;
     @JsonIgnore
     private Set usuarios = new HashSet(0);

     /**
      * Constructor vacio 
      */
    public TipoUsuario() {
    }


    /**
     * Constructor con solo el tipo
     * @param tipo de usuario 
     */	
    public TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Constructor con todos los parametros
     * @param tipo de usuario 
     * @param usuarios lista de usuarios que pertenecen a este tipo
     */
    public TipoUsuario(String tipo, Set usuarios) {
       this.tipo = tipo;
       this.usuarios = usuarios;
    }

   /**
     * Getter
     * @return el codigo del tipoUsuario
     */
    public Integer getCodigo() {
        return this.codigo;
    }

    /**
     * Setter
     * @param codigo que se asigna al tipo de usuarios
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Getter
     * @return el tipo de usuario
     */
    public String getTipo() {
        return this.tipo;
    }
    

    /**
     * Setter
     * @param tipo de usuario que se asigna
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

 
    /**
     * Getter
     * @return los usuarios que pertenecena este tipo
     */
    public Set getUsuarios() {
        return this.usuarios;
    }
    

    /**
     * Setter
     * @param usuarios que pertenecen al tipo
     */
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}


