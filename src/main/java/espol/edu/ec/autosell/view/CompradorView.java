/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.view;

import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.utils.CircularLinkedList;
import espol.edu.ec.autosell.utils.Malloc;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author José Miguel
 */
public class CompradorView extends PrincipalView{

    
    private CircularLinkedList<Vehiculo> publicaciones;
    
    public CompradorView() {
        super(true);
        initialize();
        //stage = new Stage();
        
    }

    private void initialize() {
        // Cargar publicaciones para comprador
        super.showPublications();
        
    }
   
}