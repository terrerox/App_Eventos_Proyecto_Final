/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.Evento;

/**
 *
 * @author Admin
 */
public class Conexion {

    private String url = "jdbc:mysql://localhost:3306/PROYECTO_FINAL";
    private String username = "root";
    private String password = "";
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement preparedStatement = null;

    public Conexion() {

    }

    private boolean conectar() {

        try {
            con = DriverManager.getConnection(url, username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean desconectar() {

        try {

            if (con != null) {
                con.close();
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean insertarEvento(Evento evento) {

        boolean insertado = false;

        String query = "INSERT INTO EVENTOS" + "(NOMBRE," + " HORA_INICIO," + " HORA_FINAL," + "LUGAR," + "FECHA,"
                + "DETALLES)" + " VALUES (?, ?, ?, ?, ?, ?)";

        if (conectar()) {

            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, evento.getNombreEvento());
                preparedStatement.setTime(2, evento.getHoraInicio());
                preparedStatement.setTime(3, evento.getHoraFinal());
                preparedStatement.setString(4, evento.getLugar());
                preparedStatement.setDate(5, evento.getFecha());
                preparedStatement.setString(6, evento.getDetalles());

                preparedStatement.executeUpdate();
                insertado = true;

            } catch (SQLException ex) {
                insertado = false;
            } finally {
                desconectar();
            }
        }
        return insertado;
    }

    public Evento seleccionarEvento(int id) {

        Evento evento = null;
        String query = "SELECT * FROM EVENTOS WHERE ID = " + id;

        if (conectar()) {

            try {
                preparedStatement = con.prepareStatement(query);

                rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    evento = new Evento(rs.getInt("id"), rs.getString("Nombre"), rs.getTime("Hora_Inicio"),
                            rs.getTime("Hora_Final"), rs.getString("Lugar"), rs.getDate("Fecha"),
                            rs.getString("Detalles"));
                }
            } catch (SQLException ex) {

            } finally {
                desconectar();
            }
        }
        return evento;
    }

    public List<Evento> buscarEventoPorFecha(String fecha) {

        List<Evento> eventos = new ArrayList<>();
        String query = "SELECT * FROM EVENTOS WHERE FECHA = " + "\'" + fecha + "\'";
        conectar();

        try {
            preparedStatement = con.prepareStatement(query);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                eventos.add(new Evento(rs.getInt("id"), rs.getString("Nombre"), rs.getTime("Hora_Inicio"),
                        rs.getTime("Hora_Final"), rs.getString("Lugar"), rs.getDate("Fecha"),
                        rs.getString("Detalles")));
            }

        } catch (SQLException ex) {

        } finally {
            desconectar();
        }
        return eventos;
    }

    public List<Evento> buscarEventoPorLugar(String lugar) {

        List<Evento> eventos = new ArrayList<>();

        String query = "SELECT * FROM EVENTOS WHERE LUGAR = " + "\"" + lugar + "\" ORDER BY FECHA";

        if (conectar()) {

            try {
                preparedStatement = con.prepareStatement(query);
                rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    eventos.add(new Evento(rs.getInt("id"), rs.getString("Nombre"), rs.getTime("Hora_Inicio"),
                            rs.getTime("Hora_Final"), rs.getString("Lugar"), rs.getDate("Fecha"),
                            rs.getString("Detalles")));
                }

            } catch (SQLException ex) {

            } finally {
                desconectar();
            }
        }
        return eventos;
    }

    public List<Evento> buscarEventoPorDetalle(String expresion) {

        List<Evento> eventos = new ArrayList<>();

        String query = "SELECT * FROM EVENTOS WHERE DETALLES LIKE " + "\"%" + expresion + "%\" ORDER BY FECHA";

        if (conectar()) {

            try {

                preparedStatement = con.prepareStatement(query);
                rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    eventos.add(new Evento(rs.getInt("id"), rs.getString("Nombre"), rs.getTime("Hora_Inicio"),
                            rs.getTime("Hora_Final"), rs.getString("Lugar"), rs.getDate("Fecha"),
                            rs.getString("Detalles")));
                }
            } catch (SQLException ex) {

            } finally {
                desconectar();
            }
        }
        return eventos;
    }

    public void eliminarEvento(int id) {

        String query = "DELETE FROM EVENTOS WHERE ID = ?";

        if (conectar()) {

            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();
            } catch (SQLException ex) {

            } finally {
                desconectar();
            }
        }
    }

    public List<Evento> getAllEventos() {

        List<Evento> eventos = new ArrayList<>();

        String query = "SELECT * FROM EVENTOS ORDER BY FECHA";

        if (conectar()) {

            try {
                preparedStatement = con.prepareStatement(query);
                rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    eventos.add(new Evento(rs.getInt("id"), rs.getString("Nombre"), rs.getTime("Hora_Inicio"),
                            rs.getTime("Hora_Final"), rs.getString("Lugar"), rs.getDate("Fecha"),
                            rs.getString("Detalles")));
                }
            } catch (SQLException ex) {

            } finally {
                desconectar();
            }
        }
        return eventos;
    }

    public boolean actualizarEvento(int id, Evento evento) {

        String query = "UPDATE EVENTOS SET NOMBRE = ? ,HORA_INICIO = ? ,HORA_FINAL = ? ,LUGAR = ? ,FECHA = ? ,DETALLES = ? WHERE ID = "
                + id;
        boolean actualizado = false;

        if (conectar()) {

            try {
                preparedStatement = con.prepareStatement(query);

                preparedStatement.setString(1, evento.getNombreEvento());
                preparedStatement.setTime(2, evento.getHoraInicio());
                preparedStatement.setTime(3, evento.getHoraFinal());
                preparedStatement.setString(4, evento.getLugar());
                preparedStatement.setDate(5, evento.getFecha());
                preparedStatement.setString(6, evento.getDetalles());

                preparedStatement.executeUpdate();

                actualizado = true;

            } catch (SQLException ex) {
                actualizado = false;
            } finally {
                desconectar();
            }
        }
        return actualizado;
    }
}