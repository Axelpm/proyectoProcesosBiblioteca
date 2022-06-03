/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprocesos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;  
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.scene.control.Alert;
import proyectoprocesos.Conexion.ConexionBD;
import proyectoprocesos.Util.Mensaje;

/**
 *
 * @author MrMMP
 */
public class PrestamoDAO {
    
    public static int getIdUsuario(String matricula){
        Connection conn = ConexionBD.abrirConexionBD();
        String CONSULTA_ID = "SELECT idUsuario FROM estudiante WHERE estudiante.matricula = ?";
        int idUsuario = -1;
        
        if(conn != null){
            try{
                    PreparedStatement ps = conn.prepareStatement(CONSULTA_ID);
                    ps.setString(1, matricula);
                    ResultSet rs= ps.executeQuery();
                    
                    if(rs.next()){
                       idUsuario = rs.getInt("idUsuario");
                    }
                }catch(SQLException e){
                    System.out.println("Error al recuperar el idUsuario");
                    return idUsuario;
                }
        }
        
        return idUsuario;
    }
    
    public static void registrarPrestamo(String matricula){
        Connection conn = ConexionBD.abrirConexionBD();
        String CONSULTA = "INSERT INTO prestamo (fechaLimite, fechaInicioPrestamo, idUsuario) VALUES (?,?,?);";
        
        LocalDateTime dtm = LocalDateTime.now();
        String fechaInicio = ""+dtm.toLocalDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
          
        Calendar cal = Calendar.getInstance();  
        try{  
           cal.setTime(sdf.parse(fechaInicio));  
        }catch(ParseException e){  
            e.printStackTrace();  
        }
        
        cal.add(Calendar.DAY_OF_MONTH, 5);  
        String fechaFinal = sdf.format(cal.getTime());  
        
        if(conn != null){
            try{
                PreparedStatement ps = conn.prepareStatement(CONSULTA);
                ps.setDate(1, Date.valueOf(fechaFinal));
                ps.setDate(2, Date.valueOf(fechaInicio));
                
                int idUsuario = getIdUsuario(matricula);
                if(idUsuario == -1)
                    throw new SQLException();
                
                ps.setInt(3, idUsuario);
                
                ps.executeUpdate();
                
            }catch(SQLException e){
                System.out.println("Error al registrar prestamo");
            }
        }else{
            Mensaje.mostrarAlerta("Conexion no disponible", "Imposible generar el registro del prestamo, la conexion no se encuentra disponible, una disculpa.", Alert.AlertType.ERROR);
        }
    }
}