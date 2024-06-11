/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;
import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.model.Usuario;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.model.Vendedor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    public Vendedor vendedor;
    private final ImageView imageView;
    public CrearVehiculoView(Vendedor v) {
        
        super();
        this.vendedor = v;
        view = new VBox(10);
        view.setPadding(new Insets(50));
        
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
        
        // mostrar foto
        imageView = new ImageView();
        imageView.setFitHeight(60);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        
        Label descripcionLabel = new Label("Descripción:");
        descripcionTextArea = new TextArea();
        descripcionTextArea.setPrefWidth(300);
        descripcionTextArea.setPrefHeight(400);
        
        Button crearButton = new Button("Crear");
        configurarBoton(crearButton);
        crearButton.setOnAction(event -> crearVehiculo());
        
        view.getChildren().addAll( marcaLabel, marcaTextField, modeloLabel, modeloTextField, precioLabel, precioTextField, kmLabel, kmTextField, fotosLabel, fotosTextField,seleccionarFotoButton, imageView, descripcionLabel, descripcionTextArea, crearButton);
        
        super.base_view.getChildren().add(view);
    }
    
    private void seleccionarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto");
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg"));
        selectedFile = fileChooser.showOpenDialog(view.getScene().getWindow());

        if (selectedFile != null) {
            fotosTextField.setText(selectedFile.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            
            /*try {
                this.guardarImagen(selectedFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }*/
            
            
        }
    }
    
    private String guardarImagen(File imagen) throws IOException {
        // Define la ruta del directorio donde se guardarán las imágenes
        String destinationDir = getClass().getClassLoader().getResource("Images/").getPath();
        System.out.println("EL PATH: " + destinationDir);
        File destDir = new File(destinationDir);

        // Crea el directorio si no existe
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        // Copia el archivo seleccionado al directorio destino
        //System.out.println("RONWEjhgII: " + Paths.get(destinationDir + imagen.getName()));
        Files.copy(selectedFile.toPath(), Paths.get(destDir.getAbsolutePath(), imagen.getName()).toAbsolutePath());
        
        
        // copiarla tambien en resources
        String desttDir = "src/main/resources/Images/";
        File destFileDir = new File(desttDir);

        // Crea el directorio si no existe
        if (!destFileDir.exists()) {
            destFileDir.mkdirs();
        }

        // Copia el archivo seleccionado al directorio destino
        Files.copy(selectedFile.toPath(), Paths.get(desttDir + imagen.getName()).toAbsolutePath());
        
        return "Images/" + selectedFile.getName();
    }
    

   private void crearVehiculo() {
        String marca = marcaTextField.getText();
        String modelo = modeloTextField.getText();
        int precio = Integer.parseInt(precioTextField.getText());
        int km = Integer.parseInt(kmTextField.getText());
        String descripcion = descripcionTextArea.getText();

        // Mover el archivo seleccionado a la carpeta src/main/resources/Images
        if (selectedFile != null) {
            try {
                String fotos = guardarImagen(selectedFile);
                
                String query = "FROM Vehiculo SET AUTOINCREMENT,\"" + marca + "\"," + "\"" + modelo + "\"," + precio + "," + km + ",\"" + fotos + "\"," + "\"" + descripcion + "\"," + "\"" + vendedor.user.getIdUsuario() + "\"";
                App.database.executeQuery(query);
                   System.out.println("HOLAAAA");
                Vehiculo v = PrincipalView.vehiculos.getLast();
                Vehiculo nuevoVehiculo = new Vehiculo(v.getId()+1, marca, modelo, precio, km, fotos, descripcion, String.valueOf(vendedor.user.getIdUsuario()));
                //PrincipalView.add_vehicle(nuevoVehiculo);
                //((Stage) view.getScene().getWindow()).close();
                
                App.ShowVendedorView(vendedor.user);
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
