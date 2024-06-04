package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.model.Vehiculo;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class PrincipalView {
    
    public BorderPane root;
    
    // Items
    public Button anteriorButton = new Button();
    public Button siguienteButton = new Button();
    public Button crearButton = new Button();
    public Button editarButton = new Button();
    public Button eliminarButton = new Button();
    
    public PrincipalView() {
        //login_view.getLoginButton().setOnAction(e -> validateLogin());
        //login_view.getSignUpButton().setOnAction(e -> showRegister());
         root = new BorderPane();
        
        // Contenedor para el botón de inicio de sesión a la derecha
        Button loginButton = new Button("Iniciar Sesión");
        loginButton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +     // Bordes cuadrados
            "-fx-border-radius: 10; " +         // Bordes cuadrados
            "-fx-font-size: 15px; " +          // Tamaño de letra más grande
            "-fx-padding: 5 10; " +           // Tamaño del botón más grande
            "-fx-border-color: black; " +      // Color del borde
            "-fx-border-width: 2px;" +          // Ancho del borde
            "-fx-cursor: hand;"
        );
        loginButton.setOnAction(event -> {
            // Abrir nueva ventana con LoginView
            App.ShowLogin();
        });
        HBox loginContainer = new HBox();
        loginContainer.setAlignment(Pos.TOP_RIGHT);
        loginContainer.getChildren().add(loginButton);
        
        // Contenedor para el título AutoSell centrado
        Label titleLabel = new Label("AutoSell");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.getChildren().add(titleLabel);
        
        // Agregar los contenedores al BorderPane
        root.setTop(loginContainer);
        root.setCenter(titleContainer);

        // Barra inferior con navegación y acciones
        HBox bottomBar = crearBarraInferior();
        root.setBottom(bottomBar);
    }
    
    private HBox crearBarraInferior() {
        HBox bottomBar = new HBox(10);
        bottomBar.setAlignment(Pos.CENTER);

        Button anteriorButton = new Button("Anterior");
        anteriorButton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +     // Bordes cuadrados
            "-fx-border-radius: 10; " +         // Bordes cuadrados
            "-fx-font-size: 15px; " +          // Tamaño de letra más grande
            "-fx-padding: 5 10; " +           // Tamaño del botón más grande
            "-fx-border-color: black; " +      // Color del borde
            "-fx-border-width: 2px;" +          // Ancho del borde
            "-fx-cursor: hand;"
        );
        Button siguienteButton = new Button("Siguiente");
        siguienteButton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +     // Bordes cuadrados
            "-fx-border-radius: 10; " +         // Bordes cuadrados
            "-fx-font-size: 15px; " +          // Tamaño de letra más grande
            "-fx-padding: 5 10; " +           // Tamaño del botón más grande
            "-fx-border-color: black; " +      // Color del borde
            "-fx-border-width: 2px;" +          // Ancho del borde
            "-fx-cursor: hand;"
        );
        /* just for sellers /  solo para los vendedores
        Button crearButton = new Button("Crear Vehículo");
        Button editarButton = new Button("Editar Vehículo");
        Button eliminarButton = new Button("Eliminar Vehículo");
        */
        

        bottomBar.getChildren().addAll(anteriorButton, siguienteButton);
        return bottomBar;
    }

    
    
    public BorderPane getView() {
        return root;
    }
}

