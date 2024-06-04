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
        MenuBar menuBar = crearBarraSuperior();
        root.setTop(menuBar);

        VBox topContainer = new VBox();
        topContainer.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("AutoSell");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        topContainer.getChildren().addAll(menuBar, titleLabel);
        root.setTop(topContainer);// LOGICA PARA PRESENTAR LAS PUBLICACIONES

        // Barra inferior con navegación y acciones
        HBox bottomBar = crearBarraInferior();
        root.setBottom(bottomBar);
    }
    
    private HBox crearBarraInferior() {
        HBox bottomBar = new HBox(10);
        bottomBar.setAlignment(Pos.CENTER);

        Button anteriorButton = new Button("Anterior");
        Button siguienteButton = new Button("Siguiente");
        Button crearButton = new Button("Crear Vehículo");
        Button editarButton = new Button("Editar Vehículo");
        Button eliminarButton = new Button("Eliminar Vehículo");

        

        bottomBar.getChildren().addAll(anteriorButton, siguienteButton, crearButton, editarButton, eliminarButton);
        return bottomBar;
    }

    public MenuBar crearBarraSuperior() {
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Archivo");
        Menu menuEdit = new Menu("Editar");
        Menu menuView = new Menu("Ver");
       
        MenuItem menuItemNew = new MenuItem("Nuevo");
        MenuItem menuItemOpen = new MenuItem("Abrir");
        MenuItem menuItemSave = new MenuItem("Guardar");
        MenuItem menuItemExit = new MenuItem("Salir");
        menuFile.getItems().addAll(menuItemNew, menuItemOpen, menuItemSave, menuItemExit);
        
        Menu menuLogin = new Menu("Inicio de Sesión");
        MenuItem menuItemLogin = new MenuItem("Iniciar Sesión");
        menuItemLogin.setOnAction(event -> {
            // Abrir nueva ventana con LoginView
            App.ShowLogin();
        });
        menuLogin.getItems().add(menuItemLogin);

        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuLogin);

        return menuBar;
    } 
    
    public BorderPane getView() {
        return root;
    }
}

