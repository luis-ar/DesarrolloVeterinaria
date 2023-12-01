/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoUsuario;
import com.pe.mispatitas.mispatitas.dao.DaoVeterinario;
import com.pe.mispatitas.mispatitas.dao.impl.DaoUsuarioImpl;
import com.pe.mispatitas.mispatitas.dao.impl.DaoVeterinarioImpl;
import com.pe.mispatitas.mispatitas.entidad.Usuario;
import com.pe.mispatitas.mispatitas.entidad.Veterinario;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
@Named(value = "registrarUsuarioBean")
@RequestScoped
public class RegistrarUsuarioBean {

    private String correo;
    private String contrasena;
    private String telefono;
    private String nombre;

    @PostConstruct
    public void init() {
        System.out.println("Registro usuario inicializado.");
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String registrarUsuario() {
        DaoUsuario dao = new DaoUsuarioImpl();
        List<Usuario> usuarios = dao.usuarioSel();
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreoUsuario().equals(correo)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo ya existente", null));
                return null;
            }
        }

        DaoVeterinario daoVeterinario = new DaoVeterinarioImpl();
        List<Veterinario> veterinarios = daoVeterinario.veterinarioSel();
        for (Veterinario veterinario : veterinarios) {
            if (veterinario.getCorreo().equals(correo)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo ya existente", null));
                return null;
            }
        }
        
        Usuario cat = new Usuario(null, nombre, correo, telefono, contrasena);
        int idUsuario = dao.usuarioIns(cat);
        return "/agregarMascotaUsuario.xhtml?idCodigo=" + idUsuario+ "&faces-redirect=true";
    }

}
