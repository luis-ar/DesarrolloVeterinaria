/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.entidad;

/**
 *
 * @author Luis Santos
 */
public class Veterinario {
    private Integer idVeterinario;
    private String nombreVeterinario;
    private String correo;
    private String contra;

    public Veterinario() {
    }

    public Veterinario(Integer idVeterinario, String nombreVeterinario, String correo, String contra) {
        this.idVeterinario = idVeterinario;
        this.nombreVeterinario = nombreVeterinario;
        this.correo = correo;
        this.contra = contra;
    }

    public Integer getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Integer idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombreVeterinario() {
        return nombreVeterinario;
    }

    public void setNombreVeterinario(String nombreVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
    
    
}
