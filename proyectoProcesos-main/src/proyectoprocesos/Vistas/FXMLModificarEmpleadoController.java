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
import proyectoprocesos.POJO.Empleado;
import proyectoprocesos.Util.Mensaje;
import proyectoprocesos.Util.ValidacionCorreo;

/**
 * FXML Controller class
 *
 * @author victormanuel
 */
public class FXMLModificarEmpleadoController implements Initializable {
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    Empleado empleadoObj = new Empleado();
    Mensaje mensajesEmergentes = new Mensaje();
    Empleado empleadoInfo = empleadoDAO.obtenerInformacionEmpleado(Empleado.getFkIdUsuario());
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
    private TextField textoDireccion;
    @FXML
    private ComboBox comboBoxPuesto;
    @FXML
    private TextField textoNombreUsuario;
    @FXML
    private PasswordField textoContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatoNumerico();
        comboBoxPuesto.getItems().addAll("Encargado", "Auxiliar");
        ingresarInformacionEmpleado();
    }    

    @FXML
    private void botonSalir(ActionEvent event) {
        navegacionEntreVentanas("FXMLConsultarEmpleado.fxml");
    }

    @FXML
    private void botonModificar(ActionEvent event) {
        int idUsuario = Empleado.getFkIdUsuario();
        String puesto = (String) comboBoxPuesto.getValue();
        empleadoObj.setApellido(textoApellido.getText());
        empleadoObj.setClavePersonal(textoClavePersonal.getText());
        empleadoObj.setPassword(textoContraseña.getText());
        empleadoObj.setCorreoElectronico(textoCorreoElectronico.getText());
        empleadoObj.setDireccion(textoDireccion.getText());
        empleadoObj.setNombre(textoNombre.getText());
        empleadoObj.setNombreUsuario(textoNombreUsuario.getText());
        empleadoObj.setTelefono(textoTelefono.getText());
        empleadoObj.setPuesto(puesto);
        if(validarCorreo.isValid(textoCorreoElectronico.getText()) == true){
            if(idUsuario != 0){
                if(datosLlenos() == false){
                    empleadoDAO.actualizarInformacionEmpleado(empleadoObj, idUsuario);
                    mensajesEmergentes.mostrarAlerta("Datos ingresados correctamente", 
                            "Los datos ingresados del usuario "+ textoClavePersonal.getText() + 
                                    " se han registrado de manera correcta en la base de datos" , Alert.AlertType.INFORMATION);
                    navegacionEntreVentanas("FXMLConsultarEmpleado.fxml");
                }
                else{
                    mensajesEmergentes.mostrarAlerta("Los campos solicitados están vacios", ""
                        + "Los campos que se solicitan están vacíos, por favor de llenar todos los campos solicitados "
                        + "para concluir con el registro", Alert.AlertType.WARNING);
                }
            }
            else{
                mensajesEmergentes.mostrarAlerta("Error de al registrar al empleado", 
                                "No se pudo registrar al empleado correctamente intete nuevamente", Alert.AlertType.WARNING);
            }
        }
        else{
            mensajesEmergentes.mostrarAlerta("Correo electrónico inválido.", "El correo electr"
                        + "ónico ingresado no es un correo electrónico válido por el sistema.", Alert.AlertType.WARNING);
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
    
    public void ingresarInformacionEmpleado(){
        textoApellido.setText(empleadoInfo.getApellido());
        textoClavePersonal.setText(empleadoInfo.getClavePersonal());
        textoContraseña.setText(empleadoInfo.getPassword());
        textoCorreoElectronico.setText(empleadoInfo.getCorreoElectronico());
        textoDireccion.setText(empleadoInfo.getDireccion());
        textoNombre.setText(empleadoInfo.getNombre());
        textoNombreUsuario.setText(empleadoInfo.getNombreUsuario());
        textoTelefono.setText(empleadoInfo.getTelefono());
        comboBoxPuesto.setValue(empleadoInfo.getPuesto());
    }
    
    public boolean datosLlenos(){
        String nombreUsuario = textoNombreUsuario.getText();
        String nombre = textoNombre.getText();
        String apellido = textoApellido.getText();
        String direccion = textoDireccion.getText();
        String telefono = textoTelefono.getText();
        String password = textoContraseña.getText();
        String correoElectronico = textoCorreoElectronico.getText();
        String puesto = (String) comboBoxPuesto.getValue();
        String clavePersonal = textoClavePersonal.getText();
        if(nombreUsuario.isEmpty() || nombre.isEmpty()|| apellido.isEmpty() || direccion.isEmpty()
                || telefono.isEmpty() || password.isEmpty()|| correoElectronico.isEmpty() || puesto == null
                || clavePersonal.isEmpty()){
            return true;
        }
        return false;
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
