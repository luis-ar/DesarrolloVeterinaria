/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pe.mispatitas.mispatitas.dao.impl;

import com.pe.mispatitas.mispatitas.dao.DaoUsuario;
import com.pe.mispatitas.mispatitas.entidad.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.pe.mispatitas.mispatitas.util.Conexion;
import java.sql.Statement;

/**
 *
 * @author Luis Santos
 */
public class DaoUsuarioImpl implements DaoUsuario {

    private final Conexion conexion;
    private String mensaje;

    public DaoUsuarioImpl() {
        conexion = new Conexion();
    }

    @Override
    public List<Usuario> usuarioSel() {

        List<Usuario> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idUsuario,")
                .append("nombreUsuario,")
                .append("correoUsuario,")
                .append("telefono,")
                .append("CAST(AES_DECRYPT(contraseña,'AES') AS CHAR) ")
                .append("FROM usuario");
       
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setCorreoUsuario(rs.getString(3));
                usuario.setTelefono(rs.getString(4));
                usuario.setContra(rs.getString(5));
                lista.add(usuario);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return lista;

    }

    @Override
    public String calcularMD5MySQL(String contrasena) {
        String sql="SELECT CAST(AES_DECRYPT(?,'AES') AS CHAR) AS contra_Desencriptada FROM usuario";
//        String sql = "SELECT MD5(?) AS hash_md5";
        try (Connection cn = conexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("contra_Desencriptada");
                }
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return null;
    }

    @Override
    public Usuario usuarioGet(Integer id) {

        Usuario usuario = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idUsuario,")
                .append("nombreUsuario,")
                .append("correoUsuario,")
                .append("telefono,")
                .append("CAST(AES_DECRYPT(contraseña,'AES') AS CHAR) ")
                .append("FROM usuario ")
                .append("WHERE idUsuario = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            usuario = new Usuario();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setCorreoUsuario(rs.getString(3));
                usuario.setTelefono(rs.getString(4));
                usuario.setContra(rs.getString(5));
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return usuario;

    }

    @Override
    public int usuarioIns(Usuario usuario) {
        int idGenerado = -1; // Valor por defecto si la inserción falla

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO usuario(")
                .append("nombreUsuario,")
                .append("correoUsuario,")
                .append("telefono,")
                .append("contraseña ")
                .append(") VALUES (?,?,?,AES_ENCRYPT(?,'AES'))");// Utilizar la función MD5 de MySQL

        try (Connection cn = conexion.getConexion()) {
            // Establecer el flag de retorno de claves generadas automáticamente
            try (PreparedStatement ps = cn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, usuario.getNombreUsuario());
                ps.setString(2, usuario.getCorreoUsuario());
                ps.setString(3, usuario.getTelefono());
                ps.setString(4, usuario.getContra());

                int resultado = ps.executeUpdate();

                if (resultado == 0) {
                    System.err.println("Cero registros agregados");
                } else {
                    // Recuperar el ID generado automáticamente
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            idGenerado = generatedKeys.getInt(1);
                            System.out.println("ID generado: " + idGenerado);
                        } else {
                            System.err.println("No se pudo obtener el ID generado");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return idGenerado;

    }

    @Override
    public String usuarioUpd(Usuario usuario) {

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE usuario SET ")
                .append("nombreUsuario = ?,")
                .append("correoUsuario = ?,")
                .append("telefono = ?,")
                .append("contraseña = ? ")
                .append("WHERE idUsuario = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getCorreoUsuario());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getContra());
            ps.setInt(5, usuario.getIdUsuario());
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
    public String usuarioDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM usuario")
                .append(" WHERE idUsuario = ?");
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
