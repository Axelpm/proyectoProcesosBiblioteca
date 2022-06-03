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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoprocesos.Util.Mensaje;

/**
 * FXML Controller class
 *
 * @author MrMMP
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Label lb_Menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickRegistrarPrestamo(ActionEvent event) {
        //navegacionEntreVentanas("FXMLRegistrarPrestamoAlumno.fxml");
        Stage stage = (Stage) lb_Menu.getScene().getWindow();
        try{                    
            String path = "FXMLRegistrarPrestamoAlumno.fxml";
            Parent cargaFXMLPrincipal= FXMLLoader.load(getClass().getResource(path));
            Scene scenePrincipal= new Scene(cargaFXMLPrincipal);
            stage.setScene(scenePrincipal);
            stage.show();    
        }catch(IOException e){
            Mensaje.mostrarAlerta("Error de aplicación", "Lo sentimos no se ha podido acceder a la sección de registro de préstamos"
                + "Documentales", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clickConsultarRecursos(ActionEvent event) {
        //navegacionEntreVentanas("FXMLConsultaLibro.fxml");
        try{
        Parent consultarLibro= FXMLLoader.load(getClass().getResource("FXMLConsultaLibro.fxml"));
        Scene sceneConsultarLibro= new Scene(consultarLibro);
        Stage stage = new Stage();
        stage.setScene(sceneConsultarLibro);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        }catch(IOException ex){
            Mensaje.mostrarAlerta("Error de aplicacion", "Lo sentimos no se ha podido acceder a la sección de consultar Recursos",
                Alert.AlertType.ERROR);
        }   
    }

    @FXML
    private void clickGestionEmpleados(ActionEvent event) {
        //navegacionEntreVentanas("FXMLConsultarEmpleado.fxml");
        try{
        Parent consultarLibro= FXMLLoader.load(getClass().getResource("FXMLConsultarEmpleado.fxml"));
        Scene sceneConsultarLibro= new Scene(consultarLibro);
        Stage stage = new Stage();
        stage.setScene(sceneConsultarLibro);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        }catch(IOException ex){
            System.err.println("Error de carga");
        }
        
    }
    
    public void navegacionEntreVentanas(String url){
        try {
            Stage stage = (Stage) lb_Menu.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(url)));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
