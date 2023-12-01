package com.pe.mispatitas.mispatitas.dao.impl;

import com.pe.mispatitas.mispatitas.dao.DaoMascota;
import com.pe.mispatitas.mispatitas.entidad.Mascota;
import java.io.InputStream;
import java.util.List;
import com.pe.mispatitas.mispatitas.util.Conexion;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author Luis Santos
 */
public class DaoMascotaImpl implements DaoMascota {

    private final Conexion conexion;
    private String mensaje;

    public DaoMascotaImpl() {
        conexion = new Conexion();
    }

    @Override
    public List<Mascota> mascotaSel() {
        List<Mascota> lista = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idMascota,")
                .append("idUsuario,")
                .append("nombreMascota,")
                .append("fechaNacimiento,")
                .append("razaMascota,")
                .append("descripcion,")
                .append("sexo,")
                .append("foto ")
                .append("FROM mascota");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<>();
            while (rs.next()) {
                Mascota mascota = new Mascota();
                mascota.setIdMascota(rs.getInt(1));
                mascota.setIdUsuario(rs.getInt(2));
                mascota.setNombreMascota(rs.getString(3));
                mascota.setFechaNacimiento(rs.getString(4));
                mascota.setRazaMascota(rs.getString(5));
                mascota.setDescripcion(rs.getString(6));
                mascota.setSexo(rs.getString(7));
                Blob blob = rs.getBlob("foto");
                if (blob != null) {
                    try (InputStream inputStream = blob.getBinaryStream()) {
                        mascota.setFoto(inputStream);
                    }
                }
                lista.add(mascota);
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return lista;

    }

    @Override
    public Mascota mascotaGet(Integer id) {

        Mascota mascota = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("idMascota,")
                .append("idUsuario,")
                .append("nombreMascota,")
                .append("fechaNacimiento,")
                .append("razaMascota,")
                .append("descripcion,")
                .append("sexo,")
                .append("foto ")
                .append("FROM mascota ")
                .append("WHERE idMascota = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            mascota = new Mascota();
            if (rs.next()) {
                mascota.setIdMascota(rs.getInt(1));
                mascota.setIdUsuario(rs.getInt(2));
                mascota.setNombreMascota(rs.getString(3));
                mascota.setFechaNacimiento(rs.getString(4));
                mascota.setRazaMascota(rs.getString(5));
                mascota.setDescripcion(rs.getString(6));
                mascota.setSexo(rs.getString(7));
                mascota.setFoto((InputStream) rs.getBlob(8));
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        return mascota;

    }

    @Override
    public String mascotaIns(Mascota mascota) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO mascota(")
                .append("idUsuario,")
                .append("nombreMascota,")
                .append("fechaNacimiento,")
                .append("razaMascota,")
                .append("descripcion,")
                .append("sexo,")
                .append("foto ")
                .append(") VALUES (?,?,?,?,?,?,?)");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, mascota.getIdUsuario());
            ps.setString(2, mascota.getNombreMascota());
            ps.setString(3, mascota.getFechaNacimiento());
            ps.setString(4, mascota.getRazaMascota());
            ps.setString(5, mascota.getDescripcion());
            ps.setString(6, mascota.getSexo());
            ps.setBlob(7, mascota.getFoto());
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
    public String mascotaUpd(Mascota mascota) {

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE mascota SET ")
                .append("idUsuario = ?,")
                .append("nombreMascota = ?,")
                .append("fechaNacimiento = ?,")
                .append("razaMascota = ?,")
                .append("descripcion = ?,")
                .append("sexo = ?,")
                .append("foto = ? ")
                .append("WHERE idMascota = ?");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, mascota.getIdUsuario());
            ps.setString(2, mascota.getNombreMascota());
            ps.setString(3, mascota.getFechaNacimiento());
            ps.setString(4, mascota.getRazaMascota());
            ps.setString(5, mascota.getDescripcion());
            ps.setString(6, mascota.getSexo());
            ps.setBlob(7, mascota.getFoto());
            ps.setInt(8, mascota.getIdMascota());
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
    public String mascotaDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM mascota")
                .append(" WHERE idMascota = ?");
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
