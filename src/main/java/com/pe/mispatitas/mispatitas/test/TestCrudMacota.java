/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.pe.mispatitas.mispatitas.test;

import com.pe.mispatitas.mispatitas.dao.DaoMascota;
import com.pe.mispatitas.mispatitas.dao.DaoServicio;
import com.pe.mispatitas.mispatitas.dao.impl.DaoMascotaImpl;
import com.pe.mispatitas.mispatitas.dao.impl.DaoServicioImpl;
import com.pe.mispatitas.mispatitas.entidad.Mascota;
import com.pe.mispatitas.mispatitas.entidad.Servicio;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public class TestCrudMacota {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 
        DaoMascota dao = new DaoMascotaImpl();
        DaoServicio dao1 = new DaoServicioImpl();
        // Probar SELECT
//        try {
//            List<Mascota> lista = dao.mascotaSel();
//            lista.forEach(t->System.out.println(t.getIdMascota()+"--"+t.getFoto()));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
        try {
            List<Servicio> lista1 = dao1.servicioSel();
            lista1.forEach(t->System.out.println(t.getIdServicio()+"--"+t.getFoto()));
        } catch (Exception e) {
            dao.getMensaje();
        }

        // Probar SELECT
//        try {
//            Mascota cat = dao.mascotaGet(3);
//            System.out.println(cat.getNombreMascota());
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
//       
        // Probar INSERT
//        try {
//            Mascota cat = new Mascota(null, 1, "dori","2023-10-11", "payaso", "holamundo", "f", "hola");
//            System.out.println(dao.mascotaIns(cat));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
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
