
package proyectoprocesos.Vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import proyectoprocesos.DAO.EmpleadoDAO;
import proyectoprocesos.POJO.Empleado;
import proyectoprocesos.Util.Mensaje;

/**
 * FXML Controller class
 *
 * @author victormanuel
 */
public class FXMLConsultarEmpleadoController implements Initializable {
    EmpleadoDAO empleadosInformacion = new EmpleadoDAO();
    Mensaje mensaejesInformacion = new Mensaje();

    @FXML
    private TableColumn columnaClavePersonal;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaCorreo;
    @FXML
    private TableColumn columnaDireccion;
    @FXML
    private TableColumn columnaTelefono;
    @FXML
    private TableColumn columnaPuesto;
    @FXML
    private TableView<Empleado> tableViewEmpleados;
    @FXML
    private Label textoEncabezado;
    
    private ObservableList<Empleado> listaEmpleados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewEmpleados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        setTableView();
        tableViewEmpleados.setItems(empleadosInformacion.obtenerEmpleados(listaEmpleados));
    }    

    @FXML
    private void botonSalir(ActionEvent event) {
        Stage stageEmpleado = (Stage) textoEncabezado.getScene().getWindow();
        stageEmpleado.close();
    }

    @FXML
    private void botonRegistrar(ActionEvent event) {
        navegacionEntreVentanas("FXMLRegistraEmpleado.fxml");
    }

    @FXML
    private void botonEliminar(ActionEvent event) {
        seleccionarEmpleado("Eliminar");
    }

    @FXML
    private void botonModificar(ActionEvent event) {
        seleccionarEmpleado("Modificar");
    }
    
    public void navegacionEntreVentanas(String url){
        try {
            Stage stage = (Stage) textoEncabezado.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setTableView(){
        listaEmpleados = FXCollections.observableArrayList();
        this.columnaClavePersonal.setCellValueFactory(new PropertyValueFactory("clavePersonal"));
        this.columnaCorreo.setCellValueFactory(new PropertyValueFactory("correoElectronico"));
        this.columnaDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        this.columnaPuesto.setCellValueFactory(new PropertyValueFactory("puesto"));
        this.columnaTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
    }
    
    private void seleccionarEmpleado(String tipoEntrada){
        try{
            Empleado empleadoInformacion = tableViewEmpleados.getSelectionModel().getSelectedItem();
            int idUsuario = empleadoInformacion.getIdUsuario();
            if(tipoEntrada == "Modificar"){
                Empleado.setFkIdUsuario(idUsuario);
                navegacionEntreVentanas("FXMLModificarEmpleado.fxml");
            }
            else if (tipoEntrada == "Eliminar"){
                Empleado.setFkIdUsuario(idUsuario);
                navegacionEntreVentanas("FXMLEliminarEmpleado.fxml");
            }
        }catch(NullPointerException excepcionSeleccionNull){
            mensaejesInformacion.mostrarAlerta("Alerta", 
                    "Selecciones una opción en la tabla para poder modificar o eliminar un elementos", 
                    Alert.AlertType.INFORMATION);
        }
    }
}
