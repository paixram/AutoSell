
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
       String my_query = mydb.From("Usuario").Get("rol").FindByFieldEQ("contrasenia", "Waza").toQuery();
       
       System.out.println("My Query: " + my_query);
       
       mydb.executeQuery(my_query);
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
       String my_query = mydb.From("Usuario").Set("\"1,\"lucho\",V").toQuery();
       
       System.out.println("My Query: " + my_query);
       
       mydb.executeQuery(my_query);
    }
}
