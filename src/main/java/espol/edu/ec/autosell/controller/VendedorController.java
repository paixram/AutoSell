/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.controller;

import espol.edu.ec.autosell.model.Vendedor;
import espol.edu.ec.autosell.view.VendedorView;

/**
 *
 * @author Luizzz
 */
public class VendedorController {
    
    // Set
    private VendedorView vv;
    private Vendedor v;
    
    public VendedorController(VendedorView vv, Vendedor v) {
        this.vv = vv;
        this.v = v;
    }
}
