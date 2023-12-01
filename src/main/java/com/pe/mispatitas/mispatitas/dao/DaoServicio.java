/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao;

import java.util.List;
import com.pe.mispatitas.mispatitas.entidad.Servicio;

/**
 *
 * @author Luis Santos
 */
public interface DaoServicio {
    List<Servicio> servicioSel();

    Servicio servicioGet(Integer id);

    String servicioIns(Servicio servicio);

    String servicioUpd(Servicio servicio);
    String servicioUpdParcial(Servicio servicio);

    String servicioDel(Integer id);

    String getMensaje();
}
