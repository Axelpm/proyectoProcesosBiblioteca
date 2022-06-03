package proyectoprocesos.Vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoprocesos.DAO.EmpleadoDAO;
import proyectoprocesos.POJO.Empleado;
import proyectoprocesos.Util.Mensaje;

/**
 * FXML Controller class
 *
 * @author victormanuel
 */
public class FXMLEliminarEmpleadoController implements Initializable {
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    Empleado empleadoObj = new Empleado();
    Mensaje mensajesEmergentes = new Mensaje();
    Empleado empleadoInfo = empleadoDAO.obtenerInformacionEmpleado(Empleado.getFkIdUsuario());
    
    private int idUsuario = Empleado.getFkIdUsuario();

    @FXML
    private TextField textoConfirmacion;
    @FXML
    private Label labelClavePersonal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelClavePersonal.setText(empleadoInfo.getClavePersonal());
    }    

    @FXML
    private void botonAceptar(ActionEvent event) {
        String clavePersona = empleadoInfo.getClavePersonal();
        String confirmaClave = textoConfirmacion.getText();
        if(!textoConfirmacion.getText().isEmpty()){
            if(confirmaClave.equals(empleadoInfo.getClavePersonal())){
                empleadoDAO.eliminarEmpleado(idUsuario);
                empleadoDAO.eliminarUsuarioEmpleado(idUsuario);
                mensajesEmergentes.mostrarAlerta("Eliminacion confirmada", 
                            "El empleado con la clave personal: "+ clavePersona + 
                                    " se ha eliminado de manera correcta del sistema" , Alert.AlertType.INFORMATION);
                navegacionEntreVentanas("FXMLConsultarEmpleado.fxml");
            }
            else{
                mensajesEmergentes.mostrarAlerta("Error de datos ingresado", "La clave soliditada para validar su eliminacion no es"
                        + " correcta, intentelo nuevamente.", Alert.AlertType.WARNING);
            }
        }
        else{
            mensajesEmergentes.mostrarAlerta("Los campos solicitados están vacios", ""
                    + "Los campos que se solicitan están vacíos, por favor de llenar todos los campos solicitados "
                    + "para concluir con el registro", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void botonCancelar(ActionEvent event) {
        navegacionEntreVentanas("FXMLConsultarEmpleado.fxml");
    }
    
    public void navegacionEntreVentanas(String url){
        try {
            Stage stage = (Stage) textoConfirmacion.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
