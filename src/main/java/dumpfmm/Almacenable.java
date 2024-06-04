/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dumpfmm;

import espol.edu.ec.autosell.utils.Malloc;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Luizzz
 */
public interface Almacenable {
    default String getSID() {
        return this.getClass().getSimpleName();
    };
    default HashMap<String, String> getFields() {
        HashMap<String, String> fields = new LinkedHashMap();
      
        Field[] fld = this.getClass().getDeclaredFields();
        
        Arrays.sort(fld, Comparator.comparingInt(f -> {
            if (f.isAnnotationPresent(FieldOrder.class)) {
        FieldOrder order = f.getAnnotation(FieldOrder.class);
        return order.order();
      } else {
        // Handle fields without FieldOrder (optional)
        return Integer.MAX_VALUE; // Assuming higher order for fields without annotation
      }
        }));
        
        int index = 0;
        for(Field fl : fld) {
            fields.put(fl.getName(), fl.getType().getSimpleName() + "," + index);
            index++;
        }
        System.out.println("FIELDS: " + fields);
        return fields;
    };
}
