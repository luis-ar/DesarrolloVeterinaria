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
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luis Santos
 */
@Named(value = "historialUsuarioBean")
@RequestScoped
public class HistorialUsuarioBean implements Serializable {

    private List<Mascota> listaMascota;
    private Integer idUsuario;

    @PostConstruct
    public void init() {
        System.out.println("entro al historiiaiai");
        cargarHistorialUsuario();
    }

    public List<Mascota> getListaMascota() {
        return listaMascota;
    }

    public void setListaMascota(List<Mascota> listaMascota) {
        this.listaMascota = listaMascota;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void cargarHistorialUsuario() {
        try {
            DaoMascota dao = new DaoMascotaImpl();
            DaoHistorialClinico daoHistorial = new DaoHistorialClinicoImpl();
            FacesContext facesContext = FacesContext.getCurrentInstance();

            ExternalContext externalContext = facesContext.getExternalContext();

            listaMascota = dao.mascotaSel();
// Obtener el idUsuario desde la URL
            Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();
            String idUsuarioStr = requestParameterMap.get("idCodigo");
            idUsuario = Integer.parseInt(idUsuarioStr);
            for (Mascota mascota : listaMascota) {
                HistorialClinico histo = daoHistorial.historialMascotaGet(mascota.getIdMascota());
                mascota.setFechaHistorial(histo.getFechaHistorial());
                mascota.setTratamiento(histo.getTratamiento());

            }
            
            listaMascota.removeIf(mascota -> mascota.getIdUsuario()!= idUsuario);

            System.out.println("mi codigo" + idUsuario);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
