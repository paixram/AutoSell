/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import dumpfmm.Response;
import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.model.Vendedor;
import espol.edu.ec.autosell.utils.CircularLinkedList;
import java.util.HashMap;
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
    
    public Vendedor vendedor;
    public VendedorView(Vendedor vendedor) {
        super(true);
        this.vendedor = vendedor;
        initialize();
    }

    private void initialize() {
        
        // Agregar los botones en la parte inferior
        HBox bottomBar = crearBarraInferior();
        super.root.setBottom(bottomBar);

        // Mostrar el contenido en la ventana
        
        System.out.println("Agregado");
        
        super.searchField.textProperty().addListener((o, ov, nv) -> {
            this.filterVehicles(nv, super.filterType);
        });
        
        
        
        this.showPublications();
    }
    
    @Override
    public void filterVehicles(String searchText, String filterType) {
        if(searchText.equals("")) {
            showPublications(vehiculos);
            System.out.println("POR AHI YO NO PÁSO");
            return;
        }
        CircularLinkedList<Vehiculo> filteredVehicles = new CircularLinkedList();
        searchText = searchText.toLowerCase();
        
        for(Vehiculo vehicle : this.vehiculos) {
            boolean match = false;
            if(filterType == null || filterType.equals("Marca y Modelo")) {
                match = vehicle.getMarca().toLowerCase().contains(searchText) || vehicle.getModelo().toLowerCase().contains(searchText.toLowerCase());
            }else if(filterType.equals("Precio")) {
                try{
                    int precio = Integer.parseInt(searchText);
                    match = vehicle.getPrecio() <= precio;
                }catch(NumberFormatException e) {
                    System.out.println("Error - numero sin formato");
                }
            }else if(filterType.equals("Kilometraje")) {
               try {
                    int millas = Integer.parseInt(searchText);
                    match = vehicle.getKm() <= millas;
                } catch (NumberFormatException e) {
                    System.out.println("Error - numero sin formato");
                } 
            }
            
            if(match) {
                filteredVehicles.add(vehicle);
            }
        }
        
        // Mostrar los filtrados
        System.out.println(filteredVehicles);
        showPublications(filteredVehicles);
    }
    
    private HBox crearBarraInferior() {
        HBox bottomBar = new HBox(0);
        bottomBar.setAlignment(Pos.CENTER);

        Button crearButton = new Button("Crear Vehículo");
        configurarBoton(crearButton);
        crearButton.setOnAction(event -> {
            App.showCrearVehiculo(this.vendedor);
        });
        
        
        Button editarButton = new Button("Editar Vehículo");
        configurarBoton(editarButton);
        editarButton.setOnAction(event -> {

            // Obtener los vehiculos del vendedor
            String query = "FROM Vehiculo GET .. WHEN idVendedor=\"" + this.vendedor.user.getIdUsuario() + "\"";
            Response resp = App.database.executeQuery(query);
            CircularLinkedList<Vehiculo> vehicles = new CircularLinkedList();
            for(HashMap<String, Object> d : resp.data()) {
            Vehiculo mb = new Vehiculo(
                (int) d.get("id"), 
                (String) d.get("marca"), 
                (String) d.get("modelo"), 
                (int) d.get("precio"), 
                (int) d.get("km"), 
                (String) d.get("fotos"), 
                (String) d.get("Descripcion"), 
                (String) d.get("idVendedor")
            );            vehicles.add(mb);
            }
            new GestionarVehiculosView(vehicles, true, this);
        });
            
        Button eliminarButton = new Button("Eliminar Vehículo");
        configurarBoton(eliminarButton);
        eliminarButton.setOnAction(event -> {

            // Obtener los vehiculos del vendedor
            String query = "FROM Vehiculo GET .. WHEN idVendedor=\"" + this.vendedor.user.getIdUsuario() + "\"";
            Response resp = App.database.executeQuery(query);
            CircularLinkedList<Vehiculo> vehicles = new CircularLinkedList();
            for(HashMap<String, Object> d : resp.data()) {
            Vehiculo mb = new Vehiculo(
                (int) d.get("id"), 
                (String) d.get("marca"), 
                (String) d.get("modelo"), 
                (int) d.get("precio"), 
                (int) d.get("km"), 
                (String) d.get("fotos"), 
                (String) d.get("Descripcion"), 
                (String) d.get("idVendedor")
            );            vehicles.add(mb);
            }
            new GestionarVehiculosView(vehicles, false, this);
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
