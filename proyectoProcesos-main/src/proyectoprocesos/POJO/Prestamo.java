
package proyectoprocesos.POJO;


public class Prestamo {
    private int idPrestamo;
    private String fechaPrestamo;
    private String fechaLimite;
    private String clavePersonal;
    private String matricula;
    private int numeroPersonal;

    public Prestamo() {
    }

    public Prestamo(int idPrestamo, String fechaPrestamo, String fechaLimite, String clavePersonal, String matricula, int numeroPersonal) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
        this.clavePersonal = clavePersonal;
        this.matricula = matricula;
        this.numeroPersonal = numeroPersonal;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public String getClavePersonal() {
        return clavePersonal;
    }

    public void setClavePersonal(String clavePersonal) {
        this.clavePersonal = clavePersonal;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNumeroPersonal() {
        return numeroPersonal;
    }

    public void setNumeroPersonal(int numeroPersonal) {
        this.numeroPersonal = numeroPersonal;
    }
    
    
}
