/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao;

import com.pe.mispatitas.mispatitas.entidad.Contacto;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public interface DaoContacto {
     List<Contacto> contactoSel();

    Contacto contactoGet(Integer id);

    String contactoIns(Contacto contacto);

    String contactoUpd(Contacto contacto);

    String contactoDel(Integer id);

    String getMensaje();
}
