/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprocesos.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import proyectoprocesos.Conexion.ConexionBD;

/**
 *
 * @author MrMMP
 */
public class Verificacion {
    
    public static boolean validarMatricula (String matricula){
        boolean respuesta = false;
        String inicioMatricula = matricula.substring(0, 1);
        if(matricula.length() == 9){
            if(inicioMatricula.equals("S")){
                try{
                    int numero = Integer.parseInt(matricula.substring(1));
                    respuesta = true;                            
                }catch(NumberFormatException ex){
                    System.out.println("Error: Matricula invalida");
                } 
            }
        }
        return respuesta;
    }
    
    // El ISBN lleva el formato FO-XXXXXX (Donde X son numeros)
    public static boolean validarISBN (String isbn){
        boolean respuesta = false;
        String inicioMatricula = isbn.substring(0, 3);
        if(isbn.length() == 9){
            if(inicioMatricula.equals("FO-")){
                try{
                    int numero = Integer.parseInt(isbn.substring(1));
                    respuesta = true;                            
                }catch(NumberFormatException ex){
                    System.out.println("Error: ISBN invalida");
                    return false;
                } 
            }
        }
        return respuesta;
    }
    
    public static boolean validarMatriculaBD(String matricula){
        Connection conn = ConexionBD.abrirConexionBD();
        
        if(conn != null){
            String CONSULTA = "SELECT matricula FROM estudiante WHERE estudiante.matricula = ?";
            String matriculaRecuperada = null;
            
            try{
                PreparedStatement ps = conn.prepareStatement(CONSULTA);
                ps.setString(1, matricula);
                ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    matriculaRecuperada = rs.getString("matricula");
                }
            }catch(SQLException e){
                System.out.println("Error en la base de datos");
                return false;
            }
            
            if(!matricula.equals(matriculaRecuperada)){
                return false;
            }
        }else{
            Mensaje.mostrarAlerta("No hay conexion", "No se ha podido concretar la conexion con la base de datos", Alert.AlertType.ERROR);
            return false;
        }
        
        return true;
    }
    
    public static boolean validarRecursoBD(String isbn){
        Connection conn = ConexionBD.abrirConexionBD();
        
        if(conn != null){
            String CONSULTA = "SELECT isbn FROM recursodocumental WHERE recursodocumental.isbn = ?";
            String isbnRecuperada = null;
            
            try{
                PreparedStatement ps = conn.prepareStatement(CONSULTA);
                ps.setString(1, isbn);
                ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    isbnRecuperada = rs.getString("isbn");
                }
            }catch(SQLException e){
                System.out.println("Error en la base de datos");
                return false;
            }
            
            if(!isbn.equals(isbnRecuperada)){
                Mensaje.mostrarAlerta("Recurso no encontrado", "El recurso no se encuentra registrado en el sistema, imposible continuar con el préstamo, favor de registrarlo.", Alert.AlertType.NONE);
                return false;
            }else{
                
            }
        }else{
            Mensaje.mostrarAlerta("No hay conexion", "No se ha podido concretar la conexion con la base de datos", Alert.AlertType.ERROR);
            return false;
        }
        
        return true;
    }
    
    public static boolean verificarPrestamos(String matricula){
        boolean excedioPrestamos = false;
        if(validarMatriculaBD(matricula)){
            
            Connection conn = ConexionBD.abrirConexionBD();
            int numeroPrestamosActuales = 0;
             
            if(conn != null){
                String CONSULTA_ID = "SELECT idUsuario FROM estudiante WHERE estudiante.matricula = ?";
                String CONSULTA_PRESTAMOS = "SELECT prestamosVigentes FROM usuario WHERE idUsuario = ?;";
                int idUsuario = 0;
                
                try{
                    PreparedStatement ps = conn.prepareStatement(CONSULTA_ID);
                    ps.setString(1, matricula);
                    ResultSet rs= ps.executeQuery();
                    
                    if(rs.next()){
                        idUsuario = rs.getInt("idUsuario");
                    }
                }catch(SQLException e){
                    System.out.println("Error al recuperar el idUsuario");
                    return false;
                }
                
                try{
                    PreparedStatement ps = conn.prepareStatement(CONSULTA_PRESTAMOS);
                    ps.setInt(1, idUsuario);
                    ResultSet rs= ps.executeQuery();
                    if(rs.next()){
                        numeroPrestamosActuales = rs.getInt("prestamosVigentes");
                    }
                }catch(SQLException e){
                    System.out.println("No se pudo recuperar el numero de prestamos");
                    return false;
                }
                
                if(numeroPrestamosActuales >= 3){
                    excedioPrestamos = true;
                }
                
            }else{
                Mensaje.mostrarAlerta("No hay conexion", "No se ha podido concretar la conexion con la base de datos", Alert.AlertType.ERROR);
                return false;
            }
        }else{
            Mensaje.mostrarAlerta("Matricula no existente", "La matricula no esta registrada en nuestro sistema, por favor proceda con su registro antes de seguir con el prestamo", Alert.AlertType.ERROR);
        }
        
        return excedioPrestamos;
    }
    
}
