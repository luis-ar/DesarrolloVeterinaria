/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.pe.mispatitas.mispatitas.bean;

import com.pe.mispatitas.mispatitas.dao.DaoContacto;
import com.pe.mispatitas.mispatitas.dao.impl.DaoContactoImpl;
import com.pe.mispatitas.mispatitas.entidad.Contacto;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
@Named(value = "mensajesBean")
@RequestScoped
public class MensajesBean {
private List<Contacto> lista;

@PostConstruct
    public void init() {
        DaoContacto dao = new DaoContactoImpl();

        lista = dao.contactoSel();
    }

    public List<Contacto> getLista() {
        return lista;
    }

    public void setLista(List<Contacto> lista) {
        this.lista = lista;
    }

    
    
}
