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
abstract class Query {
    private final String model;
    
    // Sets
    protected HashMap<Integer, HashMap<String, String>> model_format_data;
    protected Malloc<Malloc<String>> result_set;
    
    public Query(String model) {
        this.model = model;
    }
    
    public String getModel() {
        return model;
    }
    
    public static Malloc<LinkedHashMap<String, Object>> DumpFileModelDataFORMAT(String pathReference, HashMap<String, String> fields) {
        //Malloc<String> leer toda la data desde los archivos
        Malloc<String> raw_data = Engine.ReadRAWModelFile(pathReference);
        // TODO: Formatear los datos en un mapa de Hash
        // HashMap<Integer, Malloc<>>
        //int data_index = 0;
        //HashMap<Integer, Malloc<>>
        System.out.println("FIELFS: " + fields);
        
        Malloc<LinkedHashMap<String, Object>> formattedData = new Malloc<>();
        
        if(raw_data.isEmpty()) {
            return formattedData;
        }
        
        for(String data : raw_data) {
            System.out.println("DATA: " + data);
            formattedData.add(parseAndValidate(data, fields));
        }
        
        return formattedData;
    }
    
    protected Malloc<LinkedHashMap<String, Object>> FilterByConditions(Malloc<LinkedHashMap<String, Object>> rows, Malloc<Condition> conditions) {
        Malloc<LinkedHashMap<String, Object>> matchingRows = new Malloc();
        
        for(LinkedHashMap<String, Object> row : rows) {
            boolean matchesAll = true;
            for(Condition condition : conditions) {
                if(!condition.evaluate(row)) {
                    matchesAll = false;
                    System.out.println("Mo cmpel");
                    break;
                }
            }
            
            if(matchesAll) {
                matchingRows.add(row);
            }
        }
        
        return matchingRows;
    }
    
    protected static LinkedHashMap<String, Object> parseAndValidate(String data, HashMap<String, String> fieldMap) {
        LinkedHashMap<String, Object> row = new LinkedHashMap();
        String[] parts = data.split(",");
        fieldMap.forEach((K, V) -> {
            String fieldName = K;
            String[] typeAndIndex = V.split(",");
            String fieldType = typeAndIndex[0];
            int fieldIndex = Integer.parseInt(typeAndIndex[1]);
            System.out.println(fieldName + " -> " + fieldType + "," + fieldIndex);
            
            if(fieldIndex < parts.length) {
                String value = parts[fieldIndex];
                Object formattedValue = convertAndValidate(value, fieldType);
                if (formattedValue == null) {
                    return; // Datos inválidos, omitir la fila
                }
                row.put(fieldName, formattedValue);
            }
        });
        
        return row;
    }
    
    private static Object convertAndValidate(String value, String fieldType) {
        try {
            switch (fieldType) {
                default:
                case "String":
                    return value;
                case "int":
                    return Integer.parseInt(value);
                case "double":
                    return Double.parseDouble(value);
                
            }
        } catch (Exception e) {
            System.out.println("Error al convertir el valor: " + value + " al tipo: " + fieldType);
            return null;
        }
    }
    
    public abstract Response execute();

    @Override
    public String toString() {
        return "Query{" +
                "table='" + model + '\'' +
                '}';
    }
}
