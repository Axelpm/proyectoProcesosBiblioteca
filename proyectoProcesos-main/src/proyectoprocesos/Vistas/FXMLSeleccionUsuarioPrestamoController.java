/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import proyectoprocesos.Util.Mensaje;

/**
 * FXML Controller class
 *
 * @author MrMMP
 */
public class FXMLSeleccionUsuarioPrestamoController implements Initializable {

    @FXML
    private Label lb_prestamo;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickEstudiante(ActionEvent event) {
    }

    @FXML
    private void clickProfesor(ActionEvent event) {
    }

    @FXML
    private void ClickCancelar(ActionEvent event) {
        Stage stage = (Stage) lb_prestamo.getScene().getWindow();
        try{                    
            String path = "FXMLSeleccionUsuarioPrestamo.fxml";
            Parent cargaFXMLPrincipal= FXMLLoader.load(getClass().getResource(path));
            Scene scenePrincipal= new Scene(cargaFXMLPrincipal);
            stage.setScene(scenePrincipal);
            stage.show();    
        }catch(IOException e){
            Mensaje.mostrarAlerta("Error de aplicacion", "Lo sentimos no se ha podido acceder a la sección Recursos "
                + "Documentales", Alert.AlertType.ERROR);
        }
    }
    
}
