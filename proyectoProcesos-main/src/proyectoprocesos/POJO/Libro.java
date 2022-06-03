package proyectoprocesos.POJO;


public class Libro extends RecursoDocumental{
    private int idLibro;
    private String editorial;
    private String edicion;
    private String area;
    private String subarea;

    public Libro() {
    }

    public Libro(int idLibro, String editorial, String edicion, String area, String subarea, int idRecursoDocumental, String year, String clasificacion, String isbn, String titulo, String autor, String idPrestamo, int numeroDisponible) {
        super(idRecursoDocumental, year, clasificacion, isbn, titulo, autor, idPrestamo, numeroDisponible);
        this.idLibro = idLibro;
        this.editorial = editorial;
        this.edicion = edicion;
        this.area = area;
        this.subarea = subarea;
    }

    

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edición) {
        this.edicion = edición;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSubarea() {
        return subarea;
    }

    public void setSubarea(String subarea) {
        this.subarea = subarea;
    }

    
    
}
