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
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
@Named(value = "servicioBean")
@RequestScoped
public class ServicioBean implements Serializable {

    private Integer idServicio;
    private Integer idCodigo;

    private List<Servicio> listaServicio;
    @Inject
    @jakarta.faces.annotation.ManagedProperty(value = "#{param.parametro}")
    private String parametro;

    @PostConstruct
    public void init() {
        System.out.println("HistorialBean inicializado.");

        cargarServicios();
        String codigoParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCodigo");
        System.out.println(codigoParam);
        if (codigoParam != null) {
            idCodigo = Integer.valueOf(codigoParam);
            System.out.println("enenenenn");
        }
    }

    public Integer getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(Integer idCodigo) {
        this.idCodigo = idCodigo;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public List<Servicio> getListaServicio() {
        return listaServicio;
    }

    public String eliminarServicio() {
        System.out.println("Parámetro 1: " + parametro);
        System.out.println("Parámetro 12: " + this.idCodigo);
        DaoServicio dao = new DaoServicioImpl();
        Integer nuevo = Integer.parseInt(parametro);
        dao.servicioDel(nuevo);
        return "/servicioVeterinario.xhtml?prueba=pru&tipoUsuario=veterinario" + "&faces-redirect=true";

    }

    public void cargarServicios() {
        try {
            DaoServicio dao = new DaoServicioImpl();
            listaServicio = dao.servicioSel();

            for (Servicio servicio : listaServicio) {
                InputStream inputStream = servicio.getFoto();
                byte[] bytes = inputStream.readAllBytes();
                String imagenBase64 = Base64.getEncoder().encodeToString(bytes);
                servicio.setImagenBase64(imagenBase64);
            }
        } catch (Exception e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
        }
    }
}
