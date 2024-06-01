/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// dumpfmm = dump file model manager

package dumpfmm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Main primitives starts with _ and ends with _
*/

/**
 *
 * @author Luizzz
 */
public class Core {
    // Set Engine Configs
    private static char _SEPARATOR_ = ',';
    private static String _EXTENSION_ = ".dfm"; // DFM = Dump File Model, extension estandar para el proyecto.
    
    public Core() {
        
    }
    
    public Core(String ext) {
        _EXTENSION_ = ext;
    }
    
    public Core(char separator) {
        _SEPARATOR_ = separator;
    }
    
    public Core(String ext, char separator) {
        _EXTENSION_ = ext;
        _SEPARATOR_ = separator;
    }
    
    protected void CheckForEnvironment(String workspace) {
        // Si no existe el work space entonces crearlo
        //File db_enviroment = new File(workspace);
        Path workspace_path = Paths.get(workspace);
        System.out.println(workspace_path);
        
        if(Files.notExists(workspace_path)) {
            try{
                Files.createDirectories(workspace_path);
            }catch(IOException e) {
                System.out.println("[ - CheckForEnvironment() ] Dumpfmm Intern Engine Error: " + e.getMessage());
            }
        }
    }
    
    protected void CheckForModel(String model_route) {  // Archivos con extension dfm = Dump File Model
        // Verificar si existe el archivo modelo
        Path model_file = Paths.get(model_route);
        
        if(!Files.exists(model_file)) {
            try {
                Files.createFile(model_file);
                System.out.println("creado");
            }catch(IOException e) {
                System.out.println("[ - CheckForModel() ] Dumpfmm Intern Engine Error: " + e.getMessage());
            }
        }
    }
    
    // getters and setters
    public static String getExtension() {
        return _EXTENSION_;
    }
}
