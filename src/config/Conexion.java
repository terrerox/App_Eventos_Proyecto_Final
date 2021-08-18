/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Conexion {

    private String url = "jdbc:mysql://localhost:3307/PROYECTO_FINAL";
    private String username = "root";
    private String password = "";
    private Connection con;

    public Conexion() {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("estas conectado manin!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection obtenerConexion() {
        return con;
    }

    public void desconectar() {
        try {

            if (con != null) {
                con.close();
            }
     
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
