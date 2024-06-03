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
public class SetQuery extends Query {
    
    private Malloc<String> values;
    private String pathReference;
    private HashMap<String, String> fields;
    
    public SetQuery(String model) {
        super(model);
    }
    
    public SetQuery(String model, Malloc<String> values, String pathReference, HashMap<String, String> fields) {
        super(model);
        this.values = values;
        this.pathReference = pathReference;
        this.fields = fields;
    }
    
    public void Run() {
        // Coger lo ya existente y volver a incluirlo junto a la data nueva
        Malloc<HashMap<String, Object>> data = super.DumpFileModelDataFORMAT(this.pathReference, fields);
        //Query.parseAndValidate(, fields)
        // Juntar la data
        String data_outformatted = String.join(",", this.values);
        HashMap<String, Object> my_set_data = Query.parseAndValidate(data_outformatted, fields);
        System.out.println("[ + ] Data Writed: " + my_set_data);
        
        Engine.WriteRAWModelFile(pathReference, data_outformatted);
    }

    @Override
    public Malloc<HashMap<String, Object>> execute() {
        this.Run();
        return null;
    }
    
}
