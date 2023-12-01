/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.pe.mispatitas.mispatitas.test;

import com.pe.mispatitas.mispatitas.dao.DaoUsuario;
import com.pe.mispatitas.mispatitas.dao.impl.DaoUsuarioImpl;
import com.pe.mispatitas.mispatitas.entidad.Usuario;
import java.util.List;


/**
 *
 * @author Luis Santos
 */
public class TextCrudUsuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DaoUsuario dao = new DaoUsuarioImpl();
//  Probar SELECT
//        try {
//            List<Usuario> lista = dao.usuarioSel();
//            lista.forEach(t->System.out.println(t.getIdUsuario()+"--"+t.getContra()));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }

        // Probar SELECT
        try {
            Usuario cat = dao.usuarioGet(55);
            System.out.println(cat.getContra());
        } catch (Exception e) {
            dao.getMensaje();
        }

   
        // Probar INSERT
//        try {
//            Usuario cat = new Usuario(null, "messi", "messi@gmail.com", "947954713", "123456");
//            System.out.println(dao.usuarioIns(cat));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }

//       // Probar UPDATE
//        try {
//            Usuario cat = new Usuario(3, "rubenjr", "ruben@gmail.com", "947954713", "123456");
//            System.out.println(dao.usuarioUpd(cat));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }
        //probar delete
//        try {
//            System.out.println(dao.usuarioDel(3));
//        } catch (Exception e) {
//            dao.getMensaje();
//        }


    }
    
}
