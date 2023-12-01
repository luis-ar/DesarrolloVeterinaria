package com.pe.mispatitas.mispatitas.entidad;

import java.io.InputStream;

/**
 *
 * @author Luis Santos
 */
public class Mascota {

    private Integer idMascota;
    private Integer idUsuario;
    private String nombreMascota;
    private String fechaNacimiento;
    private String razaMascota;
    private String descripcion;
    private String sexo;
    private InputStream foto;
    private String imagenBase64;  // Nuevo atributo
    private String nombreUsuario;
    private String tratamiento;
    private String fechaHistorial;

    public Mascota() {
    }

    public Mascota(Integer idMascota, Integer idUsuario, String nombreMascota, String fechaNacimiento, String razaMascota, String descripcion, String sexo, InputStream foto) {
        this.idMascota = idMascota;
        this.idUsuario = idUsuario;
        this.nombreMascota = nombreMascota;
        this.fechaNacimiento = fechaNacimiento;
        this.razaMascota = razaMascota;
        this.descripcion = descripcion;
        this.sexo = sexo;
        this.foto = foto;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(String fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }
    

}
