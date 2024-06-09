/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.model.Vehiculo;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author José Miguel
 */

public class CrearVehiculoView extends LoginRegisterBaseView{
    private VBox view;
    private TextField idTextField;
    private TextField marcaTextField;
    private TextField modeloTextField;
    private TextField precioTextField;
    private TextField kmTextField;
    private TextField fotosTextField;
    private TextField descripcionTextField;
    private PrincipalView principalView;

    public CrearVehiculoView() {
        super();
        view = new VBox(10);
        view.setPadding(new Insets(50));
        Label idLabel = new Label("ID:");
        idTextField = new TextField();
        
        Label marcaLabel = new Label("Marca:");
        marcaTextField = new TextField();
        
        Label modeloLabel = new Label("Modelo:");
        modeloTextField = new TextField();
        
        Label precioLabel = new Label("Precio:");
        precioTextField = new TextField();
        
        Label kmLabel = new Label("Kilometraje:");
        kmTextField = new TextField();
        
        Label fotosLabel = new Label("Fotos:");
        fotosTextField = new TextField();
        
        
        Label descripcionLabel = new Label("Descripción:");
        descripcionTextField = new TextField();
        descripcionTextField.setPrefWidth(300);
        descripcionTextField.setPrefHeight(300);
        
        Button crearButton = new Button("Crear");
        configurarBoton(crearButton);
        crearButton.setOnAction(event -> crearVehiculo());
        
        view.getChildren().addAll(idLabel, idTextField, marcaLabel, marcaTextField, modeloLabel, modeloTextField, precioLabel, precioTextField, kmLabel, kmTextField, fotosLabel, fotosTextField, descripcionLabel, descripcionTextField, crearButton);
        
        super.base_view.getChildren().add(view);
    }

    

    private void crearVehiculo() {
        String id = idTextField.getText();
        String marca = marcaTextField.getText();
        String modelo = modeloTextField.getText();
        int precio = Integer.parseInt(precioTextField.getText());
        int km = Integer.parseInt(kmTextField.getText());
        String fotos = fotosTextField.getText();
        String descripcion = descripcionTextField.getText();

        Vehiculo nuevoVehiculo = new Vehiculo(id, marca, modelo, precio, km, fotos, descripcion);
        principalView.agregarVehiculo(nuevoVehiculo);
        ((Stage) view.getScene().getWindow()).close();
    }
    private void configurarBoton(Button boton) {
        boton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 0; -fx-background-radius: 0; -fx-font-size: 20px; -fx-cursor: hand;");

    }
}
