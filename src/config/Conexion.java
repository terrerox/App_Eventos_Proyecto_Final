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

/**
 *
 * @author Admin
 */
public class Conexion {

    private String url = "jdbc:mysql://localhost:3306/PROYECTO_FINAL";
    private String username = "root";
    private String password = "bryan";
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement preparedStatement = null; 

    public Conexion() {

    }
    
    public void conectar(){ 
        
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("lProbado aqui!");
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
                System.out.println("Ya nos hemos desconectado!");
            }
     
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertarEvento(){
         
        String query = "INSERT INTO EVENTOS"
                + "(NOMBRE,"
                + " HORA_INICIO,"
                + " HORA_FINAL,"
                +  "LUGAR,"
                +  "FECHA,"
                +  "DETALLES)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
      conectar();
     
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "Examen Diferencial");
            preparedStatement.setString(2, "15:00");
            preparedStatement.setString(3, "18:00");
            preparedStatement.setString(4, "ITLA");
            preparedStatement.setString(5, "2020-10-12");
            preparedStatement.setString(6, "Pasar el examen con 90");

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();
        } 
        finally {
            desconectar();
        }
    }
    
    public void seleccionarEvento(int id){
        
        String query = "SELECT * FROM EVENTOS WHERE ID = " + id;
        conectar();
        
        try {
            preparedStatement = con.prepareStatement(query);
            
            rs = preparedStatement.executeQuery();
             
            if (rs.next()){
                 System.out.println(rs.getInt("ID"));
                 System.out.println(rs.getString("NOMBRE"));
            }
           
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally {
            desconectar();
        }
    }
    
   public void buscarEventoPorFecha(String fecha){
        
        // List<Evento> eventos = new ArrayList<>(); 
       
        String query = "SELECT * FROM EVENTOS WHERE FECHA = " + "\'" + fecha + "\'";
        conectar();
        
        try {
            preparedStatement = con.prepareStatement(query);
            
            rs = preparedStatement.executeQuery();
             
            if (rs.next()){
                 System.out.println(rs.getInt("ID"));
                 System.out.println(rs.getString("NOMBRE"));
            }
           
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally {
            desconectar();
        }
    }
   
      public void buscarEventoPorLugar(String lugar){
 
         // List<Evento> eventos = new ArrayList<>();
        
        String query = "SELECT * FROM EVENTOS WHERE LUGAR = " + "\"" + lugar + "\" ORDER BY FECHA";
        conectar();
        
        try {
            preparedStatement = con.prepareStatement(query);
            
            rs = preparedStatement.executeQuery();
             
            if (rs.next()){
                 System.out.println(rs.getInt("ID"));
                 System.out.println(rs.getString("NOMBRE"));
            }
           
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally {
            desconectar();
        }
    }
     
    public void buscarEventoPorDetalle(String expresion){

        // List<Evento> eventos = new ArrayList<>();
        
       String query = "SELECT * FROM EVENTOS WHERE DETALLES LIKE " + "\"%" + expresion + "%\" ORDER BY FECHA";
       conectar();
       
         try {
                   
            preparedStatement = con.prepareStatement(query);
           
            
            rs = preparedStatement.executeQuery();
             
            if (rs.next()){
                 System.out.println(rs.getInt("ID"));
                 System.out.println(rs.getString("NOMBRE"));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally {
            desconectar();
        }      
    }
    
    public void eliminarEvento(int id) {
    
         String query = "DELETE FROM EVENTOS WHERE ID = ?" ;
         conectar();
         
         try{
             preparedStatement = con.prepareStatement(query);
             preparedStatement.setInt(1, id);
             
             preparedStatement.executeUpdate();
         }
         catch(SQLException ex){
             ex.printStackTrace();
         }
         finally {
            desconectar();
        }
    }
    
    
    public void getAllEventos(){
        
        // List<Evento> eventos = new ArrayList<>();
        
        String query = "SELECT * FROM EVENTOS ORDER BY FECHA";
        conectar();
        
        try {
            preparedStatement = con.prepareStatement(query);
            
            rs = preparedStatement.executeQuery();
             
            while(rs.next()){
                 System.out.println(rs.getInt("ID"));
                 System.out.println(rs.getString("NOMBRE"));
            }
           
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally {
            desconectar();
        }
    }
 
     public void actualizarEvento(int id){
         
        String query = "UPDATE EVENTOS SET NOMBRE = ? ,HORA_INICIO = ? ,HORA_FINAL = ? ,LUGAR = ? ,FECHA = ? ,DETALLES = ? WHERE ID = " + id;   

        conectar();
     
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, "Examen Diferencial Actualizado");
            preparedStatement.setString(2, "15:00");
            preparedStatement.setString(3, "18:00");
            preparedStatement.setString(4, "ITLA");
            preparedStatement.setString(5, "2020-10-12");
            preparedStatement.setString(6, "Pasar el examen con 90");

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {
            desconectar();
        }
    }
       
    
    /*public static void main(String[] args){
       
        Conexion co = new Conexion();
        co.buscarEventoPor("ITLA");
    }   

*/
}