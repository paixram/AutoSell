/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dumpfmm;

import espol.edu.ec.autosell.utils.Malloc;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Luizzz
 */
public class DeleteQuery extends Query {

    private Malloc<Condition> conditions;
    private String pathReference;
    private LinkedHashMap<String, String> fields;
    
    public DeleteQuery(String model) {
        super(model);
    }
    
    public DeleteQuery(String model, Malloc<Condition> conditions, String pathReference, LinkedHashMap<String, String> fields) {
        super(model);
        this.conditions = conditions;
        this.pathReference = pathReference; 
        this.fields = fields;
    }
    
    public Response Run() {
        // Si condiciones esta vacio entonces borrar todo
        if(this.conditions.isEmpty()) {
            // Campos Condition Delete
            Engine.clearModel(pathReference);
            return null;
        }
        
        // Obtener toda la data de los vehiculos
        Malloc<LinkedHashMap<String, Object>> data = super.DumpFileModelDataFORMAT(this.pathReference, fields);
        System.out.println("Formatted data: ");
        for(HashMap<String, Object> s : data) {
            System.out.println("As:" + s);
        }
        
        Malloc<LinkedHashMap<String, Object>> filter_data = new Malloc();
        if(this.conditions.size() > 0) {
            filter_data = super.FilterByConditions(data, this.conditions);
            
            System.out.println("Los que cumplenm con el docfiog: ");
            for(HashMap<String, Object> objeto : filter_data ) {
                System.out.println("Objecto: " + objeto);
            }
        }
        
        // Eliminar datos en comun.
        data.removeAll(filter_data);
        
        Engine.overwriteModel(pathReference, data);
        
        return null;
    }
    
    @Override
    public Response execute() {
        return this.Run();
    }
    
}
