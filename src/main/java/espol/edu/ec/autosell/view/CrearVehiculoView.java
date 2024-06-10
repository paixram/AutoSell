/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.model.Vehiculo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
    private File selectedFile;
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
        fotosTextField.setEditable(false);
        Button seleccionarFotoButton = new Button("Seleccionar Foto");
        configurarBoton(seleccionarFotoButton);
        seleccionarFotoButton.setOnAction(event -> seleccionarFoto());
        
        Label descripcionLabel = new Label("Descripción:");
        descripcionTextArea = new TextArea();
        descripcionTextArea.setPrefWidth(300);
        descripcionTextArea.setPrefHeight(400);
        
        Button crearButton = new Button("Crear");
        configurarBoton(crearButton);
        crearButton.setOnAction(event -> crearVehiculo());
        
        view.getChildren().addAll(idLabel, idTextField, marcaLabel, marcaTextField, modeloLabel, modeloTextField, precioLabel, precioTextField, kmLabel, kmTextField, fotosLabel, fotosTextField,seleccionarFotoButton, descripcionLabel, descripcionTextArea, crearButton);
        
        super.base_view.getChildren().add(view);
    }
    private void seleccionarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto");
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(view.getScene().getWindow());

        if (selectedFile != null) {
            fotosTextField.setText(selectedFile.getAbsolutePath());
        }
    }
    

   private void crearVehiculo() {
        String id = idTextField.getText();
        String marca = marcaTextField.getText();
        String modelo = modeloTextField.getText();
        int precio = Integer.parseInt(precioTextField.getText());
        int km = Integer.parseInt(kmTextField.getText());
        String descripcion = descripcionTextArea.getText();

        // Mover el archivo seleccionado a la carpeta src/main/resources/Images
        if (selectedFile != null) {
            try {
                Path destinoDir = Path.of("src/main/resources/Images");
                if (!Files.exists(destinoDir)) {
                    Files.createDirectories(destinoDir);
                }
                Path destinoPath = destinoDir.resolve(selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinoPath, StandardCopyOption.REPLACE_EXISTING);

                String fotos = destinoPath.toString();

                Vehiculo nuevoVehiculo = new Vehiculo(id, marca, modelo, precio, km, fotos, descripcion);
                principalView.agregarVehiculo(nuevoVehiculo);
                ((Stage) view.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar el error (mostrar una alerta o mensaje al usuario)
            }
        }
    }

    private void configurarBoton(Button button) {
        button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 0; -fx-background-radius: 0; -fx-font-size: 16px; -fx-cursor: hand;");
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
