/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.utils.CircularLinkedList;
import espol.edu.ec.autosell.utils.Malloc;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author José Miguel
 */
public class CompradorView extends PrincipalView {

    public CompradorView() {
        super(true);
        
    }

    
    @Override
    public void showPublications() {
        super.showPublications();

        // Crear y configurar el botón "Comprar"
        Button comprarButton = new Button("Comprar");
        styleButton(comprarButton);
        comprarButton.setOnAction(e -> {
            Vehiculo currentVehiculo = vehiculos.getCurrent();
            if (currentVehiculo != null) {
                // Lógica para comprar el vehículo
                System.out.println("Vehículo comprado: " + currentVehiculo.getMarca() + " " + currentVehiculo.getModelo());
            }
        });

        // Añadir el botón "Comprar" al contenedor adecuado
        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.BASELINE_CENTER);
        bottomBox.getChildren().addAll(detallesButton, comprarButton, precioLabel);
        bottomBox.setSpacing(25);

        VBox items = new VBox();
        items.setSpacing(5);
        items.setAlignment(Pos.CENTER);
        items.getChildren().addAll(imageView, marcaModeloLabel, kilometrajeLabel, bottomBox);

        StackPane publicationCard = new StackPane();
        publicationCard.setPrefSize(350, 350);
        publicationCard.setMaxWidth(350);
        publicationCard.setMaxHeight(350);
        publicationCard.setStyle(" -fx-border-width: 2; -fx-background-color: white; -fx-effect: dropshadow(three-pass-box, grey, 20, 0, 0, 0);");
        publicationCard.getChildren().addAll(items);

        centerBox = new VBox(10, publicationCard);
        centerBox.setAlignment(Pos.CENTER);

        root.setCenter(centerBox);
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