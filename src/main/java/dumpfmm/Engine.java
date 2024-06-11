/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// dumpfmm = dump file model manager

package dumpfmm;

import espol.edu.ec.autosell.utils.Malloc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
    public static Response RunQuery(String query, LinkedHashMap<String, LinkedHashMap<String, Object>> md) {
        // TODO: Se lee la query y se interpreta
        
        Lexer query_lexer = new Lexer(query); // Lee la query y la tokeniza
        Malloc<Token> tokens = query_lexer.tokenizer();
        
        for(Token s : tokens) {
            System.out.println(s);
        }
        Parser pa = new Parser(tokens, md);
        Query que = pa.parse();
        Response results = que.execute();
        return results;
    }
    
    public static boolean WriteRAWModelFile(String path_model, String data_to_write) {
        try {
           BufferedWriter bw = new BufferedWriter(new FileWriter(path_model, true));
           
           bw.write(data_to_write + "\n");
           
           bw.close();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Open Reader File Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Open while close Reader File Error: " + e);
        }
        
        return false;
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
    
    public static void clearModel(String path_model) {
        try (FileWriter fileWriter = new FileWriter(path_model)) {
            // Al abrir el archivo en modo escritura sin el modo append, se borra el contenido
            System.out.println("El contenido del archivo ha sido borrado.");
            
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error al borrar el contenido del archivo: " + e.getMessage());
        }
    }
    
    public static void overwriteModel(String pathReference, Malloc<LinkedHashMap<String, Object>> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathReference))) {
            for (LinkedHashMap<String, Object> row : data) {
                StringBuilder line = new StringBuilder();
                for (Object value : row.values()) {
                    if (line.length() > 0) {
                        line.append(",");
                    }
                    line.append(value.toString());
                }
                writer.write(line.toString());
                writer.newLine();
            }
            
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    // getters and setters
    public static String getExtension() {
        return _EXTENSION_;
    }
}
