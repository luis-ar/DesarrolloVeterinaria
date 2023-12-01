/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.pe.mispatitas.mispatitas.test;

import com.pe.mispatitas.mispatitas.dao.DaoHistorialClinico;
import com.pe.mispatitas.mispatitas.dao.impl.DaoHistorialClinicoImpl;
import com.pe.mispatitas.mispatitas.entidad.HistorialClinico;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public class TextCrudHistorial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DaoHistorialClinico dao = new DaoHistorialClinicoImpl();
 // Probar SELECT
//        try {
//            List<HistorialClinico> lista = dao.historialSel();
//            lista.forEach(t->System.out.println(t.getIdHistorial()+"--"+t.getTratamiento()));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
        
//        try {
//            HistorialClinico cat=dao.historialMascotaGet(36);
//             System.out.println(cat.getTratamiento());
//
//        } catch (Exception e) {
//        }

        // Probar SELECT
        try {
            HistorialClinico cat = dao.historialGet(22);
            System.out.println(cat.getFechaHistorial());
        } catch (Exception e) {
            dao.getMensaje();
        }

//       
        // Probar INSERT
//        try {
//            HistorialClinico cat = new HistorialClinico(null, 1, 1, "2023-11-02", "hola mundo", "hola mundo");
//            System.out.println(dao.historialIns(cat));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }

//       // Probar UPDATE
//        try {
//            HistorialClinico cat = new HistorialClinico(2, 1, 1, "2011-10-1", "holamundo11", "holamundo11");
//            System.out.println(dao.historialUpd(cat));
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
