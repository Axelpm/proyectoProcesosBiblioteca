package proyectoprocesos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import proyectoprocesos.Conexion.ConexionBD;
import proyectoprocesos.POJO.Empleado;

/**
 *
 * @author victormanuel
 */
public class EmpleadoDAO {
    Connection conectarBD = null;
    PreparedStatement preStatement = null;
    private static final String OBTENER_EMPLEADOS = "select * from usuario inner join empleado where usuario.idusuario = empleado.idusuario;";
    private static final String INSERTA_USUARIO = "insert into usuario (nombreUsuario, "
            + "password, direccion, nombre, apellido, telefono, correoElectronico) "
            + "values (?, ?, ?, ?, ?, ?, ?);";
    private static final String BUSCAR_USUARIO = "select idusuario from usuario where correoelectronico = ?;";
    private static final String INSERTAR_EMPLEADO = "insert into empleado (clavepersonal, puesto, idUsuario) values (?, ?, ?);";
    private static final String OBTENER_DATOS_EMPLEADO = "select * from usuario inner join empleado on usuario.idusuario = empleado.idusuario where empleado.idusuario = ?;";
    private static final String ACTUALIZAR_INFORMACION_EMPLEADO = "update usuario inner join empleado on "
            + "usuario.idusuario = empleado.idusuario set usuario.nombreUsuario = ?, "
            + "usuario.password = ?, usuario.direccion = ?, usuario.nombre = ?, "
            + "usuario.apellido = ?, usuario.telefono = ?, usuario.correoElectronico = ?, "
            + "empleado.clavePersonal = ?, empleado.puesto = ? where empleado.idusuario = ?;";
    private static final String ELIMINAR_EMPLEADO = "delete from empleado where idusuario = ?;";
    private static final String ELIMINAR_USUARIO = "delete from usuario where idusuario = ?;";
    
    public ObservableList<Empleado> obtenerEmpleados(ObservableList<Empleado> informacionTabla){
        conectarBD = ConexionBD.abrirConexionBD();
        Empleado empleados = null;
        if(conectarBD != null){
            try{
                preStatement = conectarBD.prepareStatement(OBTENER_EMPLEADOS);
                ResultSet rSet = preStatement.executeQuery();
                while(rSet.next()){
                    empleados = new Empleado();
                    empleados.setIdUsuario(rSet.getInt("idusuario"));
                    empleados.setClavePersonal(rSet.getString("clavePersonal"));
                    empleados.setNombreCompleto(rSet.getString("nombre") + " " + rSet.getString("apellido"));
                    empleados.setPuesto(rSet.getString("puesto"));
                    empleados.setCorreoElectronico(rSet.getString("correoElectronico"));
                    empleados.setDireccion(rSet.getString("direccion"));
                    empleados.setTelefono(rSet.getString("telefono"));
                    informacionTabla.add(empleados);
                }
                return informacionTabla;
            }catch(SQLException excepcionSQL){
                System.err.println("Error" + excepcionSQL);
            }
        }
        return informacionTabla;
    }
    
