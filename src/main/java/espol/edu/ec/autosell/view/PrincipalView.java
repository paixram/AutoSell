package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.model.Vehiculo;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import espol.edu.ec.autosell.utils.Metodos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


public class PrincipalView {
    private Stage stage;
    private ListView<Vehiculo> listaVehiculos;
    private BorderPane root;
    
    public PrincipalView(boolean esVendedor) {
        stage = new Stage();
        inicializar();
    }

    private void inicializar() {
        root = new BorderPane();
        MenuBar menuBar = crearBarraSuperior();
        root.setTop(menuBar);

        // Área central con la lista de vehículos
        listaVehiculos = new ListView<>();
        root.setCenter(listaVehiculos);

        // Barra inferior con navegación y acciones
        HBox bottomBar = crearBarraInferior();
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("AutoSell - Página Principal");
    }

    private MenuBar crearBarraSuperior() {
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
            Stage loginStage = new Stage();
            LoginView loginView = new LoginView();
            Scene loginScene = new Scene(loginView.getView(), 300, 200); // Ajusta el tamaño según sea necesario
            loginStage.setScene(loginScene);
            loginStage.setTitle("Inicio de Sesión");
            loginStage.show();
        });
        menuLogin.getItems().add(menuItemLogin);

        menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuLogin);

        return menuBar;
    }

    private void cargarVehiculos() {
        List<Vehiculo> vehiculos = Metodos.cargarVehiculos("ruta.txt");
        listaVehiculos.getItems().addAll(vehiculos);
    }

    private HBox crearBarraInferior() {
        HBox bottomBar = new HBox(10);
        bottomBar.setAlignment(Pos.CENTER);

        Button anteriorButton = new Button("Anterior");
        Button siguienteButton = new Button("Siguiente");
        Button crearButton = new Button("Crear Vehículo");
        Button editarButton = new Button("Editar Vehículo");
        Button eliminarButton = new Button("Eliminar Vehículo");

        anteriorButton.setOnAction(event -> {
            int currentIndex = listaVehiculos.getSelectionModel().getSelectedIndex();
            if (currentIndex > 0) {
                listaVehiculos.getSelectionModel().select(currentIndex - 1);
            }
        });

        siguienteButton.setOnAction(event -> {
            int currentIndex = listaVehiculos.getSelectionModel().getSelectedIndex();
            if (currentIndex < listaVehiculos.getItems().size() - 1) {
                listaVehiculos.getSelectionModel().select(currentIndex + 1);
            }
        });

        crearButton.setOnAction(event -> {
            //abrirVentanaCrearVehiculo();
            //CrearVehiculoView crearVehiculoView = new CrearVehiculoView();
            //crearVehiculoView.mostrar();
        });

        editarButton.setOnAction(event -> {
            Vehiculo vehiculoSeleccionado = listaVehiculos.getSelectionModel().getSelectedItem();
            if (vehiculoSeleccionado != null) {
                // Lógica para abrir la ventana de edición del vehículo seleccionado (por implementar)
                //abrirVentanaEditarVehiculo(vehiculoSeleccionado);
            } else {
                System.out.println("No se ha seleccionado ningún vehículo para editar."); // Mostrar un mensaje de que no se ha seleccionado ningún vehículo
            }
        });

        eliminarButton.setOnAction(event -> {
            Vehiculo vehiculoSeleccionado = listaVehiculos.getSelectionModel().getSelectedItem();
            if (vehiculoSeleccionado != null) {
                // Lógica para eliminar el vehículo seleccionado (por implementar)
                //eliminarVehiculo(vehiculoSeleccionado);
            } else {
                System.out.println("No se ha seleccionado ningún vehículo para eliminar.");// Mostrar un mensaje de que no se ha seleccionado ningún vehículo
            }
        });

        bottomBar.getChildren().addAll(anteriorButton, siguienteButton, crearButton, editarButton, eliminarButton);
        return bottomBar;
    }

    public void mostrar() {
        stage.show();
    }
}

