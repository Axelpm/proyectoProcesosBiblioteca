package proyectoprocesos.Vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoprocesos.DAO.UsuarioDAO;
import proyectoprocesos.POJO.Usuario;
import proyectoprocesos.Util.Mensaje;

public class FXMLLoginController implements Initializable {
    UsuarioDAO conectar = new UsuarioDAO();

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private Label lb_CampoVacio;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void clickLogin(ActionEvent event) {
        if(verificarCampos()){
            consultarUsuarioBD();
        }
    }
    
    private boolean verificarCampos (){
        lb_CampoVacio.setText("");
        boolean camposCompletos = false;
        if(tf_username.getText().isEmpty()){
            lb_CampoVacio.setText("Completa los campos para iniciar sesión");
        }else if(tf_password.getText().isEmpty()){
            lb_CampoVacio.setText("Completa los campos para iniciar sesión");
        }else{
            camposCompletos = true;
        }        
        return camposCompletos;
    }
    
    private void consultarUsuarioBD (){
        try{
            Usuario usuarioRecuperado = UsuarioDAO.comprobarDatosUsuario(tf_username.getText(), tf_password.getText());
            String puesto = conectar.encontrarUsuarioEmpleado(usuarioRecuperado.getIdUsuario());
            switch(puesto){
                case "Auxiliar": case "Encargado":
                    navegacionEntreVentanas("FXMLRegistrarPrestamoAlumno.fxml");
                    break;
                case "Estudiante":
                    navegacionEntreVentanas("FXMLConsultaLibro.fxml");
                    break;
                case "Jefe":
                    navegacionEntreVentanas("FXMLConsultarEmpleado.fxml");
            }
        }catch(NullPointerException expNull){
            lb_CampoVacio.setText("Usuario o contraseña incorrectos");
        }
    }
    
    public void navegacionEntreVentanas(String url){
        try {
            Stage stage = (Stage) lb_CampoVacio.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
