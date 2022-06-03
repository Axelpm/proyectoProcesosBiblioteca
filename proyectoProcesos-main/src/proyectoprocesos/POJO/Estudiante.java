
package proyectoprocesos.POJO;


public class Estudiante extends Usuario {
    private int idEstudiante;
    private String matricula;
    private String carrera;

    public Estudiante() {
    }

    public Estudiante(int idEstudiante, String matricula, String carrera, int idUsuario, String nombreUsuario, String nombre, String apellido, String direccion, String telefono, String password, String correoElectronico) {
        super(idUsuario, nombreUsuario, nombre, apellido, direccion, telefono, password, correoElectronico);
        this.idEstudiante = idEstudiante;
        this.matricula = matricula;
        this.carrera = carrera;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    
    
    
}
