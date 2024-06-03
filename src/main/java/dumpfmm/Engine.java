/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// dumpfmm = dump file model manager

package dumpfmm;

import espol.edu.ec.autosell.utils.Malloc;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/*
Main primitives starts with _ and ends with _
*/

/**
 *
 * @author Luizzz
 */
public class Engine {
    // Set Engine Configs
    private static char _SEPARATOR_ = ',';
    private static String _EXTENSION_ = ".dfm"; // DFM = Dump File Model, extension estandar para el proyecto.
    
    // lexer cofigs
    private static int position = 0;
    
    // Query settings
    protected String __QUERY__ = "";
    
    public Engine() {
        
    }
    
    public Engine(String ext) {
        _EXTENSION_ = ext;
    }
    
    public Engine(char separator) {
        _SEPARATOR_ = separator;
    }
    
    public Engine(String ext, char separator) {
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
    
    // TODO: Hacer los metodos de lectura del query
    public static void RunQuery(String query, HashMap<String, HashMap<String, Object>> md) {
        // TODO: Se lee la query y se interpreta
        
        Lexer query_lexer = new Lexer(query); // Lee la query y la tokeniza
        Malloc<Token> tokens = query_lexer.tokenizer();
        
        for(Token s : tokens) {
            System.out.println(s);
        }
        Parser pa = new Parser(tokens, md);
        Query que = pa.parse();
        que.execute();
        
        // Lee los tokens y arma el arbol sintactico
    }
    
    public static Malloc<String> ReadRAWModelFile(String path_model) {
        Malloc<String> __DATA__ = new Malloc();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(path_model));
            
            String line;
            while((line = bf.readLine()) != null) {
                __DATA__.add(line);
            }
            
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Open Reader File Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Open while close Reader File Error: " + e);
        }
        return __DATA__;
    }
    
    
    // getters and setters
    public static String getExtension() {
        return _EXTENSION_;
    }
}
