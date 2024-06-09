/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.model.Vehiculo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author José Miguel
 */


public class VendedorView extends PrincipalView{
    private Vehiculo vehiculo;
    public VendedorView() {
        super(true);
        initialize();
    }

    private void initialize() {
        // Agregar la barra de búsqueda y el filtro
        VBox searchBarAndFilter = new VBox(0);
        searchBarAndFilter.setAlignment(Pos.TOP_LEFT);

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar...");
        searchField.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10; -fx-border-color: grey;");

        searchBarAndFilter.getChildren().add(searchField);

        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Marca y Modelo", "Precio", "Kilometraje");
        filterComboBox.setPromptText("Seleccionar filtro...");
        filterComboBox.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10; -fx-border-color: grey;");

        HBox.setHgrow(searchField, Priority.ALWAYS);
        
        searchBarAndFilter.getChildren().add(filterComboBox);

        //super.root.getChildren().add(searchBarAndFilter);
        super.root.setTop(searchBarAndFilter);
        // Agregar los botones en la parte inferior
        HBox bottomBar = crearBarraInferior();
        super.root.setBottom(bottomBar);

        // Mostrar el contenido en la ventana
        //setCenter(root);
        System.out.println("Agregado");

    }

    private HBox crearBarraInferior() {
        HBox bottomBar = new HBox(10);
        bottomBar.setAlignment(Pos.CENTER);

        Button crearButton = new Button("Crear Vehículo");
        configurarBoton(crearButton);
        crearButton.setOnAction(event -> {
            App.showCrearVehiculo();
        });

        Button editarButton = new Button("Editar Vehículo");
        configurarBoton(editarButton);
        editarButton.setOnAction(event -> {
            if (vehiculo != null) { // Verifica si se ha seleccionado un vehículo
                EditarVehiculoView editarVehiculoView = new EditarVehiculoView(this, vehiculo);
                Scene scene = new Scene(editarVehiculoView.getView());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("No se ha seleccionado ningún vehículo para editar.");
            }
        });
        Button eliminarButton = new Button("Eliminar Vehículo");
        configurarBoton(eliminarButton);
        
        crearButton.setMaxWidth(Double.MAX_VALUE);
        editarButton.setMaxWidth(Double.MAX_VALUE);
        eliminarButton.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(crearButton, Priority.ALWAYS);
        HBox.setHgrow(editarButton, Priority.ALWAYS);
        HBox.setHgrow(eliminarButton, Priority.ALWAYS);
        
        bottomBar.getChildren().addAll(crearButton, editarButton, eliminarButton);
        return bottomBar;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    private void configurarBoton(Button boton) {
        /*boton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +
            "-fx-border-radius: 10; " +
            "-fx-font-size: 15px; " +
            "-fx-padding: 5 10; " +
            "-fx-border-color: black; " +
            "-fx-border-width: 2px;" +
            "-fx-cursor: hand;"
        );*/
        boton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 0; -fx-background-radius: 0; -fx-font-size: 20px; -fx-cursor: hand;");

    }
    
    
}
