package espol.edu.ec.autosell.view;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Luizzz
 */
public class LoginView extends LoginRegisterBaseView {
    
    private StackPane login_form;
    
    // Child atr
    private TextField usernameField;
    private Label welcome = new Label();
    //private Label entrar = new Label();;
    private PasswordField contraseniaField;
    private Button loginButton;
    private Button signUpButton;
    private Label signUp = new Label();;
    
    public LoginView() {
        super();
        
        login_form = new StackPane();
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxWidth(250);
        usernameField.setPrefHeight(40); // Set preferred height
        usernameField.setPrefWidth(250);
        
        contraseniaField = new PasswordField();
        contraseniaField.setPromptText("Password");
        contraseniaField.setMaxWidth(250);
        contraseniaField.setPrefHeight(40); // Set preferred height
        contraseniaField.setPrefWidth(250); 
        
        loginButton = new Button("Login");
        configurarBoton(loginButton);
        
        
        signUpButton=new Button("Signup");
        configurarBoton(signUpButton);
        
        
        // View Logic
        Font fontBold = Font.font("Arial", FontWeight.BOLD, 12);
        loginButton.setFont(fontBold);
        signUpButton.setFont(fontBold);
        
        //  --------------- Login Form -----------------
        VBox logform = new VBox(10);
        welcome.setText("Sign in");
        welcome.setFont(Font.font(30));
        signUp.setText("New User?");
        signUp.setFont(fontBold);
        
        
        // Configs
        HBox.setHgrow(login_form, Priority.ALWAYS);
       
        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll( signUp,signUpButton);
        buttonsBox.setAlignment(Pos.CENTER);
        logform.setAlignment(Pos.CENTER);
        
        // Settear los elementos
        logform.getChildren().addAll(welcome, usernameField, contraseniaField, loginButton,buttonsBox);
        login_form.getChildren().add(logform);
        
        base_view.getChildren().add(login_form);
    }
    
    public HBox getView() {
        return super.getView();
    }
      public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getContraseniaField() {
        return contraseniaField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getSignUpButton() {
         return signUpButton;
    }
    
    private void configurarBoton(Button button) {
        button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 0; -fx-background-radius: 0; -fx-font-size: 20px; -fx-cursor: hand;");
        button.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
        button.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), button);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
    }
}
