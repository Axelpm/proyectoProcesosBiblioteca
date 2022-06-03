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
    
    public static int recuperarIDRecurso(String isbn){
        Connection conn = ConexionBD.abrirConexionBD();
        String CONSULTA_ID = "SELECT idRecursodocumental FROM recursodocumental WHERE recursodocumental.isbn = ?";
        int idUsuario = -1;
        
        if(conn != null){
            try{
                    PreparedStatement ps = conn.prepareStatement(CONSULTA_ID);
                    ps.setString(1, isbn);
                    ResultSet rs= ps.executeQuery();
                    
                    if(rs.next()){
                       idUsuario = rs.getInt("idRecursodocumental");
                    }
                }catch(SQLException e){
                    System.out.println("Error al recuperar el idRecursodocumental");
                    return -1;
                }
        }
        
        return idUsuario;
    }
    
    public static int recuperarPrestamosVigentes(String matricula){
        int idUsuario = getIdUsuario(matricula);
        String consultaGET = "SELECT prestamosVigentes FROM usuario WHERE usuario.idUsuario = ?";
        int prestamosVigentes = -1;
        
        Connection conn = ConexionBD.abrirConexionBD();
        if(conn != null){
            try{
               if(idUsuario == -1)
                   throw new SQLException();
               
                PreparedStatement ps = conn.prepareStatement(consultaGET);
                ps.setInt(1, idUsuario);
                ResultSet rs= ps.executeQuery();
                
                if(rs.next()){
                    prestamosVigentes = rs.getInt("prestamosVigentes");
                }
            }catch(SQLException e){
                System.out.println(e);
                return -1;
            }
        }
        
        return prestamosVigentes;
    }
    
    public static boolean aumentarPrestamos(String matricula){
        int idUser = getIdUsuario(matricula);
        int prestamosVigentes = recuperarPrestamosVigentes(matricula);
        String consultaUP = "UPDATE usuario SET prestamosVigentes=? WHERE usuario.idUsuario = ?";
        Connection conn = ConexionBD.abrirConexionBD();
        
        if(conn != null){
            try{
                if(idUser == -1)
                    return false;
                if(prestamosVigentes == -1)
                    return false;
                
                prestamosVigentes++;
                PreparedStatement ps = conn.prepareStatement(consultaUP);
                ps.setInt(1, prestamosVigentes);
                ps.setInt(2, idUser);
                ps.executeUpdate();
                
            }catch(SQLException e){
                return false;
            }
        }
        return true;
    }
    
    public static int registrarPrestamo(String matricula, String isbn){
        Connection conn = ConexionBD.abrirConexionBD();
        String CONSULTA = "INSERT INTO prestamo (fechaLimite, fechaInicioPrestamo, idUsuario, idRecursodocumental) VALUES (?,?,?,?);";
        
        LocalDateTime dtm = LocalDateTime.now();
        String fechaInicio = ""+dtm.toLocalDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
          
        Calendar cal = Calendar.getInstance();  
        try{  
           cal.setTime(sdf.parse(fechaInicio));  
        }catch(ParseException e){  
            System.out.println("Error al registrar fecha de prestamo");
            return -1;
        }
        
        cal.add(Calendar.DAY_OF_MONTH, 5);  
        String fechaFinal = sdf.format(cal.getTime());  
        
        if(conn != null){
            try{
                int idRecurso = recuperarIDRecurso(isbn);
                PreparedStatement ps = conn.prepareStatement(CONSULTA);
                ps.setDate(1, Date.valueOf(fechaFinal));
                ps.setDate(2, Date.valueOf(fechaInicio));
                
                int idUsuario = getIdUsuario(matricula);
                if(idUsuario == -1)
                    throw new SQLException();
                if(idRecurso == -1)
                    throw new SQLException();
                
                ps.setInt(3, idUsuario);
                ps.setInt(4, idRecurso);
                
                ps.executeUpdate();
                
                aumentarPrestamos(matricula);
                
            }catch(SQLException e){
                System.out.println("Error al registrar prestamo");
                return -1;
            }
        }else{
            Mensaje.mostrarAlerta("Conexion no disponible", "Imposible generar el registro del prestamo, la conexion no se encuentra disponible, una disculpa.", Alert.AlertType.INFORMATION);
            return -1;
        }
        
        return 0;
    }
    
    public static boolean seEncuentraEnPrestamo(String isbn){
        int idRecurso = recuperarIDRecurso(isbn);
        Connection conn = ConexionBD.abrirConexionBD();
        
        if(conn != null){
            String CONSULTA = "SELECT idRecursodocumental FROM prestamo WHERE prestamo.idRecursodocumental = ?";
            int idRecuperada = -1;
            
            try{
                PreparedStatement ps = conn.prepareStatement(CONSULTA);
                ps.setInt(1, idRecurso);
                ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    idRecuperada = rs.getInt("idRecursodocumental");
                }
                
                if(idRecuperada == idRecurso){
                    Mensaje.mostrarAlerta("Recurso se encuentra en prestamo", "Imposible concretar el prestamo, el recurso ya se encuentra en un prestamo vigente.", Alert.AlertType.INFORMATION);
                    return true;
                }
            }catch(SQLException e){
                System.out.println("Error en la base de datos");
                return true;
            }
        }else{
            Mensaje.mostrarAlerta("No hay conexion", "No se ha podido concretar la conexion con la base de datos", Alert.AlertType.INFORMATION);
            return true;
        }
        
        return false;
    }
}
