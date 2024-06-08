package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.model.Usuario;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.utils.CircularLinkedList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


public class PrincipalView {
    
    // Class settings
    public boolean is_registered = false;
    
    private Label vehicleDetailsLabel;
    public BorderPane root;
    private CircularLinkedList<Vehiculo> vehiculos;
    
    // Items
    public Button anteriorButton = new Button();
    public Button siguienteButton = new Button();
    public Button crearButton = new Button();
    public Button editarButton = new Button();
    public Button eliminarButton = new Button();
    private ComboBox<String> filterComboBox = new ComboBox<>();
    private TextField marcaTextField = new TextField();
    private TextField modeloTextField = new TextField();
    private TextField minPrecioTextField = new TextField();
    private TextField maxPrecioTextField = new TextField();
    private TextField minKmTextField = new TextField();
    private TextField maxKmTextField = new TextField();
    private ImageView vehiculoImageView;
    
    
    public PrincipalView(boolean reg) {
        //this.is_registered = reg;
        root = new BorderPane();
        this.is_registered = reg;
        showStackPane();
        vehiculos = new CircularLinkedList<>();
        
        if(!this.is_registered) {
            ShowLoginButton();
        }
        
        
    }
    
    private void ShowLoginButton() {
    //login_view.getLoginButton().setOnAction(e -> validateLogin());
        //login_view.getSignUpButton().setOnAction(e -> showRegister());
         //root = new BorderPane();
        
        // Contenedor para el botón de inicio de sesión a la derecha
        Button loginButton = new Button("Iniciar Sesión");
        configurarBoton(loginButton);
         /*loginButton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +     // Bordes cuadrados
            "-fx-border-radius: 10; " +         // Bordes cuadrados
            "-fx-font-size: 15px; " +          // Tamaño de letra más grande
            "-fx-padding: 5 10; " +           // Tamaño del botón más grande
            "-fx-border-color: black; " +      // Color del borde
            "-fx-border-width: 2px;" +          // Ancho del borde
            "-fx-cursor: hand;"
        );*/
         
        loginButton.setOnAction(event -> {
            // Abrir nueva ventana con LoginView
            App.ShowLogin();
        });
        // HBOX
        HBox loginContainer = new HBox();
        loginContainer.setAlignment(Pos.TOP_RIGHT);
        loginContainer.getChildren().add(loginButton);
        root.setTop(loginContainer);
        
    }
    
