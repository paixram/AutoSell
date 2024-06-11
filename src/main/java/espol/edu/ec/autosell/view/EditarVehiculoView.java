/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.model.Vehiculo;
import java.io.File;
import javafx.animation.ScaleTransition;
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

public class EditarVehiculoView extends LoginRegisterBaseView{
    private VBox view;
    private TextField idTextField;
    private TextField marcaTextField;
    private TextField modeloTextField;
    private TextField precioTextField;
    private TextField kmTextField;
    private TextField fotosTextField;
    private TextField descripcionTextField;
    private PrincipalView principalView;
    private Vehiculo vehiculo;
    private TextArea descripcionTextArea;
    public EditarVehiculoView(Vehiculo vehiculo, PrincipalView principalView) {
        super();
        this.vehiculo = vehiculo;
        view = new VBox(10);
        view.setMinWidth(400);
        view.setMinHeight(550);
        Label idLabel = new Label("ID:");
        idTextField = new TextField(String.valueOf(vehiculo.getId()));
        Label marcaLabel = new Label("Marca:");
        marcaTextField = new TextField(vehiculo.getMarca());
        Label modeloLabel = new Label("Modelo:");
        modeloTextField = new TextField(vehiculo.getModelo());
        Label precioLabel = new Label("Precio:");
        precioTextField = new TextField(String.valueOf(vehiculo.getPrecio()));
        Label kmLabel = new Label("Kilometraje:");
        kmTextField = new TextField(String.valueOf(vehiculo.getKm()));
        Label fotosLabel = new Label("Fotos:");
        fotosTextField = new TextField(vehiculo.getFotos());
        Button seleccionarFotoButton = new Button("Seleccionar Foto");
        configurarBoton(seleccionarFotoButton);
        seleccionarFotoButton.setOnAction(event -> seleccionarFoto());
        Label descripcionLabel = new Label("Descripción:");
        descripcionTextArea = new TextArea(vehiculo.getDescripcion());
        descripcionTextArea.setPrefColumnCount(15);
        descripcionTextArea.setWrapText(true);
             
        Button editarButton = new Button("Editar");
        configurarBoton(editarButton);
        editarButton.setOnAction(event -> editarVehiculo());

        view.getChildren().addAll(idLabel, idTextField, marcaLabel, marcaTextField, modeloLabel, modeloTextField, precioLabel, precioTextField, kmLabel, kmTextField, fotosLabel, fotosTextField,seleccionarFotoButton, descripcionLabel, descripcionTextArea, editarButton);
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
    

    private void editarVehiculo() {
        String id = idTextField.getText();
        String marca = marcaTextField.getText();
        String modelo = modeloTextField.getText();
        int precio = Integer.parseInt(precioTextField.getText());
        int km = Integer.parseInt(kmTextField.getText());
        String fotos = fotosTextField.getText();
        String descripcion = descripcionTextArea.getText();

        vehiculo.setId(Integer.parseInt(id));
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setPrecio(precio);
        vehiculo.setKm(km);
        vehiculo.setFotos(fotos);
        vehiculo.setDescripcion(descripcion);
        principalView.updateVehicleDetails();
        
        String query = "FROM Vehiculo UPDATE " +
        "id = " + id +
        ", marca = '" + marca + "'" +
        ", modelo = '" + modelo + "'" +
        ", precio = " + precio +
        ", km = " + km +
        ", fotos = '" + fotos + "'" +
        ", descripcion = '" + descripcion + "'" +
        " WHERE id = " + id;
        
        
        ((Stage) view.getScene().getWindow()).close();
    }
     private void configurarBoton(Button button) {
        button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 0; -fx-background-radius: 0; -fx-font-size: 14px; -fx-cursor: hand;");
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