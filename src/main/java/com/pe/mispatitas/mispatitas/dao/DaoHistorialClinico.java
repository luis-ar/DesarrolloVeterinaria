/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao;

import com.pe.mispatitas.mispatitas.entidad.HistorialClinico;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public interface DaoHistorialClinico {
     List<HistorialClinico> historialSel();

    HistorialClinico historialGet(Integer id);
    HistorialClinico historialMascotaGet(Integer id);

    String historialIns(HistorialClinico historial);

    String historialUpd(HistorialClinico historial);

    String historialDel(Integer id);

    String getMensaje();
}
