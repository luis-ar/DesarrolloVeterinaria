/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoMascota;
import com.pe.mispatitas.mispatitas.dao.impl.DaoMascotaImpl;
import com.pe.mispatitas.mispatitas.entidad.Mascota;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Luis Santos
 */
@Named("mascotaBean")
@RequestScoped
public class MascotaBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    private List<Mascota> listaMascota;
    private Integer idUsuario;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

// Obtener el idUsuario desde la URL
        Map<String, String> requestParameterMap = externalContext.getRequestParameterMap();
        String idUsuarioStr = requestParameterMap.get("idCodigo");

        // Validar que el idUsuario sea un número
        try {
            idUsuario = Integer.parseInt(idUsuarioStr);
        } catch (NumberFormatException e) {
            // Manejar la excepción si es necesario
            e.printStackTrace();
        }
        // Lógica para obtener la lista de mascotas según el tipo de usuario
        DaoMascota dao = new DaoMascotaImpl();
        listaMascota = dao.mascotaSel();

        // Lógica para convertir las imágenes a Base64
        for (Mascota mascota : listaMascota) {
            try (InputStream inputStream = mascota.getFoto()) {
                byte[] bytes = inputStream.readAllBytes();
                String imagenBase64 = Base64.getEncoder().encodeToString(bytes);
                mascota.setImagenBase64(imagenBase64);
            } catch (IOException e) {
                // Manejar excepciones si es necesario
                e.printStackTrace();
            }
        }
        listaMascota.removeIf(mascota -> mascota.getIdUsuario() != idUsuario);

    }

    // Getter para listaMascota
    public List<Mascota> getListaMascota() {
        return listaMascota;
    }

    // Getter para idUsuario
    public Integer getIdUsuario() {
        return idUsuario;
    }
}
