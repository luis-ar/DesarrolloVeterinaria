/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.pe.mispatitas.mispatitas.test;

import com.pe.mispatitas.mispatitas.dao.DaoContacto;
import com.pe.mispatitas.mispatitas.dao.impl.DaoContactoImpl;
import com.pe.mispatitas.mispatitas.entidad.Contacto;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public class TextCrudContacto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DaoContacto dao = new DaoContactoImpl();

        // Probar SELECT
//        try {
//            List<Contacto> lista = dao.contactoSel();
//            lista.forEach(t->System.out.println(t.getNombreUsuario()));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
        // Probar SELECT
//        try {
//            Mascota cat = dao.mascotaGet(2);
//            System.out.println(cat.getNombreMascota());
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
//       
        // Probar INSERT
        try {
            Contacto cat = new Contacto(null, 55, "Prueba del contacto111");
            System.out.println(dao.contactoIns(cat));
        } catch (Exception e) {
            dao.getMensaje();
        }
        // Probar UPDATE
//        try {
//            Mascota cat = new Mascota(1, 1, "laycajr", "2023-10-15", "aleman", "holamundo22", "m", "holamundo2");
//            System.out.println(dao.mascotaUpd(cat));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
//probar delete
//        try {
//            System.out.println(dao.mascotaDel(4));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
    }

}
