package espol.edu.ec.autosell.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luizzz
 */
public class LoginView extends LoginRegisterBaseView {
    
    private StackPane login_form;
    
    // Child atr
    private TextField usernameField;
    private Label welcome = new Label();
    private Label entrar = new Label();;
    
    public LoginView() {
        super();
        login_form = new StackPane();
        
        // View Logic
        
        //  --------------- Login Form -----------------
        VBox logform = new VBox(10);
        welcome.setText("Bienvenido!");
        welcome.setFont(Font.font(30));
        
        entrar.setText("Entrar");
        entrar.setFont(Font.font(10));
        
        // Configs
        HBox.setHgrow(login_form, Priority.ALWAYS);
        BorderStroke borderStroke = new BorderStroke(
                Color.BLUE, // Color del borde
                BorderStrokeStyle.SOLID, // Estilo de línea (en este caso, línea sólida)
                null, // Radios de esquina (null para esquinas rectas)
                new BorderWidths(2) // Grosor del borde (en este caso, 2 píxeles)
        );
        //login_form.setBorder(new Border(borderStroke));
        //login_form.setAlignment(Pos.CENTER);
        logform.setBorder(new Border(borderStroke));
        
        logform.setAlignment(Pos.CENTER_LEFT);
        // Settear los elementos
        logform.getChildren().addAll(welcome, entrar);
        login_form.getChildren().add(logform);
        
        base_view.getChildren().add(login_form);
    }
    
    public HBox getView() {
        return super.getView();
    }
}
