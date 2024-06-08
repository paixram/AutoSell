package espol.edu.ec.autosell;

import dumpfmm.dumpfmm;
import espol.edu.ec.autosell.controller.CompradorController;
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
import espol.edu.ec.autosell.controller.VendedorController;
import espol.edu.ec.autosell.model.Comprador;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.model.Vendedor;
import espol.edu.ec.autosell.utils.Malloc;
import espol.edu.ec.autosell.view.CompradorView;
import espol.edu.ec.autosell.view.PrincipalView;
import espol.edu.ec.autosell.view.RegisterView;
import espol.edu.ec.autosell.view.VendedorView;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mainStage;
    private static final String filePath = "src/main/resources/file/archivo.txt";
    private static Malloc<LinkedHashMap<String,Object>> vehiculos;
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
        PrincipalView pv = new PrincipalView(false);
        PrincipalController pc = new PrincipalController(pv,vehiculos);
        
        scene = new Scene(pv.getView(), 800, 600);
        
        mainStage.setScene(scene);
    }
    
    public static void ShowVendedorView() {
        Vendedor vendedor = new Vendedor();
        VendedorView vv = new VendedorView();
        VendedorController vc = new VendedorController(vv, vendedor);
        
        scene = new Scene(vv.getView(), 800, 600);
        
        mainStage.setScene(scene);
        mainStage.setTitle("AutoSell - Vendedor");
    }
    
    public static void showCompradorView() {
        Comprador comprador = new Comprador();
        CompradorView cv = new CompradorView();
        CompradorController cc = new CompradorController(cv, comprador);
        
        scene = new Scene(cv.getView(), 800, 600);
        
        mainStage.setScene(scene);
        mainStage.setTitle("AutoSell - Vendedor");
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
        
        String my_query = database.From("Usuario").Get("rol").FindByFieldEQ("contrasenia", "lucho").toQuery();
       //String query_raw = "FROM Usuario GET rol,idUsuario WHEN contrasenia=\"lolilu324\"";
       String query_raw = "FROM Usuario GET ..";
       System.out.println("My Query: " + my_query);
       
       System.out.println(database.executeQuery(query_raw));
       //Querys
       //String query_raw2="FROM Vehiculo SET \"1\",\"Tesla\",\"Y\",43000,15000,\"src/main/resources/Images/tesla y 1.jpg\",\"El Tesla Model Y es un SUV eléctrico de tamaño mediano que ofrece un rendimiento impresionante y una gran autonomía. Tiene un motor de 351 CV y una batería de 75 kWh que le da una autonomía de hasta 507 km.\"";
       String query_raw2_jeep = "FROM Vehiculo SET \"1\", \"Jeep\", \"Avenger\", 37500, 10000, \"src/main/resources/Images/Jeep 1.jpeg\", \"El Jeep Avenger es un SUV eléctrico pequeño y elegante que ofrece una conducción divertida y un diseño atractivo. Tiene un motor de 156 CV y una batería de 50 kWh que le da una autonomía de hasta 392 km.\"";
       String query_raw2_tesla = "FROM Vehiculo SET \"2\", \"Tesla\", \"Model Y\", 43970, 15000, \"src/main/resources/Images/tesla y 1.jpg\", \"El Tesla Model Y es un SUV eléctrico de tamaño mediano que ofrece un rendimiento impresionante y una gran autonomía. Tiene un motor de 351 CV y una batería de 75 kWh que le da una autonomía de hasta 507 km.\"";
       String query_raw2_toyota_hilux = "FROM Vehiculo SET \"3\", \"Toyota\", \"Hilux\", 34000, 12000, \"src/main/resources/Images/Hilux1.jpg\", \"La Toyota Hilux es una camioneta pickup robusta y confiable que ofrece una gran capacidad de carga y un buen rendimiento todoterreno. Tiene un motor de 2.4 litros y 150 CV.\"";
       String query_raw2_ford_ranger = "FROM Vehiculo SET \"4\", \"Ford\", \"Ranger\", 32200, 20000, \"src/main/resources/Images/ford-ranger 1.jpg\", \"La Ford Ranger es una camioneta pickup moderna y versátil que ofrece una conducción cómoda y una amplia gama de tecnologías. Tiene un motor de 2.0 litros y 180 CV.\"";
       String query_raw2_mitsubishi_l200 = "FROM Vehiculo SET \"5\", \"Mitsubishi\", \"L200\", 29900, 25000, \"src/main/resources/Images/MItsubishi 1.jpg\", \"La Mitsubishi L200 es una camioneta pickup económica y duradera que ofrece una gran capacidad de remolque y un buen rendimiento todoterreno. Tiene un motor de 2.4 litros y 150 CV.\"";
       String query_raw2_nissan_navara = "FROM Vehiculo SET \"6\", \"Nissan\", \"Navara\", 31500, 8000, \"src/main/resources/Images/nissan navara 1.jpeg\", \"La Nissan Navara es una camioneta pickup tecnológica y cómoda que ofrece una conducción segura y una amplia gama de funciones. Tiene un motor de 2.3 litros y 190 CV.\"";
       String query_raw2_isuzu_dmax = "FROM Vehiculo SET \"7\", \"Isuzu\", \"D-Max\", 27500, 9500, \"src/main/resources/Images/isuzu 1.jpeg\", \"La Isuzu D-Max es una camioneta pickup robusta y confiable que ofrece una gran capacidad de carga y un buen rendimiento todoterreno. Tiene un motor de 1.9 litros y 164 CV.\"";
       String query_raw2_toyota_rav4 = "FROM Vehiculo SET \"8\", \"Toyota\", \"RAV4\", 35200, 17500, \"src/main/resources/Images/Toyota-Rav4 1.jpg\", \"El Toyota RAV4 es un SUV compacto popular que ofrece una conducción cómoda, un diseño atractivo y una amplia gama de funciones. Tiene un motor de 2.0 litros y 177 CV.\"";
       String query_raw2_hyundai_tucson = "FROM Vehiculo SET \"9\", \"Hyundai\", \"Tucson\", 30500, 20500, \"src/main/resources/Images/Hyundai 2.jpeg\", \"El Hyundai Tucson es un SUV compacto moderno y tecnológico que ofrece una conducción segura, un interior espacioso y una amplia gama de tecnologías. Tiene un motor de 1.6 litros y 150 CV.\"";
       String query_raw2_kia_sportage = "FROM Vehiculo SET \"10\", \"Kia\", \"Sportage\", 32700, 19800, \"src/main/resources/Images/kia sportage1.jpg\", \"El Kia Sportage es un SUV compacto elegante y deportivo que ofrece una conducción divertida, un diseño atractivo y una garantía de 7 años. Tiene un motor de 1.6 litros y 180 CV.\"";
       App.database.executeQuery(query_raw2_jeep);
       App.database.executeQuery(query_raw2_tesla);
       App.database.executeQuery(query_raw2_toyota_hilux);
       App.database.executeQuery(query_raw2_ford_ranger);
       App.database.executeQuery(query_raw2_mitsubishi_l200);
       App.database.executeQuery(query_raw2_nissan_navara);
       App.database.executeQuery(query_raw2_isuzu_dmax);
       App.database.executeQuery(query_raw2_toyota_rav4);
       App.database.executeQuery(query_raw2_hyundai_tucson);
       App.database.executeQuery(query_raw2_kia_sportage);
       
       String query_fotos= "FROM Vehiculo GET fotos";
       App.database.executeQuery(query_fotos);
       launch();
    }
    
}