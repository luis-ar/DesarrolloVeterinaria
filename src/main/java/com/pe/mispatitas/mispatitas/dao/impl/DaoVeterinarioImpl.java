/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao.impl;

import com.pe.mispatitas.mispatitas.dao.DaoVeterinario;
import com.pe.mispatitas.mispatitas.entidad.Veterinario;
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
public class DaoVeterinarioImpl implements DaoVeterinario {

    private final Conexion conexion;
    private String mensaje;

    public DaoVeterinarioImpl() {
        conexion = new Conexion();
    }

    @Override
    public List<Veterinario> veterinarioSel() {

        List<Veterinario> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idVeterinario,")
                .append("nombreVeterinario,")
                .append("correo,")
                .append("CAST(AES_DECRYPT(contraseña,'AES') AS CHAR) ")
                .append("FROM veterinario");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setIdVeterinario(rs.getInt(1));
                veterinario.setNombreVeterinario(rs.getString(2));
                veterinario.setCorreo(rs.getString(3));
                veterinario.setContra(rs.getString(4));
                lista.add(veterinario);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return lista;

    }

    @Override
    public Veterinario veterinarioGet(Integer id) {

        Veterinario veterinario = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idVeterinario,")
                .append("nombreVeterinario,")
                .append("correo,")
                .append("CAST(AES_DECRYPT(contraseña,'AES') AS CHAR) ")
                .append("FROM veterinario ")
                .append("WHERE idVeterinario = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            veterinario = new Veterinario();
            if (rs.next()) {
                veterinario.setIdVeterinario(rs.getInt(1));
                veterinario.setNombreVeterinario(rs.getString(2));
                veterinario.setCorreo(rs.getString(3));
                veterinario.setContra(rs.getString(4));
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return veterinario;

    }

    @Override
    public String veterinarioIns(Veterinario veterinario) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO veterinario(")
                .append("nombreVeterinario,")
                .append("correo,")
                .append("contraseña ")
                .append(") VALUES (?,?,AES_ENCRYPT(?,'AES'))");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, veterinario.getNombreVeterinario());
            ps.setString(2, veterinario.getCorreo());
            ps.setString(3, veterinario.getContra());
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
    public String veterinarioUpd(Veterinario veterinario) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE veterinario SET ")
                .append("nombreVeterinario = ?,")
                .append("correo = ?,")
                .append("contraseña = ? ")
                .append("WHERE idVeterinario = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, veterinario.getNombreVeterinario());
            ps.setString(2, veterinario.getCorreo());
            ps.setString(3, veterinario.getContra());
            ps.setInt(4, veterinario.getIdVeterinario());
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
    public String veterinarioDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM veterinario")
                .append(" WHERE idVeterinario = ?");
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
    public String calcularMD5MySQL(String contrasena) {
        String sql = "SELECT MD5(?) AS hash_md5";
        try (Connection cn = conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("hash_md5");
                }
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return null;
    }


}
