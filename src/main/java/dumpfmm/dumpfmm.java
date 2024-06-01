/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dumpfmm;

// Cada modelo registrado tiene un archivo asociado a el con el nombre del archivo
// Cada model tendra que implementar la interface Almacenable para poder operar sobre ella
import espol.edu.ec.autosell.utils.Malloc;
import java.util.HashMap;


/**
 *
 * @author Luizzz
 */

public class dumpfmm extends Core {
    
    // Stablish data location and configs
    protected String _USER_HOME_DIRECTORY_ = System.getProperty("user.home");
    protected String _WORK_SPACE_ = _USER_HOME_DIRECTORY_ + "/.dumpfmm_cache";
    
    // Stablish Hashmap with models and metadata
    protected HashMap<String, HashMap<String, Object>> models_metadata = new HashMap();
    
    public dumpfmm() {
        // Si el workspace no existe entonces crear el workspace
        super.CheckForEnvironment(_WORK_SPACE_);
    }
    
    public dumpfmm(String work_space, boolean raw_work_space) {
        if(raw_work_space == false) {
            _WORK_SPACE_ = _USER_HOME_DIRECTORY_  + "/" + work_space;
        }else{
            _WORK_SPACE_ = work_space;
        }
        
        // Si el workspace no existe entonces crear el workspace
        super.CheckForEnvironment(_WORK_SPACE_);
    }
    
    /*
    - RegModel registra la metadata de x modelo para poder hacer uso en consultas etc en otras funciones independientes.
    Model{
        "SID": nombre del modelo,
        "Fields": campos del modelo, -> 0
        "Path Reference": Nombre del archivo donde se ubica el modelo con sus datos, -> 1
    }
    */
    public <E> boolean RegModel(Almacenable model) {
        // Crear y definir el archivo asociado al modelo
        String modelName = model.getSID();
        if(models_metadata.containsKey(modelName)) return false; // Si el modelo exsite entonces no volver a registrarlo
        
        // Si el modelo no esta registrado o no existe en el hashmap entonces cargarlos con su informacion
        HashMap<String, Object> metadata = new HashMap(); 
        
        HashMap<String, String> fields = model.getFields();   // Field name - Field Type
        String path_r = _WORK_SPACE_ + "/" + modelName + Core.getExtension();
        
        metadata.put("Fields", fields);
        metadata.put("Path Reference", path_r);
        
        // ( X ) TODO: Si el archivo modelo no existe en el workspace entonces crearlo (Terminado)
        super.CheckForModel(path_r);
        
        models_metadata.put(modelName, metadata);
        
        System.out.println(models_metadata);
        return true;
    }
    
    // TODO: Hacer las funciones basicas de recoleccion, insercion, eliminacion y actualizacion de datos en los archivos dfm
}
