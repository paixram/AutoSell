package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.controller.PrincipalController;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.utils.CircularLinkedList;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.Comparator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class PrincipalView {
    
    // Class settings
    public boolean is_registered = false;
    
    private Label vehicleDetailsLabel;
    public BorderPane root;
    public static CircularLinkedList<Vehiculo> vehiculos;
    private final ComboBox<String> orderComboBox;

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
    public Button comprar = null;
    
    public ComboBox<String> filterComboBox = new ComboBox<>();
    private TextField marcaTextField = new TextField();
    private TextField modeloTextField = new TextField();
    private TextField minPrecioTextField = new TextField();
    private TextField maxPrecioTextField = new TextField();
    private TextField minKmTextField = new TextField();
    private TextField maxKmTextField = new TextField();
    private ImageView vehiculoImageView;
    public TextField searchField;
    
    protected HBox bottomBox;
    protected VBox centerBox;
    protected Button detallesButton;
    // Publications widgets
    public ImageView imageView;
    public Label marcaModeloLabel;
    public Label kilometrajeLabel;
    public Label precioLabel;
    
    
    
    public String filterType = null;
    
    public PrincipalView(boolean reg) {
        //this.is_registered = reg;
        root = new BorderPane();
        this.is_registered = reg;
        showStackPane();
        vehiculos = PrincipalController.generatePublications();
        vehicleDetailsLabel = new Label();
        if(!this.is_registered) {
            ShowLoginButton();
            showPublications();
        }
        

        // Agregar la barra de búsqueda y el filtro
        HBox searchBarAndFilter = new HBox(10);
        searchBarAndFilter.setStyle("-fx-background-color: white");
        searchBarAndFilter.setAlignment(Pos.CENTER_LEFT);
        searchBarAndFilter.setPadding(new Insets(10));
        
        // Pane for the padding
        
        HBox search_container = new HBox(5);
        searchField = new TextField();
        searchField.setPromptText("Buscar...");
        
        searchField.setStyle("-fx-border-radius: 5; -fx-padding: 10; -fx-background-color: rgba(0, 0, 0, 0);");
        searchField.setFont(new Font("Arial", 15));
        searchField.textProperty().addListener((o, ov, nv) -> {
            this.filterVehicles(nv, this.filterType);
        });
        
        
        ImageView search_icon = new ImageView();
        search_icon.setImage(new Image(getClass().getClassLoader().getResource("app_images/search_icon.png").toString()));
        search_icon.setFitHeight(25);
        search_icon.setFitWidth(25);
        
        
        search_container.setAlignment(Pos.CENTER);
        search_container.setStyle("-fx-background-color: '#fff'; -fx-border-color: #6F6F6F; -fx-border-radius: 30");
        search_container.setAlignment(Pos.CENTER_LEFT);
        
        
        search_container.setPrefWidth(250);
        search_container.setAlignment(Pos.CENTER);
        search_container.getChildren().addAll(searchField, search_icon);
        searchBarAndFilter.getChildren().addAll(search_container);

        filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Marca y Modelo", "Precio", "Kilometraje");
        filterComboBox.setPromptText("Seleccionar filtro...");
        filterComboBox.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10; -fx-border-color: grey;");
        filterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            //filterVehicles(searchField.getText(), filterComboBox.getValue());
            this.filterType = filterComboBox.getValue();
            this.filterVehicles(searchField.getText(), this.filterType);
        });
        
        orderComboBox = new ComboBox<>();
        orderComboBox.getItems().addAll( "Precio", "Kilometraje");
        orderComboBox.setStyle("-fx-background-radius: 5; -fx-border-radius: 5; -fx-padding: 10; -fx-border-color: grey;");
        orderComboBox.setPromptText("Ordenar Por");
        orderComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            //filterVehicles(searchField.getText(), filterComboBox.getValue())
            this.orderVehicles(orderComboBox.getValue());
        });
        searchBarAndFilter.getChildren().addAll(filterComboBox, orderComboBox);
                
        root.setTop(searchBarAndFilter);
        
        root.setOnMouseClicked(event -> {
            if (searchField.isFocused()) {
                root.requestFocus();
            }
        });
    }
    
    public void orderVehicles(String criterio) {
        if(criterio.equals("Precio")) {
            this.ordenarPorPrecio();
        }else if(criterio.equals("Kilometraje")) {
            this.ordenarPorKilometraje();
        }
        
        vehiculos.resetIndex();
        updateLabels(vehiculos);
        
    }
    
    public void filterVehicles(String searchText, String filterType) {
        
        if(searchText.equals("")) {
            showPublications(vehiculos);
            return;
        }
        CircularLinkedList<Vehiculo> filteredVehicles = new CircularLinkedList();
        System.out.println("Searchtext: " + searchText + "Type: " + filterType);
        searchText = searchText.toLowerCase();
        for(Vehiculo vehicle : this.vehiculos) {
            boolean match = false;
            if(filterType == null || filterType.equals("Marca y Modelo")) {
                match = vehicle.getMarca().toLowerCase().contains(searchText) || vehicle.getModelo().toLowerCase().contains(searchText.toLowerCase());
            }else if(filterType.equals("Precio")) {
                try{
                    int precio = Integer.parseInt(searchText);
                    System.out.println("Se tranformo");
                    match = vehicle.getPrecio() <= precio;
                }catch(NumberFormatException e) {
                    System.out.println("Error - numero sin formato");
                }
            }else if(filterType.equals("Kilometraje")) {
               try {
                    int millas = Integer.parseInt(searchText);
                    match = vehicle.getKm() <= millas;
                } catch (NumberFormatException e) {
                    System.out.println("Error - numero sin formato");
                } 
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
        bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.BASELINE_CENTER);
        if(comprar == null) {
            bottomBox.getChildren().addAll(detallesButton, precioLabel);
        }else{
            bottomBox.getChildren().addAll(detallesButton, comprar, precioLabel);
        }
        
        bottomBox.setSpacing(25);
        
        // Item box
        VBox items = new VBox();
        items.setSpacing(5);
        // Insertar los datos
        items.setAlignment(Pos.CENTER);
        items.getChildren().addAll(imageView, marcaModeloLabel, kilometrajeLabel, bottomBox);
        
        
        publication_card.setStyle(" -fx-border-width: 2; -fx-background-color: white; -fx-effect: dropshadow(three-pass-box, grey, 20, 0, 0, 0);");
        
        publication_card.getChildren().addAll(items);
        
        centerBox = new VBox(10, publication_card);
        centerBox.setAlignment(Pos.CENTER);
       
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
        System.out.println("Vehiculinho: " + prevPublicacion.toString());
        updateLabelsWithAnimation(prevPublicacion, 600);
    }

    private void scrollRight(CircularLinkedList<Vehiculo> vehiculos) {
        Vehiculo nextPublicacion = vehiculos.next();
        updateLabelsWithAnimation(nextPublicacion, -600);
    }
    
     private void updateLabelsWithAnimation(Vehiculo vehiculo, double offset) {
         if(vehiculo == null) {
             return;
         }
         
        StackPane publicationCard = (StackPane) centerBox.getChildren().get(0);

        TranslateTransition transitionOut = new TranslateTransition(Duration.millis(500), publicationCard);
        transitionOut.setToX(offset);
        transitionOut.setOnFinished(e -> {
            System.out.print("EL VEHICULOO: " + vehiculo.getFotos());
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
    
        // Contenedor para el botón de inicio de sesión a la derecha
        Button loginButton = new Button("Iniciar Sesión");
        styleButton(loginButton);
         
         
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
    
    public void updateVehicles(Vehiculo vehiculo) {
        Vehiculo vehiculoActual = vehiculos.getCurrent();
        vehiculoActual.setId(vehiculo.getId());
        vehiculoActual.setMarca(vehiculo.getMarca());
        vehiculoActual.setModelo(vehiculo.getModelo());
        vehiculoActual.setPrecio(vehiculo.getPrecio());
        vehiculoActual.setKm(vehiculo.getKm());
        vehiculoActual.setFotos(vehiculo.getFotos());
        vehiculoActual.setDescripcion(vehiculo.getDescripcion());
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
    
    public static void add_vehicle(Vehiculo v) {
        vehiculos.add(v);
    }
    
    public String getSelectedFilter() {
    // Suponiendo que tienes un ComboBox llamado filterComboBox
        return filterComboBox.getValue(); // Retorna el valor seleccionado del ComboBox
}

    public String getMarca() {
        // Suponiendo que tienes un TextField llamado marcaTextField
        return marcaTextField.getText(); // Retorna el texto ingresado en el TextField de la marca
    }

    public String getModelo() {
        
        return modeloTextField.getText(); 
    }

    public double getMinPrecio() {
        
        try {
            return Double.parseDouble(minPrecioTextField.getText()); 
        } catch (NumberFormatException e) {
            return 0.0; 
        }
    }

    public double getMaxPrecio() {
        
        try {
            return Double.parseDouble(maxPrecioTextField.getText()); 
        } catch (NumberFormatException e) {
            return 0.0; 
        }
    }

    public double getMinKilometraje() {
        
        try {
            return Double.parseDouble(minKmTextField.getText()); 
        } catch (NumberFormatException e) {
            return 0.0; 
        }
    }

    public double getMaxKilometraje() {
        
        try {
            return Double.parseDouble(maxKmTextField.getText()); 
        } catch (NumberFormatException e) {
            return 0.0; 
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
    public static void eliminarVehiculo(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
        
    }
    
    public void refreshPublications() {
        vehiculos.resetIndex();  // Restablece el índice de la lista circular
        this.showPublications(vehiculos);  // Muestra nuevamente las publicaciones
    }
    
}

