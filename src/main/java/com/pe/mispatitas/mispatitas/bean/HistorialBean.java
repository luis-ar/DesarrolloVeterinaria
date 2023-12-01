
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoHistorialClinico;
import com.pe.mispatitas.mispatitas.dao.DaoMascota;
import com.pe.mispatitas.mispatitas.dao.DaoUsuario;
import com.pe.mispatitas.mispatitas.dao.impl.DaoHistorialClinicoImpl;
import com.pe.mispatitas.mispatitas.dao.impl.DaoMascotaImpl;
import com.pe.mispatitas.mispatitas.dao.impl.DaoUsuarioImpl;
import com.pe.mispatitas.mispatitas.entidad.HistorialClinico;
import com.pe.mispatitas.mispatitas.entidad.Mascota;
import com.pe.mispatitas.mispatitas.entidad.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
@Named(value = "historialBean")
@RequestScoped
public class HistorialBean implements Serializable {

    private List<Mascota> listaMascotaVeterinario;
private Integer idMascota;
    @PostConstruct
    public void init() {
        System.out.println("HistorialBean inicializado.");
        cargarHistorial();
    }

    public List<Mascota> getListaMascotaVeterinario() {
        return listaMascotaVeterinario;
    }

    public void setListaMascotaVeterinario(List<Mascota> listaMascotaVeterinario) {
        this.listaMascotaVeterinario = listaMascotaVeterinario;
    }

    

    public void cargarHistorial() {
        try {
             DaoMascota dao = new DaoMascotaImpl();
        DaoHistorialClinico daoHistorial = new DaoHistorialClinicoImpl();
        DaoUsuario daoUsuario = new DaoUsuarioImpl();
        listaMascotaVeterinario = dao.mascotaSel();
       for (Mascota mascota : listaMascotaVeterinario) {
            Usuario cat = daoUsuario.usuarioGet(mascota.getIdUsuario());
            HistorialClinico histo=daoHistorial.historialMascotaGet(mascota.getIdMascota());
            mascota.setNombreUsuario(cat.getNombreUsuario());
            mascota.setTratamiento(histo.getTratamiento());
            mascota.setSexo(mascota.getSexo().toUpperCase());
        }
        
        
        
        } catch (Exception e) {
            // Manejar la excepción según tus necesidades
            e.printStackTrace();
        }
    }
}
