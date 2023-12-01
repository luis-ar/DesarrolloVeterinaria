/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao.impl;

import com.pe.mispatitas.mispatitas.dao.DaoServicio;
import com.pe.mispatitas.mispatitas.entidad.Servicio;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.pe.mispatitas.mispatitas.util.Conexion;

/**
 *
 * @author Luis Santos
 */
public class DaoServicioImpl implements DaoServicio {

    private final Conexion conexion;
    private String mensaje;

    public DaoServicioImpl() {
        conexion = new Conexion();
    }

    @Override
    public List<Servicio> servicioSel() {
        List<Servicio> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idServicio,")
                .append("nombre,")
                .append("descripcion,")
                .append("foto ")
                .append("FROM servicio");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setIdServicio(rs.getInt(1));
                servicio.setNombre(rs.getString(2));
                servicio.setDescripcion(rs.getString(3));
                Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    try (InputStream inputStream = blob.getBinaryStream()) {
                        servicio.setFoto(inputStream);
                    }
                }
                lista.add(servicio);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return lista;
    }

    @Override
    public Servicio servicioGet(Integer id) {

        Servicio servicio = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idServicio,")
                .append("nombre,")
                .append("descripcion,")
                .append("foto ")
                .append("FROM servicio ")
                .append("WHERE idServicio = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            servicio = new Servicio();
            if (rs.next()) {
                servicio.setIdServicio(rs.getInt(1));
                servicio.setNombre(rs.getString(2));
                servicio.setDescripcion(rs.getString(3));
                servicio.setFoto((InputStream) rs.getBlob(4));
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return servicio;

    }

    @Override
    public String servicioIns(Servicio servicio) {
 StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO servicio(")
                .append("nombre,")
                .append("descripcion,")
                .append("foto ")
                .append(") VALUES (?,?,?)");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setBlob(3, servicio.getFoto());
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
                mensaje = "Cero registros agregados";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return mensaje;

    }

    @Override
    public String servicioUpd(Servicio servicio) {

  StringBuilder sql = new StringBuilder();
        sql.append("UPDATE servicio SET ")
                .append("nombre = ?,")
                .append("descripcion = ?,")
                .append("foto = ? ")
                .append("WHERE idServicio = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setBlob(3, servicio.getFoto());
            ps.setInt(4, servicio.getIdServicio());
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
                mensaje = "Cero registros actualizados";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return mensaje;

    }

    
    @Override
    public String servicioUpdParcial(Servicio servicio) {

         System.out.println("antes de actualizar");
        
  StringBuilder sql = new StringBuilder();
        sql.append("UPDATE servicio SET ")
                .append("nombre = ?,")
                .append("descripcion = ? ")
                .append("WHERE idServicio = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setInt(3, servicio.getIdServicio());
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
                mensaje = "Cero registros actualizados";
                System.out.println("SIN REGISTRO");
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return mensaje;

    }
   @Override
    public String servicioDel(Integer id) {

 StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM servicio")
                .append(" WHERE idServicio = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            int resultado = ps.executeUpdate();
            if (resultado == 0) {
                mensaje = "Cero registros eliminados";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return mensaje;

    }

    @Override
    public String getMensaje() {
        return mensaje;
    }

}
