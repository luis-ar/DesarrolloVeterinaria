package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoHistorialClinico;
import com.pe.mispatitas.mispatitas.dao.impl.DaoHistorialClinicoImpl;
import com.pe.mispatitas.mispatitas.entidad.HistorialClinico;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luis Santos
 */
@Named(value = "historialVeterinarioBean")
@RequestScoped
public class HistorialVeterinarioBean {

    private List<HistorialClinico> lista;
    private Integer idUsuario;
    private Integer idMascota;
    private Integer idHistorial;
    private Integer idEliminado;

    public HistorialVeterinarioBean() {
    }

    
    @PostConstruct

    public void init() {
        System.out.println("Bean HistorialVeterinarioBean inicializado correctamente");
        String codigoParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMascota");
        String eliminarId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idHistorial");
        idMascota = Integer.valueOf(codigoParam);
        DaoHistorialClinico dao = new DaoHistorialClinicoImpl();
        lista = dao.historialSel();
        lista.removeIf(histo -> histo.getIdMascota() != idMascota);
        lista.forEach(valor -> idHistorial = valor.getIdHistorial());
        System.out.println("nosee"+eliminarId);
        if (eliminarId!=null) {
         idEliminado = Integer.valueOf(eliminarId);
            System.out.println("elimiandoodo el usuario");

        }
        
    }

    public Integer getIdEliminado() {
        return idEliminado;
    }

    public void setIdEliminado(Integer idEliminado) {
        this.idEliminado = idEliminado;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public List<HistorialClinico> getLista() {
        return lista;
    }

    public void setLista(List<HistorialClinico> lista) {
        this.lista = lista;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String eliminarHistorial() {
        
        System.out.println("eliminando correctamente");
        return "/index.xhtml?prueba=pru&tipoUsuario=veterinario&idCodigo=" + idUsuario ;
    }

}
