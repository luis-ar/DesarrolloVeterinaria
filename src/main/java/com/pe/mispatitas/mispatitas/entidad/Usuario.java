
package com.pe.mispatitas.mispatitas.entidad;

/**
 *
 * @author Luis Santos
 */
public class Usuario {
    
    private Integer idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String telefono;
    private String contra;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String correoUsuario, String telefono, String contra) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.telefono = telefono;
        this.contra = contra;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
    
    
    
}
