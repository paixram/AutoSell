/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.controller;
import dumpfmm.Response;
import espol.edu.ec.autosell.App;
import java.util.List;
import espol.edu.ec.autosell.model.Usuario;
import espol.edu.ec.autosell.utils.UserRole;
import espol.edu.ec.autosell.view.LoginView;
import java.util.LinkedHashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
/**
 *
 * @author José Miguel
 */
public class LoginController {
    
    private Usuario user_model;
    private LoginView login_view;
    //private List<String[]> userData;
    private Stage primaryStage;
    public LoginController(Usuario um, LoginView lv, Stage primaryStage) {
        user_model = um;
        login_view = lv;
        this.primaryStage = primaryStage;
        initialize();
        
    }
    
    public void initialize() {
        //this.usuarios=Qu;
        login_view.getLoginButton().setOnAction(e -> validateLogin());
        login_view.getSignUpButton().setOnAction(e -> App.ShowRegister());
    }

    private void validateLogin() {
        String username = login_view.getUsernameField().getText();
        String contrasenia = login_view.getContraseniaField().getText();
        
        String query_user_find = "FROM Usuario GET .. WHEN username=\"" + username + "\"";
        Response r = App.database.executeQuery(query_user_find);
        
        
        
        if(r.isEmptyResult()) {
            showAlert("Error", "Usuario no encontrado", AlertType.ERROR);
            return;
        }
        
        LinkedHashMap<String, Object> user_data = r.getUnique();
            
        if(!user_data.get("contrasenia").equals(contrasenia)) {   
            showAlert("Error", "Contraseña no valida", AlertType.ERROR);
            return;
        }

            
        showAlert("Succesfull", "Usuario encontrado", AlertType.CONFIRMATION);
        
        switch((String)(user_data.get("rol"))) {
            case "VENDEDOR":
                Usuario vendedor = new Usuario((int)user_data.get("idUsuario"), (String)user_data.get("username"), (String)user_data.get("contrasenia"), UserRole.valueOf(user_data.get("rol")));
                
                App.ShowVendedorView(vendedor);
                break;
            case "COMPRADOR":
                Usuario comprador = new Usuario((int)user_data.get("idUsuario"), (String)user_data.get("username"), (String)user_data.get("contrasenia"), UserRole.valueOf(user_data.get("rol")));
                
                App.showCompradorView(comprador);
        }
        
    }
    
  
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
