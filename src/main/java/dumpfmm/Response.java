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
public class Response {
    
    public Malloc<LinkedHashMap<String, Object>> _DATA_;
    
    public Response(Malloc<LinkedHashMap<String, Object>> data_response) {
        this._DATA_ = data_response;
    }
    
    
    public boolean isEmptyResult() {
        if(this._DATA_.size() == 0) return true;
        return false;
    }
    
    public Malloc<LinkedHashMap<String, Object>> data() {
        return this._DATA_;
    }
    
    public LinkedHashMap<String, Object> getUnique() {
        if(!this.data().isEmpty()) {
            return this.data().get(0);
        }
        return null;
    }
}
