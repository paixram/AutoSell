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
import espol.edu.ec.autosell.model.Usuario;
import espol.edu.ec.autosell.utils.Metodos;
import espol.edu.ec.autosell.view.LoginView;
import espol.edu.ec.autosell.utils.UserRole;
import espol.edu.ec.autosell.view.CompradorView;
import espol.edu.ec.autosell.view.RegisterView;
import espol.edu.ec.autosell.view.VendedorView;
import java.util.ArrayList;
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
    private List<Usuario> usuarios= new ArrayList<>();
    private List<String[]> userData;
    private Stage primaryStage;
    public LoginController(Usuario um, LoginView lv, Stage primaryStage) {
        user_model = um;
        login_view = lv;
        this.primaryStage = primaryStage;
        initialize();
        this.userData = Metodos.readDataFromFile("src/main/resources/file/archivo.txt");
        this.usuarios = Metodos.readUsersFromFile("src/main/resources/file/archivo.txt");
    }
    
    public void initialize() {
        login_view.getLoginButton().setOnAction(e -> validateLogin());
        login_view.getSignUpButton().setOnAction(e -> showRegister());
    }

    private void validateLogin() {
        String idUsuario = login_view.getUsernameField().getText();
        String contrasenia = login_view.getContraseniaField().getText();

        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario().equals(idUsuario) && usuario.validarContrasenia(contrasenia)) {
                primaryStage.close();
                if (usuario.getRole() == UserRole.COMPRADOR) {
                    
                    
                    new CompradorView().show();
                } else if (usuario.getRole() == UserRole.VENDEDOR) {
                    
                    
                    new VendedorView().show();
                }
                return;
            }
        }

        showAlert("Error", "Usuario o contraseña incorrectos", AlertType.ERROR);
    }
    private void showRegister() {
        RegisterView registerView = new RegisterView();
        registerView.show();
    }
    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
