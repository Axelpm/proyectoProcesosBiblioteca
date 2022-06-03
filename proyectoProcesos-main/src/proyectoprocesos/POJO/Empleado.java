
package proyectoprocesos.POJO;


public class Empleado extends Usuario{
    private String clavePersonal;
    private String puesto;
    private String nombreCompleto;
    private static int fkIdUsuario;

    public Empleado() {
    }

    public Empleado(String clavePersonal, String puesto, int idUsuario, String nombreUsuario, String nombre, String apellido, String direccion, String telefono, String password, String correoElectronico) {
        super(idUsuario, nombreUsuario, nombre, apellido, direccion, telefono, password, correoElectronico);
        this.clavePersonal = clavePersonal;
        this.puesto = puesto;
    }

    public String getClavePersonal() {
        return clavePersonal;
    }

    public void setClavePersonal(String clavePersonal) {
        this.clavePersonal = clavePersonal;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public static int getFkIdUsuario() {
        return fkIdUsuario;
    }

    public static void setFkIdUsuario(int fkIdUsuario) {
        Empleado.fkIdUsuario = fkIdUsuario;
    }
    
    

    
}
