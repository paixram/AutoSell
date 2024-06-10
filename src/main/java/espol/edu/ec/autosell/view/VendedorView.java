/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.model.Vehiculo;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

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
        
        // Agregar los botones en la parte inferior
        HBox bottomBar = crearBarraInferior();
        super.root.setBottom(bottomBar);

        // Mostrar el contenido en la ventana
        
        System.out.println("Agregado");
        
        super.showPublications();
    }

    private HBox crearBarraInferior() {
        HBox bottomBar = new HBox(0);
        bottomBar.setAlignment(Pos.CENTER);

        Button crearButton = new Button("Crear Vehículo");
        configurarBoton(crearButton);
        crearButton.setOnAction(event -> {
            App.showCrearVehiculo();
        });

        Button editarButton = new Button("Editar Vehículo");
        configurarBoton(editarButton);
        editarButton.setOnAction(event -> {
            new GestionarVehiculosView(vehiculos, true, this);
        });
            
        Button eliminarButton = new Button("Eliminar Vehículo");
        configurarBoton(eliminarButton);
        eliminarButton.setOnAction(event -> {
            new GestionarVehiculosView(vehiculos, false, this);
        });
        
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
