
import dumpfmm.dumpfmm;
import espol.edu.ec.autosell.model.Usuario;
import espol.edu.ec.autosell.model.Vehiculo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luizzz
 */
public class dumpfmmTest {
    @Test
     public void getQuery() {
        dumpfmm mydb = new dumpfmm();
        
        // Models
        Usuario user_model = new Usuario();
        Vehiculo vehiculo = new Vehiculo();
        // Register models
        mydb.RegModel(user_model);
        mydb.RegModel(vehiculo);
       
       //System.out.println( System.getProperty("user.home"));
       // Consults by find
       String my_query = mydb.From("Usuario").Get("rol").FindByFieldEQ("contrasenia", "lucho").toQuery();
       //String query_raw = "FROM Usuario GET rol,idUsuario WHEN contrasenia=\"lolilu324\"";

       String query_raw = "FROM Vehiculo GET idVendedor";
       System.out.println("My Query: " + my_query);
       
       System.out.println("Data: " + (mydb.executeQuery(query_raw).data()).toString());

    }
     
     @Test
     public void setQuery() {
        dumpfmm mydb = new dumpfmm();
        
        // Models
        Usuario user_model = new Usuario();
        
        // Register models
        mydb.RegModel(user_model);
       
       //System.out.println( System.getProperty("user.home"));
       // Consults by find
       String my_query = mydb.From("Usuario").Set("\"1\",\"lucho\",V").toQuery();
       String query_raw = "FROM Usuario SET AUTOINCREMENT,\"Luiz\",\"Qwbda\", vv";
       
       System.out.println("My Query: " + my_query);
       
       mydb.executeQuery(query_raw);
    }
    @Test
     public void setVehiculos() {
        dumpfmm mydb = new dumpfmm();
        
        // Models
        Usuario user_model = new Usuario();
        
        // Register models
        mydb.RegModel(user_model);
       
       //System.out.println( System.getProperty("user.home"));
       // Consults by find
       String my_query = mydb.From("Usuario").Set("\"1\",\"lucho\",V").toQuery();
       String query_raw = "FROM Usuario SET AUTOINCREMENT,\"Juan\",\"Elluan\", C";
       
       System.out.println("My Query: " + my_query);
       
       mydb.executeQuery(query_raw);
    }
     
    
    @Test
     public void deleteVehiculos() {
        dumpfmm mydb = new dumpfmm();
        
        // Models
        Usuario user_model = new Usuario();
        Vehiculo vehiculo = new Vehiculo();
        // Register models
        mydb.RegModel(user_model);
        mydb.RegModel(vehiculo);
       
       //System.out.println( System.getProperty("user.home"));
       // Delete by fields
       String query_raw = "FROM Vehiculo DELETE ..";
       
       System.out.println("My Query: " + query_raw);
       
       mydb.executeQuery(query_raw);
    }
     
     @Test
     public void UpdateVehiculo() {
        dumpfmm mydb = new dumpfmm();
        
        // Models
        Usuario user_model = new Usuario();
        Vehiculo vehiculo = new Vehiculo();
        // Register models
        mydb.RegModel(user_model);
        mydb.RegModel(vehiculo);
       
       //System.out.println( System.getProperty("user.home"));
       // Delete by fields
       String query_raw = "FROM Vehiculo UPDATE " +
        "id = " + 12 +
        ", marca = '" + "elpepe" + "'" +
        ", modelo = '" + "modular" + "'" +
        ", precio = " + 23324 +
        ", km = " + 43435 +
        ", fotos = '" + "Images/ellcaha.png" + "'" +
        ", descripcion = '" + "actualziado con exito" + "'" +
        " WHERE id = " + 11;
       
       System.out.println("My Query: " + query_raw);
       
       mydb.executeQuery(query_raw);
    }
}
