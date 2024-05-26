/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author José Miguel
 */
public class LoginController {

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contraseniaField;

    
    private List<Usuario> usuarios = List.of(new Usuario("comprador", "123", UserRole.COMPRADOR),new Usuario("vendedor", "456", UserRole.VENDEDOR));

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        String usuario = usuarioField.getText();
        String contrasenia = contraseniaField.getText();

        for (Usuario u : usuarios) {
            if (u.getIdUsuario().equals(usuario) && u.validarContrasenia(contrasenia)) {
                if (u.getRole() == UserRole.COMPRADOR) {
                    App.setRoot("comprador");
                } else if (u.getRole() == UserRole.VENDEDOR) {
                    App.setRoot("vendedor");
                }
                return;
            }
        }

       
        System.out.println("Usuario o contraseña incorrectos");
    }
}
