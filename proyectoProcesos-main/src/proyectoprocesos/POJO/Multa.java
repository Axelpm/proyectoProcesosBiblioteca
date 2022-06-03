
package proyectoprocesos.POJO;


public class Multa {
    private int idMulta;
    private int montoTotal;

    public Multa() {
    }

    public Multa(int idMulta, int montoTotal) {
        this.idMulta = idMulta;
        this.montoTotal = montoTotal;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }
    
}
