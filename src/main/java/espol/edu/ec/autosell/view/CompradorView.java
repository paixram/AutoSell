/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.model.Comprador;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.utils.CircularLinkedList;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author José Miguel
 */
public class CompradorView extends PrincipalView {
    
    public Comprador comprador;
    
    public CompradorView() {
        super(true);
        super.searchField.textProperty().addListener((o, ov, nv) -> {
            this.filterVehicles(nv, null);
        });
        this.showPublications();
    }

    
    @Override
    public void showPublications() {
        

        // Crear y configurar el botón "Comprar"
        super.comprar = new Button("Comprar");
        styleButton(super.comprar);
        super.comprar.setOnAction(e -> {
            Vehiculo currentVehiculo = vehiculos.getCurrent();
            if (currentVehiculo != null) {
                // Lógica para comprar el vehículo
                System.out.println("Vehículo comprado: " + currentVehiculo.getMarca() + " " + currentVehiculo.getModelo());
            }
        });

        super.showPublications();
    }
    
    @Override
    public void filterVehicles(String searchText, String filterType) {
        CircularLinkedList<Vehiculo> filteredVehicles = new CircularLinkedList();
        searchText = searchText.toLowerCase();
        for(Vehiculo vehicle : this.vehiculos) {
            boolean match = false;
            if(filterType == null || filterType.equals("Marca - Modelo")) {
                match = vehicle.getMarca().toLowerCase().contains(searchText) || vehicle.getModelo().toLowerCase().contains(searchText.toLowerCase());
            }
            
            if(match) {
                filteredVehicles.add(vehicle);
            }
        }
        
        // Mostrar los filtrados
        System.out.println(filteredVehicles);
        showPublications(filteredVehicles);
    }

    private void styleButton(Button button) {
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