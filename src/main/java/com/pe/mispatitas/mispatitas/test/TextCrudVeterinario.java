/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.pe.mispatitas.mispatitas.test;

import com.pe.mispatitas.mispatitas.dao.DaoVeterinario;
import com.pe.mispatitas.mispatitas.dao.impl.DaoVeterinarioImpl;
import com.pe.mispatitas.mispatitas.entidad.Veterinario;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public class TextCrudVeterinario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DaoVeterinario dao = new DaoVeterinarioImpl();
 // Probar SELECT
        try {
            List<Veterinario> lista = dao.veterinarioSel();
            lista.forEach(t->System.out.println(t.getCorreo()+"--"+t.getContra()));
        } catch (Exception e) {
            dao.getMensaje();
        }

        // Probar SELECT
//        try {
//            Veterinario cat = dao.veterinarioGet(1);
//            System.out.println(cat.getNombreVeterinario());
//        } catch (Exception e) {
//            dao.getMensaje();
//        }

   
        // Probar INSERT
//        try {
//            Veterinario cat = new Veterinario(null, "Antonella", "antonella@gmail.com", "123456");
//            System.out.println(dao.veterinarioIns(cat));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }

//       // Probar UPDATE
//        try {
//            Veterinario cat = new Veterinario(2, "Antonella jr", "antonella@gmail.com", "123456");
//            System.out.println(dao.veterinarioUpd(cat));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
        //probar delete
//        try {
//            System.out.println(dao.veterinarioDel(2));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }


    }
    
}
