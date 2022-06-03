
package proyectoprocesos.POJO;


public class RecursoDocumental {
    private int idRecursoDocumental;
    private String year;
    private String clasificacion;
    private String isbn;
    private String titulo;
    private String autor;
    private String idPrestamo;
    private int numeroDisponible;

    public RecursoDocumental() {
    }

    public RecursoDocumental(int idRecursoDocumental, String year, String clasificacion, String isbn, String titulo, String autor, String idPrestamo, int numeroDisponible) {
        this.idRecursoDocumental = idRecursoDocumental;
        this.year = year;
        this.clasificacion = clasificacion;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.idPrestamo = idPrestamo;
        this.numeroDisponible = numeroDisponible;
    }

    

    public int getIdRecursoDocumental() {
        return idRecursoDocumental;
    }

    public void setIdRecursoDocumental(int idRecursoDocumental) {
        this.idRecursoDocumental = idRecursoDocumental;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getNumeroDisponible() {
        return numeroDisponible;
    }

    public void setNumeroDisponible(int numeroDisponible) {
        this.numeroDisponible = numeroDisponible;
    }

    
    
    
}
