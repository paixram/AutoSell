/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.controller;

import espol.edu.ec.autosell.model.Comprador;
import espol.edu.ec.autosell.view.CompradorView;

/**
 *
 * @author Luizzz
 */
public class CompradorController {
    
    // Set
    public CompradorView cv;
    public Comprador comprador;
    
    public CompradorController(CompradorView cv, Comprador comprador) {
        this.cv = cv;
        this.comprador = comprador;
    }
    
}
