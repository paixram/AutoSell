/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dumpfmm;

import espol.edu.ec.autosell.utils.Malloc;
import java.util.HashMap;

/**
 *
 * @author Luizzz
 */
public class GetQuery extends Query {

    private HashMap<String, String> columns;
    private Malloc<Condition> conditions;
    private String pathReference;
    private HashMap<String, String> fields;
    
    
    
    public GetQuery(String model) {
        super(model);
    }
    
    public GetQuery(String model, HashMap<String, String> columns, Malloc<Condition> conditions, String pathReference, HashMap<String, String> fields) {
        super(model);
        this.columns = columns;
        this.conditions = conditions;
        this.pathReference = pathReference; 
        this.fields = fields;
    }
    
    
    // Operar bajo estas condiciones y sacar los campos requeridos en columns
    public Malloc<HashMap<String, Object>> Run() {
        Malloc<HashMap<String, Object>> data = super.DumpFileModelDataFORMAT(this.pathReference, fields);
        System.out.println("Formatted data: ");
        for(HashMap<String, Object> s : data) {
            System.out.println("As:" + s);
        }
        
        if(this.conditions.size() >= 1) {
            Malloc<HashMap<String, Object>> filter_data = super.FilterByConditions(data, this.conditions);
            
            System.out.println("Los que cumplenm con el docfiog: ");
            for(HashMap<String, Object> objeto : filter_data ) {
                System.out.println("Objecto: " + objeto);
            }
            
            // Devolver solo los campos que estan en columns
            Malloc<HashMap<String, Object>> result = filterFields(filter_data, this.columns);
            System.out.println("Resultados con campos especificados: ");
            for (HashMap<String, Object> obj : result) {
                System.out.println("Objeto con campos especificados: " + obj);
            }
            
            return result;
        }
        
        return data;
    }
    
    private Malloc<HashMap<String, Object>> filterFields(Malloc<HashMap<String, Object>> data, HashMap<String, String> columns) {
        Malloc<HashMap<String, Object>> result = new Malloc<>();

        for (HashMap<String, Object> row : data) {
            HashMap<String, Object> filteredRow = new HashMap<>();
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
    public Malloc<HashMap<String, Object>> execute() {
        return this.Run();
    }
}
