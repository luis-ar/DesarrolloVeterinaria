/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao;

import com.pe.mispatitas.mispatitas.entidad.Veterinario;
import java.util.List;

/**
 *
 * @author Luis Santos
 */
public interface DaoVeterinario {
     List<Veterinario> veterinarioSel();

    Veterinario veterinarioGet(Integer id);

    String veterinarioIns(Veterinario veterinario);

    String veterinarioUpd(Veterinario veterinario);
    String calcularMD5MySQL(String contrasena);

    String veterinarioDel(Integer id);

    String getMensaje();
}
