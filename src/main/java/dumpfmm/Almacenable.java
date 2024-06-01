/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dumpfmm;

import espol.edu.ec.autosell.utils.Malloc;
import java.lang.reflect.Field;
import java.util.HashMap;

/**
 *
 * @author Luizzz
 */
public interface Almacenable {
    default String getSID() {
        return this.getClass().getSimpleName();
    };
    default HashMap<String, String> getFields() {
        HashMap<String, String> fields = new HashMap();
      
        Field[] fld = this.getClass().getDeclaredFields();

        for(Field fl : fld) {
            fields.put(fl.getName(), fl.getType().getSimpleName());
        }

        return fields;
    };
}
