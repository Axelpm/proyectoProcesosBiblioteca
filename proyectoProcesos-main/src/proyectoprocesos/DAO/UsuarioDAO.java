package proyectoprocesos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import proyectoprocesos.Conexion.ConexionBD;
import proyectoprocesos.POJO.Usuario;

public class UsuarioDAO {
    Connection conectarBD= null;
    PreparedStatement preStatement= null;
    
    public static Usuario comprobarDatosUsuario (String nombreUsuario, String password){
        Connection conn = ConexionBD.abrirConexionBD();
        Usuario usuario = new Usuario();
        
        if(conn != null){
            String consulta = "SELECT * FROM usuario WHERE nombreUsuario = ? AND password = ?";
            PreparedStatement prepararSentencia;
            try {
                prepararSentencia = conn.prepareStatement(consulta);
                
                prepararSentencia.setString(1, nombreUsuario);
                prepararSentencia.setString(2, password);
               
                ResultSet resultadoConsulta = prepararSentencia.executeQuery();
                
                if(resultadoConsulta.next()){
                   usuario.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                   usuario.setNombreUsuario(resultadoConsulta.getString("nombreUsuario"));
                   usuario.setPassword(resultadoConsulta.getString("password"));
                   usuario.setDireccion(resultadoConsulta.getString("direccion"));
                   usuario.setNombre(resultadoConsulta.getString("nombre"));
                   usuario.setApellido(resultadoConsulta.getString("apellido"));
                   usuario.setTelefono(resultadoConsulta.getString("telefono"));
                }else{
                    usuario = null;                    
                }
                
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }else{
            usuario = null; 
        }
        return usuario;
    }
    
    public String encontrarUsuarioEmpleado(int idUsuario){
        conectarBD = ConexionBD.abrirConexionBD();
        String puesto = "Estudiante";
        if(conectarBD != null){
            try{
                preStatement = conectarBD.prepareStatement("select puesto from empleado where idusuario = ?");
                preStatement.setInt(1, idUsuario);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    puesto = rSet.getString("puesto");
                    return puesto;
                }
            }catch(SQLException excepcionSQL){
                System.err.println("Error" + excepcionSQL);
            }
            finally{
                ConexionBD.cerrar(preStatement);
                ConexionBD.cerrar(conectarBD);
            }
        }
        return puesto;
    }
    
}
