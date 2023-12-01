/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoMascota;
import com.pe.mispatitas.mispatitas.entidad.Mascota;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Luis Santos
 */
@Named(value = "registroMascotaBean")
@RequestScoped
public class RegistroMascotaBean implements Serializable {

    private String nombreMascota;
    private String razaMascota;
    private String generoMascota;
    private LocalDate fechaMascota;
    private String descripcionMascota;
    private int idUsuario;
    private Part imagenMascota;

    public RegistroMascotaBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Agregar mascota inicializado.");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();
        String idUsuarioStr = requestParameterMap.get("idCodigo");
        if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
            idUsuario = Integer.parseInt(idUsuarioStr);
        } else {
            // Manejar el caso en que el parámetro "codigo" es nulo o vacío
            System.out.println("El parámetro 'codigo' es nulo o vacío");
        }
    }
    @Inject
    private DaoMascota daoMascota;

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }

    public String getGeneroMascota() {
        return generoMascota;
    }

    public void setGeneroMascota(String generoMascota) {
        this.generoMascota = generoMascota;
    }

    public LocalDate getFechaMascota() {
        return fechaMascota;
    }

    public void setFechaMascota(LocalDate fechaMascota) {
        this.fechaMascota = fechaMascota;
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Part getImagenMascota() {
        return imagenMascota;
    }

    public void setImagenMascota(Part imagenMascota) {
        this.imagenMascota = imagenMascota;
    }

    public DaoMascota getDaoMascota() {
        return daoMascota;
    }

    public void setDaoMascota(DaoMascota daoMascota) {
        this.daoMascota = daoMascota;
    }

    public void registrarMascota() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try (InputStream inputStream = imagenMascota.getInputStream()) {
            if (nombreMascota == null || razaMascota == null || generoMascota == null || fechaMascota == null || descripcionMascota == null) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Todos los campos son obligatorios", null));
            } else {
                Mascota mascota = new Mascota(null, idUsuario, nombreMascota, fechaMascota.toString(), razaMascota, descripcionMascota, generoMascota, inputStream);
                daoMascota.mascotaIns(mascota);

                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mascota registrada con éxito", null));

                // Redirigir a la página de inicio después de registrar la mascota
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?prueba=pru&tipoUsuario=usuario&idCodigo=" + idUsuario);

            }
        } catch (IOException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al procesar la imagen", null));
            e.printStackTrace(); // Manejar la excepción apropiadamente
        }
    }
}
