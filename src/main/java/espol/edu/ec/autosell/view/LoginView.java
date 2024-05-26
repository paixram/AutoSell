package espol.edu.ec.autosell.view;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luizzz
 */
public class LoginView {
    
    private Pane ViewParent;
    
    // Child atr
    private TextField usernameField;
    
    public LoginView() {
        ViewParent = new Pane();
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        
        ViewParent.getChildren().addAll(usernameField);
    }
    
    public Pane getView() {
        return ViewParent;
    }
}