    public void showStackPane() {
        
        /*ScrollPane scrollPane = new ScrollPane();
        VBox mainContent = new VBox();
        mainContent.setAlignment(Pos.TOP_CENTER);
        // Aquí puedes agregar contenido a mainContent
        Label titleLabel = new Label("AutoSell");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        mainContent.getChildren().add(titleLabel);
        vehicleDetailsLabel = new Label();
        mainContent.getChildren().add(vehicleDetailsLabel);
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);
        // Barra inferior con navegación y acciones
        HBox bottomBar = crearBarraInferior();
        root.setBottom(bottomBar);*/
        Label titleLabel = new Label();
        Label contentLabel = new Label();
        
        Button prevButton = new Button("<");
        styleButton(prevButton);

        Button nextButton = new Button(">");
        styleButton(nextButton);
        
        VBox centerBox = new VBox(10, titleLabel, contentLabel);
        centerBox.setAlignment(Pos.CENTER);

        root.setCenter(centerBox);
        root.setLeft(prevButton);
        root.setRight(nextButton);
        
        BorderPane.setAlignment(prevButton, Pos.CENTER_LEFT);
        BorderPane.setAlignment(nextButton, Pos.CENTER_RIGHT);
        
       StackPane leftStack = new StackPane(prevButton);
        StackPane rightStack = new StackPane(nextButton);
        leftStack.setStyle("-fx-background-color: transparent;");
        rightStack.setStyle("-fx-background-color: transparent;");
        leftStack.setAlignment(Pos.CENTER_LEFT);
        rightStack.setAlignment(Pos.CENTER_RIGHT);
        leftStack.setPadding(new Insets(0, 10, 0, 10));  // Margen para el botón izquierdo
        rightStack.setPadding(new Insets(0, 10, 0, 10)); // Margen para el botón derecho

        root.setLeft(leftStack);
        root.setRight(rightStack);
        
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
    
    
    
    private HBox crearBarraInferior() {
        HBox bottomBar = new HBox(10);
        bottomBar.setAlignment(Pos.CENTER);

        Button anteriorButton = new Button("Anterior");
        configurarBoton(anteriorButton);
        /*anteriorButton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +     // Bordes cuadrados
            "-fx-border-radius: 10; " +         // Bordes cuadrados
            "-fx-font-size: 15px; " +          // Tamaño de letra más grande
            "-fx-padding: 5 10; " +           // Tamaño del botón más grande
            "-fx-border-color: black; " +      // Color del borde
            "-fx-border-width: 2px;" +          // Ancho del borde
            "-fx-cursor: hand;"
        );*/
        Button siguienteButton = new Button("Siguiente");
        configurarBoton(siguienteButton);
       /* siguienteButton.setStyle(
            "-fx-background-color: black;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 5; " +     // Bordes cuadrados
            "-fx-border-radius: 10; " +         // Bordes cuadrados
            "-fx-font-size: 15px; " +          // Tamaño de letra más grande
            "-fx-padding: 5 10; " +           // Tamaño del botón más grande
            "-fx-border-color: black; " +      // Color del borde
            "-fx-border-width: 2px;" +          // Ancho del borde
            "-fx-cursor: hand;"
        );
        /* just for sellers /  solo para los vendedores
        Button crearButton = new Button("Crear Vehículo");
        Button editarButton = new Button("Editar Vehículo");
        Button eliminarButton = new Button("Eliminar Vehículo");
        */
        
        //anteriorButton.setOnAction(event -> AnteriorVehiculo());
        //siguienteButton.setOnAction(event -> SiguienteVehiculo());
        bottomBar.getChildren().addAll(anteriorButton, siguienteButton);
        return bottomBar;
    }
    
    private void configurarBoton(Button boton) {
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

    public Button getAnteriorButton() {
        return anteriorButton;
    }

    public Button getSiguienteButton() {
        return siguienteButton;
    }

    public Button getCrearButton() {
        return crearButton;
    }

    public Button getEditarButton() {
        return editarButton;
    }

    public Button getEliminarButton() {
        return eliminarButton;
    }
    
    public BorderPane getView() {
        return root;
    }
    public void updateVehicleDetails() {
        Vehiculo vehiculoActual = vehiculos.getCurrent();
        if (vehiculoActual != null) {
            String detalles = "Marca: " + vehiculoActual.getMarca() + "\n" + "Modelo: " + vehiculoActual.getModelo() + "\n" +"Precio: $" + vehiculoActual.getPrecio() + "\n" + "Kilometraje: " + vehiculoActual.getKm() + " km";
            vehicleDetailsLabel.setText(detalles);
        } else {
            vehicleDetailsLabel.setText("No hay vehículos disponibles.");
        }
    }
    //private void AnteriorVehiculo(){
      //  vehiculos.getPrevious();
        //updateVehicleDetails();
    //}
    //}
     //private void SiguienteVehiculo() {
        //vehiculos.getNext();
        //updateVehicleDetails();
    //}
    public String getSelectedFilter() {
    // Suponiendo que tienes un ComboBox llamado filterComboBox
        return filterComboBox.getValue(); // Retorna el valor seleccionado del ComboBox
}

    public String getMarca() {
        // Suponiendo que tienes un TextField llamado marcaTextField
        return marcaTextField.getText(); // Retorna el texto ingresado en el TextField de la marca
    }

    public String getModelo() {
        // Suponiendo que tienes un TextField llamado modeloTextField
        return modeloTextField.getText(); // Retorna el texto ingresado en el TextField del modelo
    }

    public double getMinPrecio() {
        // Suponiendo que tienes un TextField llamado minPrecioTextField para el precio mínimo
        try {
            return Double.parseDouble(minPrecioTextField.getText()); // Retorna el valor parseado del TextField de precio mínimo
        } catch (NumberFormatException e) {
            return 0.0; // Retorna 0.0 si no se puede parsear como un número válido
        }
    }

    public double getMaxPrecio() {
        // Suponiendo que tienes un TextField llamado maxPrecioTextField para el precio máximo
        try {
            return Double.parseDouble(maxPrecioTextField.getText()); // Retorna el valor parseado del TextField de precio máximo
        } catch (NumberFormatException e) {
            return 0.0; // Retorna 0.0 si no se puede parsear como un número válido
        }
    }

    public double getMinKilometraje() {
        // Suponiendo que tienes un TextField llamado minKmTextField para el kilometraje mínimo
        try {
            return Double.parseDouble(minKmTextField.getText()); // Retorna el valor parseado del TextField de kilometraje mínimo
        } catch (NumberFormatException e) {
            return 0.0; // Retorna 0.0 si no se puede parsear como un número válido
        }
    }

    public double getMaxKilometraje() {
        // Suponiendo que tienes un TextField llamado maxKmTextField para el kilometraje máximo
        try {
            return Double.parseDouble(maxKmTextField.getText()); // Retorna el valor parseado del TextField de kilometraje máximo
        } catch (NumberFormatException e) {
            return 0.0; // Retorna 0.0 si no se puede parsear como un número válido
        }
    }
    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
        if (vehiculos.size() == 1) {
            updateVehicleDetails();
        }
    }
    
    
}

