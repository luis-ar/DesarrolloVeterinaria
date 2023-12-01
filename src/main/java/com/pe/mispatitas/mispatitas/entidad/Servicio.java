/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.entidad;

import java.io.InputStream;

/**
 *
 * @author Luis Santos
 */
public class Servicio {
    
    private Integer idServicio;
    private String nombre;
    private String descripcion;
    private InputStream foto;
        private String imagenBase64;  // Nuevo atributo


    public Servicio() {
    }

    public Servicio(Integer idServicio, String nombre, String descripcion) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Servicio(Integer idServicio, String nombre, String descripcion, InputStream foto) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

   
    
    
}
