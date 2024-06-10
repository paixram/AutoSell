/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.model.Vehiculo;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    public EditarVehiculoView(PrincipalView principalView, Vehiculo vehiculo) {
        super();
        this.principalView = principalView;
        this.vehiculo = vehiculo;
        view = new VBox(10);
        Label idLabel = new Label("ID:");
        idTextField = new TextField(vehiculo.getId());
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
        Label descripcionLabel = new Label("Descripción:");
        descripcionTextField = new TextField(vehiculo.getDescripcion());
        Button editarButton = new Button("Editar");
        configurarBoton(editarButton);
        editarButton.setOnAction(event -> editarVehiculo());

        view.getChildren().addAll(idLabel, idTextField, marcaLabel, marcaTextField, modeloLabel, modeloTextField, precioLabel, precioTextField, kmLabel, kmTextField, fotosLabel, fotosTextField, descripcionLabel, descripcionTextField, editarButton);
    }

    

    private void editarVehiculo() {
        String id = idTextField.getText();
        String marca = marcaTextField.getText();
        String modelo = modeloTextField.getText();
        int precio = Integer.parseInt(precioTextField.getText());
        int km = Integer.parseInt(kmTextField.getText());
        String fotos = fotosTextField.getText();
        String descripcion = descripcionTextField.getText();

        vehiculo.setId(id);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setPrecio(precio);
        vehiculo.setKm(km);
        vehiculo.setFotos(fotos);
        vehiculo.setDescripcion(descripcion);
        principalView.updateVehicleDetails();
        ((Stage) view.getScene().getWindow()).close();
    }
    private void configurarBoton(Button boton) {
        boton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 0; -fx-background-radius: 0; -fx-font-size: 20px; -fx-cursor: hand;");

    }
}

