package espol.edu.ec.autosell;

import dumpfmm.dumpfmm;
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
import espol.edu.ec.autosell.controller.PrincipalController;
import espol.edu.ec.autosell.controller.RegisterController;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.view.PrincipalView;
import espol.edu.ec.autosell.view.RegisterView;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mainStage;
    private static final String filePath = "src/main/resources/file/archivo.txt";
    
    // Database Settings
    public static dumpfmm database;

    @Override
    public void start(Stage stage) {
        //PrincipalView principalView = new PrincipalView(true); // Asumimos que el usuario es vendedor
        //principalView.mostrar();
        mainStage = stage;
        showPrincipalView();
        mainStage.setTitle("AutoSell - Página Principal");
        mainStage.show();
        
    }
    
    public void showPrincipalView() {
        PrincipalView pv = new PrincipalView();
        PrincipalController pc = new PrincipalController(pv);
        
        scene = new Scene(pv.getView(), 800, 600);
        
        mainStage.setScene(scene);
    }
    //public void start(Stage stage) throws IOException {
       
      //  mainStage = stage;
       // ShowLogin();
       // mainStage.setTitle("AutoSell - Login");
       // mainStage.show();
    //}
    
    public static void ShowLogin() {
        
        Usuario user_model = new Usuario();
        LoginView login_view = new LoginView();
        LoginController login_controller = new LoginController(user_model, login_view,mainStage);
        
        scene = new Scene(login_view.getView(), 800, 600);
        
        mainStage.setScene(scene);
        mainStage.setTitle("AutoSell - Inicio de sesion");
    } 
    
    public static void ShowRegister() {
        RegisterView register_view = new RegisterView();
        Usuario user_model = new Usuario();
        RegisterController register_controller = new RegisterController(register_view);
        
        scene = new Scene(register_view.getView(), 800, 600);
        
        mainStage.setScene(scene);
        mainStage.setTitle("AutoSell - Registro de Usuario");
    }
    
    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    public static void main(String[] args) {
        
        // Database settings
        database = new dumpfmm();
        // Definir modelos
        Usuario user_model = new Usuario();
        Vehiculo vehiculo_model = new Vehiculo();
        
        // Registrar modelos
        database.RegModel(user_model);
        database.RegModel(vehiculo_model);
        
        launch();
    }
    
}