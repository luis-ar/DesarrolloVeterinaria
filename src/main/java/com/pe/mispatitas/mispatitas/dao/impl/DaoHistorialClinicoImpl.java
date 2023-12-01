/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao.impl;

import com.pe.mispatitas.mispatitas.dao.DaoHistorialClinico;
import com.pe.mispatitas.mispatitas.entidad.HistorialClinico;
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
public class DaoHistorialClinicoImpl implements DaoHistorialClinico {

    private final Conexion conexion;
    private String mensaje;

    public DaoHistorialClinicoImpl() {
        conexion = new Conexion();
    }

    @Override
    public List<HistorialClinico> historialSel() {

        List<HistorialClinico> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idHistorial,")
                .append("idVeterinario,")
                .append("idMascota,")
                .append("fechaHistorial,")
                .append("descripcion,")
                .append("tratamiento ")
                .append("FROM historial");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                HistorialClinico historialClinico = new HistorialClinico();
                historialClinico.setIdHistorial(rs.getInt(1));
                historialClinico.setIdVeterinario(rs.getInt(2));
                historialClinico.setIdMascota(rs.getInt(3));
                historialClinico.setFechaHistorial(rs.getString(4));
                historialClinico.setDescripcion(rs.getString(5));
                historialClinico.setTratamiento(rs.getString(6));
                lista.add(historialClinico);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return lista;
    }

    @Override
    public HistorialClinico historialGet(Integer id) {

        HistorialClinico historialClinico = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idHistorial,")
                .append("idVeterinario,")
                .append("idMascota,")
                .append("fechaHistorial,")
                .append("descripcion,")
                .append("tratamiento ")
                .append("FROM historial ")
                .append("WHERE idHistorial = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            historialClinico = new HistorialClinico();
            if (rs.next()) {
                historialClinico.setIdHistorial(rs.getInt(1));
                historialClinico.setIdVeterinario(rs.getInt(2));
                historialClinico.setIdMascota(rs.getInt(3));
                historialClinico.setFechaHistorial(rs.getString(4));
                historialClinico.setDescripcion(rs.getString(5));
                historialClinico.setTratamiento(rs.getString(6));
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return historialClinico;

    }

    @Override
    public String historialIns(HistorialClinico historial) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO historial(")
                .append("idVeterinario,")
                .append("idMascota,")
                .append("fechaHistorial,")
                .append("descripcion,")
                .append("tratamiento ")
                .append(") VALUES (?,?,?,?,?)");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, historial.getIdVeterinario());
            ps.setInt(2, historial.getIdMascota());
            ps.setString(3, historial.getFechaHistorial());
            ps.setString(4, historial.getDescripcion());
            ps.setString(5, historial.getTratamiento());
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
    public String historialUpd(HistorialClinico historial) {

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE historial SET ")
                .append("idVeterinario = ?,")
                .append("idMascota = ?,")
                .append("fechaHistorial = ?,")
                .append("descripcion = ?,")
                .append("tratamiento = ? ")
                .append("WHERE idHistorial = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, historial.getIdVeterinario());
            ps.setInt(2, historial.getIdMascota());
            ps.setString(3, historial.getFechaHistorial());
            ps.setString(4, historial.getDescripcion());
            ps.setString(5, historial.getTratamiento());
            ps.setInt(6, historial.getIdHistorial());
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
    public String historialDel(Integer id) {

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM historial")
                .append(" WHERE idHistorial = ?");
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

    @Override
    public HistorialClinico historialMascotaGet(Integer id) {

        HistorialClinico historialClinico = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idHistorial,")
                .append("idVeterinario,")
                .append("idMascota,")
                .append("fechaHistorial,")
                .append("descripcion,")
                .append("tratamiento ")
                .append("FROM historial ")
                .append("WHERE idMascota = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            historialClinico = new HistorialClinico();
            if (rs.next()) {
                historialClinico.setIdHistorial(rs.getInt(1));
                historialClinico.setIdVeterinario(rs.getInt(2));
                historialClinico.setIdMascota(rs.getInt(3));
                historialClinico.setFechaHistorial(rs.getString(4));
                historialClinico.setDescripcion(rs.getString(5));
                historialClinico.setTratamiento(rs.getString(6));
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return historialClinico;

    }
}
