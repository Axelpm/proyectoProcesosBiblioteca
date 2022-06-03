package proyectoprocesos.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import proyectoprocesos.Util.Mensaje;

public class ConexionBD {
    private static final String DRIVER= "com.mysql.jdbc.Driver";
    private static final String DATABASE="gestionBiblioteca";
    private static final String HOSTNAME="localhost"; 
    private static final String PORT="3306";
    private static final String URL="jdbc:mysql://"+HOSTNAME+":"+PORT+"/"+DATABASE+"?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME="AdminBiblioteca";
    private static final String PASSWORD="[SZXjT6Prfdd";
    
    public static Connection abrirConexionBD(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch (Exception e){
            Mensaje.mostrarAlerta("Error", "Base de datos no disponible", Alert.AlertType.ERROR);
        }
        return conn;
    }
    
        public static void cerrar(ResultSet rSet){
        try{
            if(rSet != null){
                rSet.close();
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static void cerrar(PreparedStatement pStatement){
        try{
            if(pStatement != null){
                pStatement.close();
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static void cerrar(Connection connect){
        try{
            if(connect != null){
                connect.close();
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
