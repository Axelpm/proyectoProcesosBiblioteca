/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprocesos.Vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static proyectoprocesos.DAO.PrestamoDAO.registrarPrestamo;
import proyectoprocesos.POJO.Prestamo;
import proyectoprocesos.Util.Mensaje;
import proyectoprocesos.Util.Verificacion;
import static proyectoprocesos.Util.Verificacion.validarISBN;
import static proyectoprocesos.Util.Verificacion.validarMatricula;
import static proyectoprocesos.Util.Verificacion.validarRecursoBD;
import static proyectoprocesos.Util.Verificacion.verificarPrestamoVigente;
import static proyectoprocesos.Util.Verificacion.verificarPrestamos;

/**
 * FXML Controller class
 *
 * @author MrMMP
 */
public class FXMLRegistrarPrestamoController implements Initializable {

    @FXML
    private Label lb_prestamo;
    @FXML
    private ComboBox<String> cb_RecursoDocumental;
    private ObservableList<String> recursos;
    @FXML
    private TextField tf_ISBN;
    @FXML
    private TextField tf_matricula;
    @FXML
    private Label lb_datosInvalidos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRecursos();
    }    

    @FXML
    private void ClickRegistrar(ActionEvent event) {
        if(validarFormulario()){
            lb_datosInvalidos.setVisible(false);
            if(registrarPrestamo(tf_matricula.getText(), tf_ISBN.getText()) == 0){
                Mensaje.mostrarAlerta("Registro exitoso", "El registro se guardó de manera exitosa", Alert.AlertType.INFORMATION);
                tf_ISBN.setText("");
                tf_matricula.setText("");
            }else{
                Mensaje.mostrarAlerta("Registro fallido", "Favor de intentar el registro mas tarde", Alert.AlertType.ERROR);
            }
        }else{
            lb_datosInvalidos.setVisible(true);
        }
    }

    @FXML
    private void ClickCancelar(ActionEvent event) {
        Stage stage = (Stage) lb_prestamo.getScene().getWindow();
        try{                    
            String path = "FXMLLogin.fxml";
            Parent cargaFXMLPrincipal= FXMLLoader.load(getClass().getResource(path));
            Scene scenePrincipal= new Scene(cargaFXMLPrincipal);
            stage.setScene(scenePrincipal);
            stage.show();    
        }catch(IOException e){
            Mensaje.mostrarAlerta("Error de aplicación", "Lo sentimos no se ha podido acceder a la sección de registro de préstamos"
                + "Documentales", Alert.AlertType.ERROR);
        }
    }
    
    private void cargarRecursos() {
        recursos = FXCollections.observableArrayList();
        ArrayList<String> recursos_l = new ArrayList<>(Arrays.asList("Libro","Revista"));
        recursos.addAll(recursos_l);
        cb_RecursoDocumental.setItems(recursos);
    }
    
     private boolean validarFormulario(){
        boolean respuesta = true;
        if(cb_RecursoDocumental.getSelectionModel().isEmpty()){
            respuesta = false;
        }
        if(tf_ISBN.getText().isEmpty()){
            respuesta = false;
        }else{
            if(!validarISBN(tf_ISBN.getText())){
                respuesta = false;
                Mensaje.mostrarAlerta("ISBN no válido", "El ISBN introducido no es válido, favor de corregirlo.", Alert.AlertType.WARNING);
            }
            if(!validarRecursoBD(tf_ISBN.getText())){
                respuesta = false;
                Mensaje.mostrarAlerta("ISBN no encontrado", "El ISBN introducido no se encuentra en nuestra base de datos, favor de registrarlo.", Alert.AlertType.WARNING);
            }
            if(verificarPrestamoVigente(tf_ISBN.getText())){
                respuesta = false;
            }
        }
        if(tf_matricula.getText().isEmpty()){
            respuesta = false;
        }else{
            if(!validarMatricula(tf_matricula.getText())){
                respuesta = false;
                Mensaje.mostrarAlerta("Matrícula no válida", "La matrícula introducida no es válida, verifique que esté escrita de manera correcta.", Alert.AlertType.WARNING );
            }
            if(verificarPrestamos(tf_matricula.getText())){
                respuesta = false;
                Mensaje.mostrarAlerta("Préstamos activos máximo alcanzado", "Imposible registrar el préstamo, este usuario ha alcanzado el máximo de prestaciones activas.", Alert.AlertType.ERROR);
            }
        }             
        
        return respuesta;
    }
}
