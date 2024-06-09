/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author José Miguel
 */


public class VendedorView extends PrincipalView{

    public VendedorView() {
        super(true);
        initialize();
    }

    private void initialize() {


        // Agregar la barra de búsqueda y el filtro
        VBox searchBarAndFilter = new VBox(10);
        searchBarAndFilter.setAlignment(Pos.TOP_LEFT);

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar...");
        searchBarAndFilter.getChildren().add(searchField);

        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Marca y Modelo", "Precio", "Kilometraje");
        filterComboBox.setPromptText("Seleccionar filtro...");
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

        Button editarButton = new Button("Editar Vehículo");
        configurarBoton(editarButton);

        Button eliminarButton = new Button("Eliminar Vehículo");
        configurarBoton(eliminarButton);

        bottomBar.getChildren().addAll(crearButton, editarButton, eliminarButton);
        return bottomBar;
    }

    private void configurarBoton(Button boton) {
        boton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +
            "-fx-border-radius: 10; " +
            "-fx-font-size: 15px; " +
            "-fx-padding: 5 10; " +
            "-fx-border-color: black; " +
            "-fx-border-width: 2px;" +
            "-fx-cursor: hand;"
        );
    }
    
}
