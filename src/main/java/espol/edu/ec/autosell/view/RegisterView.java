
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.utils.UserRole;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 *
 * @author Luizzz
 */

public class RegisterView extends LoginRegisterBaseView {
    
    private StackPane register_form;
    private TextField usernameField;
    private PasswordField passwordField;
    private ComboBox<UserRole> roleComboBox;
    private Button signUpButton;
    private Label welcome = new Label();

    public RegisterView() {
        super();
        register_form = new StackPane();

        
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(200);
        usernameField.setPrefHeight(40);
        usernameField.setPrefWidth(200);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setMaxWidth(200);
        passwordField.setPrefHeight(40);
        passwordField.setPrefWidth(200); 

        
        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll(UserRole.COMPRADOR, UserRole.VENDEDOR);
        roleComboBox.setMaxWidth(200);
        roleComboBox.setPrefHeight(40);
        roleComboBox.setPrefWidth(200);

        
        signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: black;" + "-fx-text-fill: white;" + "-fx-background-radius: 5; " + "-fx-border-radius: 10; " +"-fx-font-size: 15px; " + "-fx-padding: 5 10; " + "-fx-border-color: black; " +  "-fx-border-width: 2px;" +   "-fx-cursor: hand;" );
        Font fontBold = Font.font("Arial", FontWeight.BOLD, 12);
        signUpButton.setFont(fontBold);

        
        VBox regform = new VBox(10);
        welcome.setText("Register");
        welcome.setFont(Font.font(30));

        regform.setAlignment(Pos.CENTER);
        regform.getChildren().addAll(welcome, usernameField, passwordField, roleComboBox, signUpButton);
        register_form.getChildren().add(regform);

        base_view.getChildren().add(register_form);
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public ComboBox<UserRole> getRoleComboBox() {
        return roleComboBox;
    }

    public Button getSignUpButton() {
        return signUpButton;
    }
}