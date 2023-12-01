/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoContacto;
import com.pe.mispatitas.mispatitas.dao.DaoUsuario;
import com.pe.mispatitas.mispatitas.dao.impl.DaoContactoImpl;
import com.pe.mispatitas.mispatitas.dao.impl.DaoUsuarioImpl;
import com.pe.mispatitas.mispatitas.entidad.Contacto;
import com.pe.mispatitas.mispatitas.entidad.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Luis Santos
 */
@Named(value = "contactoUsuarioBean")
@ViewScoped
public class ContactoUsuarioBean implements Serializable  {

    private String mensaje;
    private Integer idUsuario;
    private Integer codigoUsuario;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        cargaDatos();
        System.out.println("mi correo"+usuario.getCorreoUsuario());


    }
    public void  cargaDatos(){
        String codigoParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCodigo");

        if (codigoParam != null && !codigoParam.isEmpty()) {
            System.out.println("carganddodododo");
            idUsuario = Integer.valueOf(codigoParam);
            DaoUsuario daoUsuario = new DaoUsuarioImpl();
            List<Usuario> usuarios = daoUsuario.usuarioSel();

            for (Usuario u : usuarios) {
                if (Objects.equals(u.getIdUsuario(), idUsuario)) {
                    this.usuario = u;
                    break;
                }
            }

        }
    }

     public void enviarMensaje() {
        // No es necesario realizar la validación aquí
        DaoContacto dao = new DaoContactoImpl();
        Contacto cat = new Contacto(null, idUsuario, mensaje);
        System.out.println(dao.contactoIns(cat));
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?prueba=pru&tipoUsuario=usuario&idCodigo=" + idUsuario);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción apropiadamente
        }
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
