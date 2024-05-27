/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 *
 * @author Luizzz
 */
public abstract class LoginRegisterBaseView {
    protected HBox base_view;
    
    // Base widgets
    Label app_label = new Label();
    
    
    public LoginRegisterBaseView() {
        base_view = new HBox(100);
        base_view.setStyle("-fx-background-color: white;");
        base_view.setAlignment(Pos.CENTER);
        // Solo la parte del formulario sera dinamico;
        StackPane logo_pane = new StackPane();
        
        // View Logic
        app_label.setText("AutoSell");
        app_label.setStyle("-fx-text-fill: white;");
        app_label.setFont(Font.font(30));
        
        logo_pane.setStyle("-fx-background-color: black;");
        
        // Settings
        HBox.setHgrow(logo_pane, Priority.ALWAYS);
        
        // set elements
        logo_pane.getChildren().addAll(app_label);
        base_view.getChildren().add(logo_pane);
    }
    
    public HBox getView() {
        return base_view;
    }
}
