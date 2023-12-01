/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao;

import com.pe.mispatitas.mispatitas.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public interface DaoUsuario {

    List<Usuario> usuarioSel();

    Usuario usuarioGet(Integer id);

    int usuarioIns(Usuario usuario);

    String usuarioUpd(Usuario usuario);

    String usuarioDel(Integer id);
    
    String calcularMD5MySQL(String contrasena);

    String getMensaje();

}
