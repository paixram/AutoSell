/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.utils.CircularLinkedList;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author José Miguel
 */
public class GestionarVehiculosView {
    private boolean editar;
    private Map<Vehiculo, VBox> vehiculoBoxMap = new HashMap<>();;
    

    public GestionarVehiculosView(CircularLinkedList<Vehiculo> vehiculos, boolean editar) {
        this.editar = editar;

        Stage stage = new Stage();
        stage.setTitle("Editar y Borrar View");
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        VBox vehiculosContainer = new VBox(10);
        vehiculosContainer.setAlignment(Pos.CENTER);

        if (vehiculos.size() > 0) {
            CircularLinkedList.Node<Vehiculo> current = vehiculos.getHead();
            do {
                Vehiculo vehiculo = current.element;

                VBox vehiculoBox = new VBox(5);
                vehiculoBox.setAlignment(Pos.CENTER);

                if (!vehiculo.getFotos().isEmpty()) {
               
                    ImageView imageView = new ImageView(new Image(getClass().getClassLoader().getResource(vehiculo.getFotos()).toString()));
                    imageView.setFitWidth(175); 
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    imageView.setCache(true);

                    HBox imageAndButtonBox = new HBox(10);
                    imageAndButtonBox.setAlignment(Pos.CENTER);

                    Button button = new Button(editar ? "Editar" : "Eliminar");
                    configurarBoton(button);
                    button.setOnAction(event -> {
                        if (editar) {
                            Stage editStage = new Stage();
                            EditarVehiculoView editarVehiculoView = new EditarVehiculoView(vehiculo);
                            Scene scene = new Scene(editarVehiculoView.getView());
                            editStage.setScene(scene);
                            editStage.show();
                        } else {
                            System.out.println("El vehiculazo: " + vehiculo);
                            //principalView.eliminarVehiculo(vehiculo);
                            //PrincipalView.vehiculos.remove(vehiculo);
                            //vehiculos.remove(vehiculo);
                            
                            //PrincipalView.updateLabels(vehiculos);
                            EliminarVehiculoView eliminar_v = new EliminarVehiculoView(vehiculo);
                            if(EliminarVehiculoView.is_delete) {
                                // Remover de base de datros y de la lista
                                vehiculosContainer.getChildren().remove(vehiculoBoxMap.get(vehiculo));
                                EliminarVehiculoView.is_delete = false;

                            }
                            // Crear una nueva ventana para mostrar EliminarVehiculoView
                            Stage eliminarStage = new Stage();
                            Scene eliminarScene = new Scene(eliminar_v.getView());
                            eliminarStage.setScene(eliminarScene);
                            eliminarStage.setTitle("Eliminar Vehículo");
                            eliminarStage.show();
                        }
                    });

                    imageAndButtonBox.getChildren().addAll(imageView, button);
                    vehiculoBox.getChildren().add(imageAndButtonBox);
                }

                String vehiculoInfo = "Marca: " + vehiculo.getMarca() + "\nModelo: " + vehiculo.getModelo() + "\nPrecio: $" + vehiculo.getPrecio() + "\nKilometraje: " + vehiculo.getKm() + " km";
                vehiculoBox.getChildren().add(new javafx.scene.control.Label(vehiculoInfo));

                vehiculosContainer.getChildren().add(vehiculoBox);
                
                vehiculoBoxMap.put(vehiculo, vehiculoBox);

                current = current.next;
            } while (current != vehiculos.getHead());
        }

        ScrollPane scrollPane = new ScrollPane(vehiculosContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 800, 650);
        stage.setScene(scene);
        stage.show();
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