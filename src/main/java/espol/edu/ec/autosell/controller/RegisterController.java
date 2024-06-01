/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import espol.edu.ec.autosell.model.Usuario;
import espol.edu.ec.autosell.utils.Metodos;
import espol.edu.ec.autosell.utils.UserRole;
import espol.edu.ec.autosell.view.RegisterView;
import espol.edu.ec.autosell.view.CompradorView;
import espol.edu.ec.autosell.view.VendedorView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

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
        this.usuarios = Metodos.readUsersFromFile("src/main/resources/file/archivo.txt");
    }
    
    public void initialize() {
        registerView.getSignUpButton().setOnAction(e -> registerUser());
    }

    private void registerUser() {
        String username = registerView.getUsernameField().getText();
        String password = registerView.getPasswordField().getText();
        UserRole role = registerView.getRoleComboBox().getValue();

      
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(username)) {
                showAlert("Error", "El usuario ya existe", AlertType.ERROR);
                return;
            }
        }

        
        Usuario nuevoUsuario = new Usuario(username, password, role);
        usuarios.add(nuevoUsuario);

        
        Metodos.writeUsersToFile("src/main/resources/file/archivo.txt", usuarios);

        
        showAlert("Registro Exitoso", "Usuario registrado correctamente", AlertType.INFORMATION);
        if (role == UserRole.COMPRADOR) {
            new CompradorView().show();
        } else if (role == UserRole.VENDEDOR) {
            new VendedorView().show();
        }

        
        Stage stage = (Stage) registerView.getSignUpButton().getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
