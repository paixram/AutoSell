package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.controller.PrincipalController;
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
import java.util.Comparator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
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
    public CircularLinkedList<Vehiculo> vehiculos;

    public void setStyle(String color) {
        root.setStyle(color);
    }
    
    // Items
    public Button anteriorButton = new Button();
    public Button siguienteButton = new Button();
    public Button crearButton = new Button();
    public Button editarButton = new Button();
    public Button eliminarButton = new Button();
    public Button prevButton = new Button();
    public Button nextButton = new Button();
    
    private ComboBox<String> filterComboBox = new ComboBox<>();
    private TextField marcaTextField = new TextField();
    private TextField modeloTextField = new TextField();
    private TextField minPrecioTextField = new TextField();
    private TextField maxPrecioTextField = new TextField();
    private TextField minKmTextField = new TextField();
    private TextField maxKmTextField = new TextField();
    private ImageView vehiculoImageView;
    
    protected VBox centerBox;
    protected Button detallesButton;
    // Publications widgets
    public ImageView imageView;
    public Label marcaModeloLabel;
    public Label kilometrajeLabel;
    public Label precioLabel;
    
    
    
    
    public PrincipalView(boolean reg) {
        //this.is_registered = reg;
        root = new BorderPane();
        this.is_registered = reg;
        showStackPane();
        vehiculos = PrincipalController.generatePublications();
        
        if(!this.is_registered) {
            ShowLoginButton();
        }
        
        showPublications();
        // Agregar la barra de búsqueda y el filtro
        HBox searchBarAndFilter = new HBox(10);
        searchBarAndFilter.setAlignment(Pos.CENTER_LEFT);
        searchBarAndFilter.setPadding(new Insets(10));
        
        // Pane for the padding
        
        HBox search_container = new HBox(5);
        TextField searchField = new TextField();
        searchField.setPromptText("Buscar...");
        
        searchField.setStyle("-fx-border-radius: 5; -fx-padding: 10; -fx-background-color: rgba(0, 0, 0, 0);");
        searchField.setFont(new Font("Arial", 15));
        
        ImageView search_icon = new ImageView();
        search_icon.setImage(new Image(getClass().getClassLoader().getResource("app_images/search_icon.png").toString()));
        search_icon.setFitHeight(25);
        search_icon.setFitWidth(25);
        searchField.textProperty().addListener((o, ov, nv) -> {
            this.filterVehicles(nv, null);
        });
        
        search_container.setAlignment(Pos.CENTER);
        search_container.setStyle("-fx-background-color: '#fff'; -fx-border-color: #6F6F6F; -fx-border-radius: 30");
        search_container.setAlignment(Pos.CENTER_LEFT);
        
        
        search_container.setPrefWidth(250);
        search_container.setAlignment(Pos.CENTER);
        search_container.getChildren().addAll(searchField, search_icon);
        searchBarAndFilter.getChildren().addAll(search_container);

        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Marca y Modelo", "Precio", "Kilometraje");
        filterComboBox.setPromptText("Seleccionar filtro...");
        filterComboBox.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10; -fx-border-color: grey;");

        //HBox.setHgrow(searchField, Priority.ALWAYS);
        
        searchBarAndFilter.getChildren().add(filterComboBox);
        
        //HBox.setHgrow(search_container, Priority.ALWAYS);
        //HBox.setHgrow(filterComboBox, Priority.ALWAYS);
        
        //super.root.getChildren().add(searchBarAndFilter);
        root.setTop(searchBarAndFilter);
        
        root.setOnMouseClicked(event -> {
            if (searchField.isFocused()) {
                root.requestFocus();
            }
        });
    }
    
    public void filterVehicles(String searchText, String filterType) {
        CircularLinkedList<Vehiculo> filteredVehicles = new CircularLinkedList();
        
        for(Vehiculo vehicle : this.vehiculos) {
            boolean match = false;
            if(filterType == null || filterType.equals("Marca - Modelo")) {
                match = vehicle.getMarca().toLowerCase().contains(searchText) || vehicle.getModelo().toLowerCase().contains(searchText.toLowerCase());
            }
            
            if(match) {
                filteredVehicles.add(vehicle);
            }
        }
        
        // Mostrar los filtrados
        System.out.println(filteredVehicles);
        showPublications(filteredVehicles);
    }
    
    
    public void showPublications() {
        this.showPublications(this.vehiculos);
    }
    
    public void showPublications(CircularLinkedList<Vehiculo> vehiculos) {
        prevButton.setOnAction(e -> scrollLeft(vehiculos));
        nextButton.setOnAction(e -> scrollRight(vehiculos));
        
        imageView = new ImageView();
        imageView.setFitWidth(300);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);
       
        
        marcaModeloLabel = new Label();
        marcaModeloLabel.setFont(new Font("Arial", 24));

        kilometrajeLabel = new Label();
        kilometrajeLabel.setFont(new Font("Arial", 18));

        precioLabel = new Label();
        precioLabel.setFont(new Font("Arial", 18));
        precioLabel.setStyle("-fx-text-fill: green;");

        detallesButton = new Button("Detalles");
        styleButton(detallesButton);
        detallesButton.setOnAction(e -> {
        Vehiculo currentVehiculo = vehiculos.getCurrent();
        if (currentVehiculo != null) {
            DetalleView detalleView = new DetalleView(currentVehiculo);
            detalleView.show();
        }
    });
    updateLabels(vehiculos);
        
        StackPane publication_card = new StackPane();
        publication_card.setPrefSize(350, 350);
        publication_card.setMaxWidth(350);
        publication_card.setMaxHeight(350);
        
        // Hbox for button items
        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.BASELINE_CENTER);
        bottomBox.getChildren().addAll(detallesButton, precioLabel);
        bottomBox.setSpacing(140);
        
        // Item box
        VBox items = new VBox();
        items.setSpacing(5);
        // Insertar los datos
        items.setAlignment(Pos.CENTER);
        items.getChildren().addAll(imageView, marcaModeloLabel, kilometrajeLabel, bottomBox);
        
        
        publication_card.setStyle(" -fx-border-width: 2; -fx-background-color: white; -fx-effect: dropshadow(three-pass-box, grey, 20, 0, 0, 0);");
        //publication_card.getChildren().addAll(imageView, marcaModeloLabel, kilometrajeLabel, precioLabel);
        publication_card.getChildren().addAll(items);
        
        centerBox = new VBox(10, publication_card);
        centerBox.setAlignment(Pos.CENTER);
        //centerBox.getChildren().add(publication_card);
        
        //centerBox = new VBox(10, imageView, marcaModeloLabel, kilometrajeLabel, precioLabel);
        //centerBox.setAlignment(Pos.CENTER);
        //centerBox.setPadding(new Insets(20));
        //centerBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #f0f0f0; -fx-border-radius: 10; -fx-background-radius: 10;");
    
        root.setCenter(centerBox);
    }
    

    

    protected void updateLabels(CircularLinkedList<Vehiculo> vehiculos) {
        Vehiculo vehiculo = vehiculos.getCurrent();

        if (vehiculo != null) {
            imageView.setImage(new Image(getClass().getClassLoader().getResource(vehiculo.getFotos()).toString()));
            marcaModeloLabel.setText(vehiculo.getMarca() + " - " + vehiculo.getModelo());
            kilometrajeLabel.setText("Km: " + vehiculo.getKm());
            precioLabel.setText("$" + vehiculo.getPrecio());
        }
    }
    
    private void scrollLeft(CircularLinkedList<Vehiculo> vehiculos) {
        Vehiculo prevPublicacion = vehiculos.prev();
        updateLabelsWithAnimation(prevPublicacion, 600);
    }

    private void scrollRight(CircularLinkedList<Vehiculo> vehiculos) {
        Vehiculo nextPublicacion = vehiculos.next();
        updateLabelsWithAnimation(nextPublicacion, -600);
    }
    
     private void updateLabelsWithAnimation(Vehiculo vehiculo, double offset) {
        StackPane publicationCard = (StackPane) centerBox.getChildren().get(0);

        TranslateTransition transitionOut = new TranslateTransition(Duration.millis(500), publicationCard);
        transitionOut.setToX(offset);
        transitionOut.setOnFinished(e -> {
            imageView.setImage(new Image(getClass().getClassLoader().getResource(vehiculo.getFotos()).toString()));
            marcaModeloLabel.setText(vehiculo.getMarca() + " - " + vehiculo.getModelo());
            kilometrajeLabel.setText("Km: " + vehiculo.getKm());
            precioLabel.setText("$" + vehiculo.getPrecio());
            publicationCard.setTranslateX(-offset);
            TranslateTransition transitionIn = new TranslateTransition(Duration.millis(500), publicationCard);
            transitionIn.setToX(0);
            transitionIn.play();
        });
        transitionOut.play();
    }

    
    private void ShowLoginButton() {
    //login_view.getLoginButton().setOnAction(e -> validateLogin());
        //login_view.getSignUpButton().setOnAction(e -> showRegister());
         //root = new BorderPane();
        
        // Contenedor para el botón de inicio de sesión a la derecha
        Button loginButton = new Button("Iniciar Sesión");
        configurarBoton(loginButton);
         
         
        loginButton.setOnAction(event -> {
            // Abrir nueva ventana con LoginView
            App.ShowLogin();
        });
        // HBOX
        HBox loginContainer = new HBox();
        loginContainer.setAlignment(Pos.TOP_CENTER);
        loginContainer.getChildren().add(loginButton);
        root.setBottom(loginContainer);
        
    }
    
    public void showStackPane() {
       
        Label titleLabel = new Label();
        Label contentLabel = new Label();
        
        prevButton = new Button("<");
        styleButton(prevButton);
        
        nextButton = new Button(">");
        styleButton(nextButton);
        
        
        

        
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
    
    
    
    
    
    private void configurarBoton(Button boton) {
        boton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: black; -fx-border-radius: 0; -fx-background-radius: 0; -fx-font-size: 20px; -fx-cursor: hand;");
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
    public void ordenarPorPrecio() {
        vehiculos.sort(Comparator.comparingInt(Vehiculo::getPrecio));
        updateVehicleDetails();
    }

    public void ordenarPorKilometraje() {
        vehiculos.sort(Comparator.comparingInt(Vehiculo::getKm));
        updateVehicleDetails();
    }
    public void setCenter(StackPane center) {
        root.setCenter(center);
    }
    public void eliminarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
    }
    
}

