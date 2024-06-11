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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @autor José Miguel
 */

public class EliminarVehiculoView extends LoginRegisterBaseView{
    private VBox view;
    private Vehiculo vehiculo;
    
    public static boolean is_delete = false;
    private Runnable  onDelete;

    public EliminarVehiculoView(Vehiculo vehiculo, Runnable onDelete) {
        super();
        this.vehiculo = vehiculo;
        this.onDelete = onDelete;
        view = new VBox(10);
        view.setPrefSize(650, 200); // Ajusta el tamaño preferido según tus necesidades
        view.setPadding(new Insets(20));
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
        PrincipalView.eliminarVehiculo(vehiculo);
        if (onDelete != null) {
            onDelete.run();
        }
        ((Stage) view.getScene().getWindow()).close();
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
