
import dumpfmm.DumpFileModelEngine;
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
    public void testAddAll() {
        dumpfmm mydb = new dumpfmm();
        
        // Models
        Usuario user_model = new Usuario();
        
        // Register models
        mydb.RegModel(user_model);
       
       //System.out.println( System.getProperty("user.home"));
        
    }
}
