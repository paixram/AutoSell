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
import espol.edu.ec.autosell.utils.Metodos;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mainStage;
    private static final String filePath = "src/main/sources/file/archivo.txt";

    @Override
    public void start(Stage stage) throws IOException {
       
        mainStage = stage;
        ShowLogin();
        mainStage.setTitle("AutoSell - Login");
        mainStage.show();
    }
    
    public void ShowLogin() {
        
        
        List<String[]> data = Metodos.readDataFromFile(filePath);
        Usuario user_model = new Usuario();
        LoginView login_view = new LoginView();
        LoginController login_controller = new LoginController(user_model, login_view);
        
        scene = new Scene(login_view.getView(), 800, 600);
        
        mainStage.setScene(scene);
    } 
    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    public static void main(String[] args) {
        launch();
    }
    
}