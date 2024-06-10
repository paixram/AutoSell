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
public class GetQuery extends Query {

    private LinkedHashMap<String, String> columns;
    private Malloc<Condition> conditions;
    private String pathReference;
    private LinkedHashMap<String, String> fields;
    
    
    
    public GetQuery(String model) {
        super(model);
    }
    
    public GetQuery(String model, LinkedHashMap<String, String> columns, Malloc<Condition> conditions, String pathReference, LinkedHashMap<String, String> fields) {
        super(model);
        this.columns = columns;
        this.conditions = conditions;
        this.pathReference = pathReference; 
        this.fields = fields;
    }
    
    
    // Operar bajo estas condiciones y sacar los campos requeridos en columns
    public Response Run() {
        Malloc<LinkedHashMap<String, Object>> data = super.DumpFileModelDataFORMAT(this.pathReference, fields);
        System.out.println("Formatted data: ");
        for(HashMap<String, Object> s : data) {
            System.out.println("As:" + s);
        }
        
        Malloc<LinkedHashMap<String, Object>> filter_data = new Malloc();
        if(this.conditions.size() >= 1) {
            filter_data = super.FilterByConditions(data, this.conditions);
            
            System.out.println("Los que cumplenm con el docfiog: ");
            for(HashMap<String, Object> objeto : filter_data ) {
                System.out.println("Objecto: " + objeto);
            }
            
            
        }
        
        if(filter_data.size() > 0) {
            // Devolver solo los campos que estan en columns
            Malloc<LinkedHashMap<String, Object>> result = filterFields(filter_data, this.columns);
            System.out.println("Resultados con campos especificados: ");
            for (HashMap<String, Object> obj : result) {
                System.out.println("Objeto con campos especificados: " + obj);
            }
            
            return new Response(result);
        }
        
        // Devolver solo los campos que estan en columns
        Malloc<LinkedHashMap<String, Object>> result = filterFields(data, this.columns);
        System.out.println("Resultados con campos especificados: ");
        for (HashMap<String, Object> obj : result) {
            System.out.println("Objeto con campos especificados: " + obj);
        }
        
        return new Response(result);
    }
    
    private Malloc<LinkedHashMap<String, Object>> filterFields(Malloc<LinkedHashMap<String, Object>> data, LinkedHashMap<String, String> columns) {
        Malloc<LinkedHashMap<String, Object>> result = new Malloc<>();
        System.out.println("Integro: " + data);
        for (HashMap<String, Object> row : data) {
            LinkedHashMap<String, Object> filteredRow = new LinkedHashMap<>();
            for (String column : columns.keySet()) {
                if (row.containsKey(column)) {
                    filteredRow.put(column, row.get(column));
                }
            }
            result.add(filteredRow);
        }

        return result;
    }

    @Override
    public Response execute() {
        return this.Run();
    }
}
