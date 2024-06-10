/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.model.Vehiculo;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private TextArea descripcionTextArea;
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
        descripcionTextArea = new TextArea();
        descripcionTextArea.setPrefWidth(300);
        descripcionTextArea.setPrefHeight(400);
        
        Button crearButton = new Button("Crear");
        configurarBoton(crearButton);
        crearButton.setOnAction(event -> crearVehiculo());
        
        view.getChildren().addAll(idLabel, idTextField, marcaLabel, marcaTextField, modeloLabel, modeloTextField, precioLabel, precioTextField, kmLabel, kmTextField, fotosLabel, fotosTextField, descripcionLabel, descripcionTextArea, crearButton);
        
        super.base_view.getChildren().add(view);
    }

    

    private void crearVehiculo() {
        String id = idTextField.getText();
        String marca = marcaTextField.getText();
        String modelo = modeloTextField.getText();
        int precio = Integer.parseInt(precioTextField.getText());
        int km = Integer.parseInt(kmTextField.getText());
        String fotos = fotosTextField.getText();
        String descripcion = descripcionTextArea.getText();

        Vehiculo nuevoVehiculo = new Vehiculo(id, marca, modelo, precio, km, fotos, descripcion);
        principalView.agregarVehiculo(nuevoVehiculo);
        ((Stage) view.getScene().getWindow()).close();
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