    public int encontrarUsuarioEmpleado(String correoElectronico){
        conectarBD = ConexionBD.abrirConexionBD();
        int idUsuario = 0;
        if(conectarBD != null){
            try{
                preStatement = conectarBD.prepareStatement(BUSCAR_USUARIO);
                preStatement.setString(1, correoElectronico);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    idUsuario = rSet.getInt("idusuario");
                }
                return idUsuario;
            }catch(SQLException excepcionSQL){
                System.err.println("Error" + excepcionSQL);
            }
        }
        return idUsuario;
    }
    
    public void registrarUsuarioEmpleado(String nombreUsuario, String nombre, 
            String apellido, String direccion, String telefono, String password, 
            String correoElectronico){
        conectarBD = ConexionBD.abrirConexionBD();
        try{
            preStatement = conectarBD.prepareStatement(INSERTA_USUARIO);
            preStatement.setString(1, nombreUsuario);
            preStatement.setString(2, password);
            preStatement.setString(3, direccion);
            preStatement.setString(4, nombre);
            preStatement.setString(5, apellido);
            preStatement.setString(6, telefono);
            preStatement.setString(7, correoElectronico);
            preStatement.executeUpdate();
        }catch(SQLException excepcionSQL){
            System.err.println("Error " + excepcionSQL);
        }
    }
    
    public void registrarEmpledoa(String clavePersonal, String puesto, int idUsuario){
        conectarBD = ConexionBD.abrirConexionBD();
        try{
            preStatement = conectarBD.prepareStatement(INSERTAR_EMPLEADO);
            preStatement.setString(1, clavePersonal);
            preStatement.setString(2, puesto);
            preStatement.setInt(3, idUsuario);
            preStatement.executeUpdate();
        }catch(SQLException excepcionSQL){
            System.err.println("Error " + excepcionSQL);
        }
    }
    
    public Empleado obtenerInformacionEmpleado(int idUsuario){
        conectarBD = ConexionBD.abrirConexionBD();
        Empleado infoEmpleado = null;
        if(conectarBD != null){
            try{
                preStatement = conectarBD.prepareStatement(OBTENER_DATOS_EMPLEADO);
                preStatement.setInt(1, idUsuario);
                ResultSet rSet = preStatement.executeQuery();
                if(rSet.next()){
                    infoEmpleado = new Empleado();
                    infoEmpleado.setIdUsuario(rSet.getInt("idusuario"));
                    infoEmpleado.setNombreUsuario(rSet.getString("nombreUsuario"));
                    infoEmpleado.setPassword(rSet.getString("password"));
                    infoEmpleado.setDireccion(rSet.getString("direccion"));
                    infoEmpleado.setNombre(rSet.getString("nombre"));
                    infoEmpleado.setApellido(rSet.getString("apellido"));
                    infoEmpleado.setTelefono(rSet.getString("telefono"));
                    infoEmpleado.setCorreoElectronico(rSet.getString("correoelectronico"));
                    infoEmpleado.setClavePersonal(rSet.getString("clavepersonal"));
                    infoEmpleado.setPuesto(rSet.getString("puesto"));
                    return infoEmpleado;
                }
            }catch(SQLException excepcionSQL){
                System.err.println("Error" + excepcionSQL);
            }
        }
        return infoEmpleado;
    }
    
    public boolean actualizarInformacionEmpleado (Empleado empleado, int idUsuario){
        conectarBD = ConexionBD.abrirConexionBD();
        boolean confirmar = false;
        if(conectarBD != null){
            try{
                preStatement = conectarBD.prepareStatement(ACTUALIZAR_INFORMACION_EMPLEADO);
                preStatement.setString(1, empleado.getNombreUsuario());
                preStatement.setString(2, empleado.getPassword());
                preStatement.setString(3, empleado.getDireccion());
                preStatement.setString(4, empleado.getNombre());
                preStatement.setString(5, empleado.getApellido());
                preStatement.setString(6, empleado.getTelefono());
                preStatement.setString(7, empleado.getCorreoElectronico());
                preStatement.setString(8, empleado.getClavePersonal());
                preStatement.setString(9, empleado.getPuesto());
                preStatement.setInt(10, idUsuario);
                preStatement.executeUpdate();
                confirmar = true;
            }catch(SQLException excepcionSQL){
                System.err.println("Error: " + excepcionSQL);
            }
        }
        return confirmar;
    }
    
    public void eliminarEmpleado(int idUsuario){
        conectarBD = ConexionBD.abrirConexionBD();
        if(conectarBD != null){
            try{
                preStatement = conectarBD.prepareStatement(ELIMINAR_EMPLEADO);
                preStatement.setInt(1, idUsuario);
                preStatement.executeUpdate();
            }catch(SQLException excepcionSQL){
                System.err.println("Error: " + excepcionSQL);
            }
        }
    }
    
        public void eliminarUsuarioEmpleado(int idUsuario){
        conectarBD = ConexionBD.abrirConexionBD();
        if(conectarBD != null){
            try{
                preStatement = conectarBD.prepareStatement(ELIMINAR_USUARIO);
                preStatement.setInt(1, idUsuario);
                preStatement.executeUpdate();
            }catch(SQLException excepcionSQL){
                System.err.println("Error: " + excepcionSQL);
            }
        }
    }
}
