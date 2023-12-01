/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoHistorialClinico;
import com.pe.mispatitas.mispatitas.dao.DaoMascota;
import com.pe.mispatitas.mispatitas.dao.impl.DaoHistorialClinicoImpl;
import com.pe.mispatitas.mispatitas.dao.impl.DaoMascotaImpl;
import com.pe.mispatitas.mispatitas.entidad.HistorialClinico;
import com.pe.mispatitas.mispatitas.entidad.Mascota;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
@Named(value = "reporteBean")
@RequestScoped
public class ReporteBean {

    private Integer idHistorial;
    private List<HistorialClinico> listaHistorial;

    @PostConstruct

    public void init() {
        System.out.println("Bean reporte inicializado correctamente");

        DaoHistorialClinico dao = new DaoHistorialClinicoImpl();
        String codigoParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idHistorial");
        if (codigoParam != null) {
            idHistorial = Integer.valueOf(codigoParam);
            System.out.println("eliminando correctamente");
            System.out.println(dao.historialDel(idHistorial));
        }
        DaoMascota daoMascota = new DaoMascotaImpl();
        listaHistorial = dao.historialSel();
        for (HistorialClinico historial : listaHistorial) {

            Mascota cat = daoMascota.mascotaGet(historial.getIdMascota());
            historial.setNombreMascota(cat.getNombreMascota());
        }

        

    }

    public List<HistorialClinico> getListaHistorial() {
        return listaHistorial;
    }

    public void setListaHistorial(List<HistorialClinico> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

}
