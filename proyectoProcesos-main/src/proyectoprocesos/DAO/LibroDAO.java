package proyectoprocesos.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import proyectoprocesos.POJO.Libro;
import proyectoprocesos.Conexion.ConexionBD;


public class LibroDAO {
    
    public static ArrayList<Libro> obtenerInfoLibros(){
        ArrayList<Libro> librosBD=null;
        Connection conn= ConexionBD.abrirConexionBD();
        if(conn != null){
            try{
                String consulta= "select recursodocumental.idRecursoDocumental,titulo, autor, clasificacion, isbn, year, "
                + "libro.editorial,\n" +
                "libro.edicion, libro.area, libro.subarea, recursodocumental.numeroDisponible \n" +
                "from recursodocumental \n" +
                "inner join libro \n" +
                "on recursodocumental.idRecursodocumental= libro.idRecursodocumental";
            
                PreparedStatement ps = conn.prepareStatement(consulta);
                ResultSet resultadoConsulta= ps.executeQuery();
                librosBD = new ArrayList<>();
                while (resultadoConsulta.next()){
                    Libro libro= new Libro();
                    libro.setIdRecursoDocumental(resultadoConsulta.getInt("idRecursoDocumental"));
                    libro.setTitulo(resultadoConsulta.getString("titulo"));
                    libro.setAutor(resultadoConsulta.getString("autor"));
                    libro.setClasificacion(resultadoConsulta.getString("clasificacion"));
                    libro.setIsbn(resultadoConsulta.getString("isbn"));
                    libro.setYear(resultadoConsulta.getString("year"));
                    libro.setEditorial(resultadoConsulta.getString("editorial"));
                    libro.setEdicion(resultadoConsulta.getString("edicion"));
                    libro.setArea(resultadoConsulta.getString("area"));
                    libro.setSubarea(resultadoConsulta.getString("subarea"));
                    libro.setNumeroDisponible(resultadoConsulta.getInt("numeroDisponible"));
                    librosBD.add(libro);
                }
            conn.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return librosBD;
    }
}
