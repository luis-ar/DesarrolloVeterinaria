
package com.pe.mispatitas.mispatitas.entidad;


/**
 *
 * @author Luis Santos
 */
public class HistorialClinico {
    private Integer idHistorial;
    private Integer idMascota;
    private Integer idVeterinario;
    private String fechaHistorial;
    private String descripcion;
    private String tratamiento;
    private String nombreVeterinario;
private String nombreMascota;
    public HistorialClinico() {
    }

    public HistorialClinico(Integer idHistorial, Integer idVeterinario,Integer idMascota,  String fechaHistorial, String descripcion, String tratamiento) {
        this.idHistorial = idHistorial;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
        this.fechaHistorial = fechaHistorial;
        this.descripcion = descripcion;
        this.tratamiento = tratamiento;
    }

    public String getNombreVeterinario() {
        return nombreVeterinario;
    }

    public void setNombreVeterinario(String nombreVeterinario) {
        this.nombreVeterinario = nombreVeterinario;
    }

    
    public String getNombreMascota() {
        return nombreMascota;
    }
    

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }
    

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public Integer getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(Integer idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(String fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    
}
