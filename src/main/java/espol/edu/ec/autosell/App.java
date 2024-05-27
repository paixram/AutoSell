package espol.edu.ec.autosell;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// import models
import espol.edu.ec.autosell.model.Usuario;

// Import views
import espol.edu.ec.autosell.view.LoginView;

// import controllers
import espol.edu.ec.autosell.controller.LoginController;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("primary"), 640, 480);
        mainStage = stage;
        ShowLogin();
        mainStage.setTitle("AutoSell - Login");
        mainStage.show();
    }
    
    public void ShowLogin() {
        // Crear la vista y el modelo para asociarlo con el controlador
        Usuario user_model = new Usuario();
        LoginView login_view = new LoginView();
        LoginController login_controller = new LoginController(user_model, login_view);
        
        scene = new Scene(login_view.getView(), 800, 600);
        
        mainStage.setScene(scene);
    } 

    public static void main(String[] args) {
        launch();
    }
    
}