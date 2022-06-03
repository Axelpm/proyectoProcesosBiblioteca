
package proyectoprocesos.POJO;


public class Profesor extends Usuario{
    private int idProfesor;
    private int numPersonal;
    private String facultad;

    public Profesor() {
    }

    public Profesor(int idProfesor, int numPersonal, String facultad, int idUsuario, String nombreUsuario, String nombre, String apellido, String direccion, String telefono, String password, String correoElectronico) {
        super(idUsuario, nombreUsuario, nombre, apellido, direccion, telefono, password, correoElectronico);
        this.idProfesor = idProfesor;
        this.numPersonal = numPersonal;
        this.facultad = facultad;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(int numPersonal) {
        this.numPersonal = numPersonal;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    
}
