/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao.impl;

import com.pe.mispatitas.mispatitas.dao.DaoContacto;
import com.pe.mispatitas.mispatitas.entidad.Contacto;
import java.util.List;
import com.pe.mispatitas.mispatitas.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Luis Santos
 */
public class DaoContactoImpl implements DaoContacto {

    private final Conexion conexion;
    private String mensaje;

    public DaoContactoImpl() {
        conexion = new Conexion();
    }

    @Override
    public List<Contacto> contactoSel() {

        List<Contacto> lista = null;
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT ")
            .append("c.idContacto,")
            .append("c.idUsuario,")
            .append("c.mensaje,")
            .append("u.nombreUsuario,")
            .append("u.telefono ")
            .append("FROM contacto c ")
            .append("INNER JOIN usuario u ON c.idUsuario = u.idUsuario");
    try (Connection cn = conexion.getConexion()) {
        PreparedStatement ps = cn.prepareStatement(sql.toString());
        ResultSet rs = ps.executeQuery();
        lista = new ArrayList<>();
        while (rs.next()) {
            Contacto contactoConUsuario = new Contacto();
            contactoConUsuario.setIdContacto(rs.getInt(1));
            contactoConUsuario.setIdUsuario(rs.getInt(2));
            contactoConUsuario.setMensaje(rs.getString(3));
            contactoConUsuario.setNombreUsuario(rs.getString(4));
            contactoConUsuario.setTelefonoUsuario(rs.getString(5));
            lista.add(contactoConUsuario);
        }
    } catch (Exception e) {
        mensaje = e.getMessage();
    }
    return lista;

    }

    @Override
    public Contacto contactoGet(Integer id) {

  Contacto contacto = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idContacto,")
                .append("idUsuario,")
                .append("mensaje ")
                .append("FROM contacto ")
                .append("WHERE idContacto = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            contacto = new Contacto();
            if (rs.next()) {
                contacto.setIdContacto(rs.getInt(1));
                contacto.setIdUsuario(rs.getInt(2));
                contacto.setMensaje(rs.getString(3));
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return contacto;


    }

    @Override
    public String contactoIns(Contacto contacto) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO contacto(")
                .append("idUsuario,")
                .append("mensaje ")
                .append(") VALUES (?,?)");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, contacto.getIdUsuario());
            ps.setString(2, contacto.getMensaje());
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
    public String contactoUpd(Contacto contacto) {

StringBuilder sql = new StringBuilder();
        sql.append("UPDATE contacto SET ")
                .append("idUsuario = ?,")
                .append("mensaje = ? ")
                .append("WHERE idContacto = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, contacto.getIdUsuario());
            ps.setString(2, contacto.getMensaje());
            ps.setInt(3, contacto.getIdContacto());
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
    public String contactoDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM contacto")
                .append(" WHERE idContacto = ?");
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

return  mensaje;
    }

}
