package proyectoprocesos.POJO;


public class Revista extends RecursoDocumental{
   private int idRevista;
   private String categoria;

    public Revista() {
    }

    public Revista(int idRevista, String categoria, int idRecursoDocumental, String year, String clasificacion, String isbn, String titulo, String autor, String idPrestamo, int numeroDisponible) {
        super(idRecursoDocumental, year, clasificacion, isbn, titulo, autor, idPrestamo, numeroDisponible);
        this.idRevista = idRevista;
        this.categoria = categoria;
    }

    

    

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
   
   
    
}
