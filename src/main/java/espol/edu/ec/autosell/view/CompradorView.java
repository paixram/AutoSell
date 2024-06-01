/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author José Miguel
 */
public class CompradorView {
    private Stage stage;

    public CompradorView() {
        stage = new Stage();
        initialize();
    }

    private void initialize() {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Bienvenido, Comprador!");
        root.getChildren().add(welcomeLabel);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Vista del Comprador");
    }

    public void show() {
        stage.show();
    }
}