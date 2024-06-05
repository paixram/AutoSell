/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.controller;

import dumpfmm.Response;
import espol.edu.ec.autosell.App;
import javafx.scene.Scene;
import javafx.stage.Stage;
import espol.edu.ec.autosell.model.Usuario;
import espol.edu.ec.autosell.utils.Malloc;
import espol.edu.ec.autosell.utils.UserRole;
import espol.edu.ec.autosell.view.RegisterView;
import espol.edu.ec.autosell.view.CompradorView;
import espol.edu.ec.autosell.view.VendedorView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Luizzz
 */

public class RegisterController {
    
    private RegisterView registerView;
    private List<Usuario> usuarios = new ArrayList<>();

    public RegisterController(RegisterView rv) {
        registerView = rv;
        initialize();
        //this.usuarios = Metodos.readUsersFromFile("src/main/resources/file/archivo.txt");
    }
    
    public void initialize() {
        registerView.getSignUpButton().setOnAction(e -> registerUser());
    }

    private void registerUser() {
        String username = registerView.getUsernameField().getText();
        String password = registerView.getPasswordField().getText();
        UserRole role = registerView.getRoleComboBox().getValue();

      
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                showAlert("Error", "El usuario ya existe", AlertType.ERROR);
                return;
            }
        }

        
        //Usuario nuevoUsuario = new Usuario(username, password, role);
        //usuarios.add(nuevoUsuario);
        String query = "FROM Usuario GET .. WHEN username = \"" + username + "\""; // Verificar si usuario existe
        Response results = App.database.executeQuery(query);
        
        if(!results.isEmptyResult()) {
            showAlert("User Already Exists", "Este usuario ya existe en la base de datos", AlertType.WARNING);
            return;
        }
        
        //Metodos.writeUsersToFile("src/main/resources/file/archivo.txt", usuarios);
        
        // Insertar el usuario en la DB
        String register_query = "FROM Usuario SET AUTOINCREMENT, \"" + username + "\",\"" + password + "\"" + "\"" + role.toString()+ "\"";
        
        App.database.executeQuery(register_query);
        
        showAlert("Registro Exitoso", "Usuario registrado correctamente", AlertType.INFORMATION);
        App.ShowLogin();
        /*if (role == UserRole.COMPRADOR) {
            new CompradorView().show();
        } else if (role == UserRole.VENDEDOR) {
            new VendedorView().show();
        }*/

        
        //Stage stage = (Stage) registerView.getSignUpButton().getScene().getWindow();
        //stage.close();
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
