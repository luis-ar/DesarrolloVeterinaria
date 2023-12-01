/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoHistorialClinico;
import com.pe.mispatitas.mispatitas.dao.impl.DaoHistorialClinicoImpl;
import com.pe.mispatitas.mispatitas.entidad.HistorialClinico;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Luis Santos
 */
@Named(value = "registrarHistorial")
@ViewScoped
public class RegistrarHistorial implements Serializable {

    private Integer idCodigo;
    private Integer idMascota;
    private Integer idHistorial;
    private String sintomas;
    private String tratamiento;
    private String fechaHistorial;

    public RegistrarHistorial() {
        System.out.println("Bean HistorialVeterinarioBean inicializado correctamente");
        String codigoParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMascota");
        String codigoParam2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCodigo");
        String codigoParam3 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idHistorial");
        if (codigoParam != null) {
            idMascota = Integer.valueOf(codigoParam);
            idCodigo = Integer.valueOf(codigoParam2);
        }
        if (codigoParam3 != null) {
            DaoHistorialClinico dao = new DaoHistorialClinicoImpl();

            idHistorial = Integer.valueOf(codigoParam3);
            HistorialClinico cat = dao.historialGet(idHistorial);
            tratamiento=cat.getTratamiento();
            sintomas=cat.getDescripcion();
            fechaHistorial=cat.getFechaHistorial();

        }
    }

    @PostConstruct
    public void init() {

    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(Integer idCodigo) {
        this.idCodigo = idCodigo;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(String fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public String registrarHistorial() {
        if (idHistorial == null) {
            DaoHistorialClinico dao = new DaoHistorialClinicoImpl();
            HistorialClinico cat = new HistorialClinico(null, idCodigo, idMascota, fechaHistorial, sintomas, tratamiento);
            System.out.println(dao.historialIns(cat));
            return "/index.xhtml?prueba=pru&tipoUsuario=veterinario&idCodigo=" + idCodigo + "&faces-redirect=true";

        } else {
            DaoHistorialClinico dao = new DaoHistorialClinicoImpl();
            HistorialClinico cat = new HistorialClinico(idHistorial, idCodigo, idMascota, fechaHistorial, sintomas, tratamiento);
            System.out.println(dao.historialUpd(cat));
            return "/index.xhtml?prueba=pru&tipoUsuario=veterinario&idCodigo=" + idCodigo + "&faces-redirect=true";
        }

    }
}
