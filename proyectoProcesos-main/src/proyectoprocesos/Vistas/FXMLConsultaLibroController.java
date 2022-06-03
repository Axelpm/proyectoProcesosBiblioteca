
package proyectoprocesos.Vistas;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import proyectoprocesos.DAO.LibroDAO;
import proyectoprocesos.POJO.Libro;
import proyectoprocesos.Util.Mensaje;


public class FXMLConsultaLibroController implements Initializable {

    @FXML
    private TextField lbBusqueda;
    @FXML
    private TableColumn colTitulo;
    @FXML
    private TableColumn colAutor;
    @FXML
    private TableColumn colArea;
    @FXML
    private TableColumn colAnio;
    @FXML
    private TableColumn colClasificacion;
    @FXML
    private TableColumn colISBN;
    @FXML
    private TableView<Libro> tbLibros;

    private ObservableList<Libro> libros;
    @FXML
    private TableColumn colEditorial;
    @FXML
    private TableColumn colEdicion;
    @FXML
    private TableColumn colSubArea;
    @FXML
    private TableColumn colDisponibles;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarElementosTabla();
        cargarInformacionTablaLibros();
        buscarLibro();
    }    

    private void configurarElementosTabla(){
        libros= FXCollections.observableArrayList();
        this.colTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
        this.colAutor.setCellValueFactory(new PropertyValueFactory("autor"));
        this.colArea.setCellValueFactory(new PropertyValueFactory("area"));
        this.colAnio.setCellValueFactory(new PropertyValueFactory("year"));
        this.colClasificacion.setCellValueFactory(new PropertyValueFactory("clasificacion"));
        this.colISBN.setCellValueFactory(new PropertyValueFactory("isbn"));
        this.colEditorial.setCellValueFactory(new PropertyValueFactory("editorial"));
        this.colEdicion.setCellValueFactory(new PropertyValueFactory("edicion"));
        this.colSubArea.setCellValueFactory(new PropertyValueFactory("subarea"));
        this.colDisponibles.setCellValueFactory(new PropertyValueFactory("numeroDisponible"));
    }
    
    private void cargarInformacionTablaLibros(){
        ArrayList<Libro> librosBD= LibroDAO.obtenerInfoLibros();
        if(librosBD != null){
        libros.addAll(librosBD);
        tbLibros.setItems(libros);
        }else{
            Mensaje.mostrarAlerta("Error de conexión", "Error al recupera la información de los libros",
                    Alert.AlertType.ERROR);
            Stage stageLibro = (Stage) tbLibros.getScene().getWindow();
            stageLibro.close();
        }
    }
    
    @FXML
    private void clicSalir(ActionEvent event) {
        Stage stageLibro = (Stage) tbLibros.getScene().getWindow();
        stageLibro.close();
    }
    
    private void buscarLibro(){
        if(libros.size() > 0){
            FilteredList<Libro> filtroLibros= new FilteredList<>(libros, p-> true);
            
            lbBusqueda.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    filtroLibros.setPredicate(busqueda -> {
                        if(newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        String cadenaMinuscula= newValue.toLowerCase();
                        if(busqueda.getTitulo().toLowerCase().contains(cadenaMinuscula)){
                            return true;
                        }else if(busqueda.getAutor().toLowerCase().contains(cadenaMinuscula)){
                            return true;
                        }
                        return false;
                    });
                    
                }
            });
            SortedList<Libro> ordenamientoLibros= new SortedList<>(filtroLibros);
            ordenamientoLibros.comparatorProperty().bind(tbLibros.comparatorProperty());
            tbLibros.setItems(ordenamientoLibros);
        }
    }
    
}
