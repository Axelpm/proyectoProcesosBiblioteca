
package proyectoprocesos.POJO;


public class ConstanciaNoAdeudo {
    private String biblioteca;
    private String tipoTramite;

    public ConstanciaNoAdeudo() {
    }

    public ConstanciaNoAdeudo(String biblioteca, String tipoTramite) {
        this.biblioteca = biblioteca;
        this.tipoTramite = tipoTramite;
    }

    public String getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(String biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
    
    
}
