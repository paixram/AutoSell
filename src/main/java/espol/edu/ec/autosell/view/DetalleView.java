/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.model.Vehiculo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author José Miguel
 */


public class DetalleView {

    private Stage stage;
    private Vehiculo vehiculo;
    private Label marcaModeloLabel;
    private Label kilometrajeLabel;
    private Label precioLabel;
    private Label descripcionLabel;
    private ImageView vehiculoImageView;

    public DetalleView(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        createWindowContent();
    }

    private void createWindowContent() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        vehiculoImageView = new ImageView();
        vehiculoImageView.setFitWidth(250);
        vehiculoImageView.setFitHeight(200);
        vehiculoImageView.setPreserveRatio(true);
        vehiculoImageView.setImage(new Image(getClass().getClassLoader().getResource(vehiculo.getFotos()).toString()));

        VBox detailsVBox = new VBox(10);
        detailsVBox.setAlignment(Pos.CENTER);

        marcaModeloLabel = new Label(vehiculo.getMarca() + " - " + vehiculo.getModelo());
        marcaModeloLabel.setFont(new javafx.scene.text.Font("Arial", 25));

        kilometrajeLabel = new Label("Km: " + vehiculo.getKm());
        precioLabel = new Label("$" + vehiculo.getPrecio());
        precioLabel.setStyle("-fx-text-fill: green;");

        descripcionLabel = new Label(vehiculo.getDescripcion());
        descripcionLabel.setWrapText(true);
        descripcionLabel.setPrefWidth(300);
        descripcionLabel.setTextAlignment(TextAlignment.JUSTIFY);

        detailsVBox.getChildren().addAll(marcaModeloLabel, kilometrajeLabel, precioLabel, descripcionLabel);

        // Botón de cerrar
        Button closeButton = new Button("Cerrar");
        styleButton(closeButton);
        closeButton.setOnAction(e -> stage.close());

        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().add(closeButton);

        VBox contentBox = new VBox(20);
        contentBox.getChildren().addAll(vehiculoImageView, detailsVBox);
        contentBox.setAlignment(Pos.CENTER);

        root.setCenter(contentBox);
        root.setBottom(bottomBox);

        // Configuración de la escena y el título de la ventana
        stage.setScene(new Scene(root, 600, 500));
        stage.setTitle("Detalles del Vehículo");
    }

    public void show() {
        stage.showAndWait();
    }
    private void styleButton(Button boton) {
        boton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +
            "-fx-border-radius: 10; " +
            "-fx-font-size: 15px; " +
            "-fx-padding: 5 10; " +
            "-fx-border-color: black; " +
            "-fx-border-width: 2px;" +
            "-fx-cursor: hand;"
        );
    }
}