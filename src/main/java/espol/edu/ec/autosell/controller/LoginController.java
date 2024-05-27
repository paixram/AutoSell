/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;

// Models
import espol.edu.ec.autosell.model.Usuario;

// views
import espol.edu.ec.autosell.view.LoginView;

// Enums
import espol.edu.ec.autosell.utils.UserRole;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 *
 * @author José Miguel
 */
public class LoginController {
    
    private Usuario user_model;
    private LoginView login_view;
    private List<Usuario> usuarios;
    public LoginController(Usuario um, LoginView lv) {
        user_model = um;
        login_view = lv;
        initialize();
    }
    
    public void initialize() {
        login_view.getLoginButton().setOnAction(e -> validateLogin());
    }

    private void validateLogin() {
        String idUsuario = login_view.getUsernameField().getText();
        String contrasenia = login_view.getContraseniaField().getText();

        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(idUsuario) && usuario.validarContrasenia(contrasenia)) {
                if (usuario.getRole() == UserRole.COMPRADOR) {
                    showAlert("Bienvenido", "Bienvenido comprador!", AlertType.INFORMATION);
                    // Aquí se puede cambiar la escena a la vista del comprador
                  
                } else if (usuario.getRole() == UserRole.VENDEDOR) {
                    showAlert("Bienvenido", "Bienvenido vendedor!", AlertType.INFORMATION);
                    // Aquí se puede cambiar la escena a la vista del vendedor
                   
                }
                return;
            }
        }

        showAlert("Error", "Usuario o contraseña incorrectos", AlertType.ERROR);
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
