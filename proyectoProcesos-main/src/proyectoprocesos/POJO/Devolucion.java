
package proyectoprocesos.POJO;

public class Devolucion {
    private int idDevolucion;
    private String fechaLimite;

    public Devolucion() {
    }

    public Devolucion(int idDevolucion, String fechaLimite) {
        this.idDevolucion = idDevolucion;
        this.fechaLimite = fechaLimite;
    }

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
}
