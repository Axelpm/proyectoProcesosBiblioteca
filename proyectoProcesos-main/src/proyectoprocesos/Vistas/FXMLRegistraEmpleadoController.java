
package proyectoprocesos.Vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoprocesos.DAO.EmpleadoDAO;
import proyectoprocesos.Util.Mensaje;
import proyectoprocesos.Util.ValidacionCorreo;

/**
 * FXML Controller class
 *
 * @author victormanuel
 */
public class FXMLRegistraEmpleadoController implements Initializable {
    EmpleadoDAO empleadoRegistraInformacion = new EmpleadoDAO();
    Mensaje mensajesEmergentes = new Mensaje();
    ValidacionCorreo validarCorreo = new ValidacionCorreo();

    @FXML
    private TextField textoClavePersonal;
    @FXML
    private TextField textoNombre;
    @FXML
    private TextField textoApellido;
    @FXML
    private TextField textoCorreoElectronico;
    @FXML
    private TextField textoTelefono;
    @FXML
    private TextField textoDoreccion;
    @FXML
    private ComboBox comboBoxPuesto;
    @FXML
    private TextField textoNombreUsuario;
    @FXML
    private PasswordField textoContraseña;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatoNumerico();
        comboBoxPuesto.getItems().addAll("Encargado", "Auxiliar");
    }    

    @FXML
    private void botonSalir(ActionEvent event) {
        navegacionEntreVentanas("FXMLConsultarEmpleado.fxml");
    }

    @FXML
    private void botonRegistrar(ActionEvent event) {
        int idUsuario;
        String nombreUsuario = textoNombreUsuario.getText();
        String nombre = textoNombre.getText();
        String apellido = textoApellido.getText();
        String direccion = textoDoreccion.getText();
        String telefono = textoTelefono.getText();
        String password = textoContraseña.getText();
        String correoElectronico = textoCorreoElectronico.getText();
        String puesto = (String) comboBoxPuesto.getValue();
        String clavePersonal = textoClavePersonal.getText();
        idUsuario = empleadoRegistraInformacion.encontrarUsuarioEmpleado(textoCorreoElectronico.getText());
        if(nombreUsuario.isEmpty() || nombre.isEmpty()|| apellido.isEmpty() || direccion.isEmpty()
                || telefono.isEmpty() || password.isEmpty()|| correoElectronico.isEmpty() || puesto == null
                || clavePersonal.isEmpty()){
            mensajesEmergentes.mostrarAlerta("Los campos solicitados están vacios", ""
                    + "Los campos que se solicitan están vacíos, por favor de llenar todos los campos solicitados "
                    + "para concluir con el registro", Alert.AlertType.WARNING);
        }
        else{
            if(validarCorreo.isValid(correoElectronico) == true){
                if(idUsuario == 0){
                    empleadoRegistraInformacion.registrarUsuarioEmpleado(nombreUsuario, 
                            nombre, apellido, direccion, telefono, password, correoElectronico);
                    idUsuario = empleadoRegistraInformacion.encontrarUsuarioEmpleado(textoCorreoElectronico.getText());
                    if(idUsuario != 0){
                        empleadoRegistraInformacion.registrarEmpledoa(clavePersonal, puesto, idUsuario);
                        limpiarCampos();
                        mensajesEmergentes.mostrarAlerta("Registro de empleado", 
                                "Se ha registrado al nuevo personal correctamente.", Alert.AlertType.INFORMATION);
                    }
                    else if(idUsuario == 0){
                        mensajesEmergentes.mostrarAlerta("Error de al registrar al empleado", 
                                "No se pudo registrar al empleado correctamente intete nuevamente", Alert.AlertType.WARNING);
                    }
                }
                else{
                    mensajesEmergentes.mostrarAlerta("El empleado ya está registrado", "El usuario de tipo empleado que esta"
                            + "dando de alta, ya se encuentra registrado, intente con otros datos diferentes", Alert.AlertType.INFORMATION);
                }
            }
            else{
                mensajesEmergentes.mostrarAlerta("Correo electrónico inválido.", "El correo electr"
                        + "ónico ingresado no es un correo electrónico válido por el sistema.", Alert.AlertType.WARNING);
            }
        }
    }
    
    public void navegacionEntreVentanas(String url){
        try {
            Stage stage = (Stage) textoApellido.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void limpiarCampos(){
        textoApellido.setText(null);
        textoClavePersonal.setText(null);
        textoContraseña.setText(null);
        textoCorreoElectronico.setText(null);
        textoDoreccion.setText(null);
        textoNombre.setText(null);
        textoNombreUsuario.setText(null);
        textoTelefono.setText("");
        comboBoxPuesto.setValue(null);
    }
    
    public void formatoNumerico(){
        textoTelefono.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 if(!newValue.matches("\\d*")){
                     textoTelefono.setText(newValue.replaceAll("[^\\d]", ""));
                 }               
            }               
        });
    }
    
}
