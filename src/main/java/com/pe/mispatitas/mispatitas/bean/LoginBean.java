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
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import java.util.List;
import jakarta.faces.context.FacesContext;

/**
 *
 * @author Luis Santos
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    private String correo;
    private String contrasena;
    private String tipoUsuario;

    @PostConstruct
    public void init() {
        System.out.println("HistorialBean inicializado.");
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public String iniciarSesion() {
        // Validar campos
        if (correo == null || correo.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            // Mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Todos los campos son obligatorios", null));
            return null;
        }

        // Validar correo
        if (!validarCorreo(correo)) {
            // Mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es un correo válido", null));
            return null;
        }

        // Validar usuario
        DaoUsuario daoUsuario = new DaoUsuarioImpl();
        List<Usuario> usuarios = daoUsuario.usuarioSel();
        DaoVeterinario daoVeterinario = new DaoVeterinarioImpl();
        List<Veterinario> veterinarios = daoVeterinario.veterinarioSel();
        boolean correoRegistrado = false;

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreoUsuario().equals(correo)) {
                correoRegistrado = true;
                break; // Si encontraste el correo, no necesitas seguir buscando
            }
        }
        for (Veterinario veterinario : veterinarios) {
            if (veterinario.getCorreo().equals(correo)) {
                correoRegistrado = true;
                break; // Si encontraste el correo, no necesitas seguir buscando
            }
        }
        if (!correoRegistrado) {
            // El correo no está registrado, realiza la redirección
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no encontrado", null));
            return null;
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreoUsuario().equals(correo) && usuario.getContra().equals(contrasena)) {
                // Guardar el idUsuario en la sesión (opcional)
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("codigoUsuario", usuario.getIdUsuario());
                // Redirigir a la página de usuario
                tipoUsuario = "usuario";
                System.out.println("codigo usuario" + tipoUsuario);

                return "/index?prueba=pru&tipoUsuario=usuario" + "&idCodigo=" + usuario.getIdUsuario() + "&faces-redirect=true";
            }
        }

        // Validar veterinario
        for (Veterinario veterinario : veterinarios) {
            if (veterinario.getCorreo().equals(correo) && veterinario.getContra().equals(contrasena)) {
                // Guardar el idVeterinario en la sesión (opcional)
                tipoUsuario = "veterinario";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idVeterinario", veterinario.getIdVeterinario());
                // Redirigir a la página de veterinario
                System.out.println("codigo veterinario" + veterinario.getIdVeterinario());

                return "/index?prueba=pru&tipoUsuario=veterinario&idHistorial=12&idCodigo=" + veterinario.getIdVeterinario() + "&faces-redirect=true";
            }
        }

        // Mensaje de error si no se encuentra el usuario o veterinario
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseña incorrecta", null));
        return null;
    }

    private boolean validarCorreo(String correo) {
        // Lógica de validación de correo
        // Devuelve true si el correo es válido, false de lo contrario
        // Puedes implementar tu propia lógica o usar una biblioteca de validación de correo electrónico
        return true;
    }

}
