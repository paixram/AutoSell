
import dumpfmm.dumpfmm;
import espol.edu.ec.autosell.model.Usuario;
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
        
        // Register models
        mydb.RegModel(user_model);
       
       //System.out.println( System.getProperty("user.home"));
       // Consults by find
       String my_query = mydb.From("Usuario").Get("rol").FindByFieldEQ("contrasenia", "lucho").toQuery();
       //String query_raw = "FROM Usuario GET rol,idUsuario WHEN contrasenia=\"lolilu324\"";
       String query_raw = "FROM Usuario GET ..";
       System.out.println("My Query: " + my_query);
       
       mydb.executeQuery(query_raw);
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
       String query_raw = "FROM Usuario SET 2,\"Juan\",\"Elluan\", C";
       
       System.out.println("My Query: " + my_query);
       
       mydb.executeQuery(query_raw);
    }
}
