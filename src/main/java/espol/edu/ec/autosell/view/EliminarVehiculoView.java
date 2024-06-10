/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.model.Vehiculo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @autor José Miguel
 */

public class EliminarVehiculoView extends LoginRegisterBaseView{
    private VBox view;
    private PrincipalView principalView;
    private Vehiculo vehiculo;

    public EliminarVehiculoView(PrincipalView principalView, Vehiculo vehiculo) {
        super();
        this.principalView = principalView;
        this.vehiculo = vehiculo;
        view = new VBox(10);

        Label confirmLabel = new Label("¿Estás seguro de que deseas eliminar este vehículo?");
        Label vehiculoLabel = new Label(vehiculo.toString());
        Button eliminarButton = new Button("Eliminar");
        configurarBoton(eliminarButton);
        eliminarButton.setOnAction(event -> eliminarVehiculo());

        Button cancelarButton = new Button("Cancelar");
        configurarBoton(cancelarButton);
        cancelarButton.setOnAction(event -> ((Stage) view.getScene().getWindow()).close());

        view.getChildren().addAll(confirmLabel, vehiculoLabel, eliminarButton, cancelarButton);
        super.base_view.getChildren().add(view);
    }

    private void eliminarVehiculo() {
        principalView.eliminarVehiculo(vehiculo);
        ((Stage) view.getScene().getWindow()).close();
    }

   private void configurarBoton(Button boton) {
        boton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 0; -fx-background-radius: 0; -fx-font-size: 20px; -fx-cursor: hand;");

    }
}
