/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoServicio;
import com.pe.mispatitas.mispatitas.dao.impl.DaoServicioImpl;
import com.pe.mispatitas.mispatitas.entidad.Servicio;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author Luis Santos
 */
@Named(value = "registroServicioBean")
@RequestScoped
public class RegistroServicioBean {

    private String nombreServicio;
    private String descripcionServicio;
    private Part imagenServicio;
    private int idCodigo;
    private Integer idServicio;

    public RegistroServicioBean() {
        System.out.println("Agregar mascota inicializado.");
        String codigoParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCodigo");
        String codigoParam3 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idServicio");
        if (codigoParam != null) {
            idCodigo = Integer.valueOf(codigoParam);
        }
        if (codigoParam3 != null) {
            DaoServicio dao = new DaoServicioImpl();

            idServicio = Integer.valueOf(codigoParam3);
            Servicio cat = dao.servicioGet(idServicio);
            nombreServicio = cat.getNombre();
            descripcionServicio = cat.getDescripcion();

        }
    }

    @PostConstruct
    public void init() {

    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public int getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(int idCodigo) {
        this.idCodigo = idCodigo;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public Part getImagenServicio() {
        return imagenServicio;
    }

    public void setImagenServicio(Part imagenServicio) {
        this.imagenServicio = imagenServicio;
    }

    public void registrarServicio() throws IOException {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    System.out.println("entre a la funcion");

    if (idServicio == null) {
        try {
            if (imagenServicio != null && imagenServicio.getSize() > 0) {
                try (InputStream inputStream = imagenServicio.getInputStream()) {
                    DaoServicio dao = new DaoServicioImpl();
                    Servicio cat = new Servicio(null, nombreServicio, descripcionServicio, inputStream);
                    dao.servicioIns(cat);

                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mascota registrada con éxito", null));
                    // Redirigir a la página de inicio después de registrar la mascota
                    FacesContext.getCurrentInstance().getExternalContext().redirect("servicioVeterinario.xhtml?prueba=pru&tipoUsuario=veterinario&idCodigo=" + this.idCodigo);
                }
            } else {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "La imagen es obligatoria", null));
            }
        } catch (IOException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al procesar la imagen", null));
            e.printStackTrace(); // Manejar la excepción apropiadamente
        }
    } else {
        try {
            if (imagenServicio != null && imagenServicio.getSize() > 0) {
                try (InputStream inputStream = imagenServicio.getInputStream()) {
                    DaoServicio dao = new DaoServicioImpl();
                    Servicio cat = new Servicio(idServicio, nombreServicio, descripcionServicio, inputStream);
                    dao.servicioUpd(cat);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("servicioVeterinario.xhtml?prueba=pru&tipoUsuario=veterinario&idCodigo=" + this.idCodigo);
                }
            } else {
                DaoServicio dao = new DaoServicioImpl();
                Servicio cat = new Servicio(idServicio, nombreServicio, descripcionServicio);
                dao.servicioUpdParcial(cat);
                FacesContext.getCurrentInstance().getExternalContext().redirect("servicioVeterinario.xhtml?prueba=pru&tipoUsuario=veterinario&idCodigo=" + this.idCodigo);
            }
        } catch (IOException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al procesar la imagen", null));
            e.printStackTrace(); // Manejar la excepción apropiadamente
        }
    }
}

}
