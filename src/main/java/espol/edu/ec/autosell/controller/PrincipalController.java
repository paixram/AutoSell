/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.controller;

import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.view.PrincipalView;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Luizzz
 */
public class PrincipalController {
    private PrincipalView principal_view;
    
    private ListView<Vehiculo> listaVehiculos;
    
    public PrincipalController(PrincipalView pv) {
        principal_view = pv;
        initialize();
    }
    
    public void initialize() {
        principal_view.anteriorButton.setOnAction(event -> {
            int currentIndex = listaVehiculos.getSelectionModel().getSelectedIndex();
            if (currentIndex > 0) {
                listaVehiculos.getSelectionModel().select(currentIndex - 1);
            }
        });

        principal_view.siguienteButton.setOnAction(event -> {
            int currentIndex = listaVehiculos.getSelectionModel().getSelectedIndex();
            if (currentIndex < listaVehiculos.getItems().size() - 1) {
                listaVehiculos.getSelectionModel().select(currentIndex + 1);
            }
        });

        principal_view.crearButton.setOnAction(event -> {
            //abrirVentanaCrearVehiculo();
            //CrearVehiculoView crearVehiculoView = new CrearVehiculoView();
            //crearVehiculoView.mostrar();
        });

        principal_view.editarButton.setOnAction(event -> {
            Vehiculo vehiculoSeleccionado = listaVehiculos.getSelectionModel().getSelectedItem();
            if (vehiculoSeleccionado != null) {
                // Lógica para abrir la ventana de edición del vehículo seleccionado (por implementar)
                //abrirVentanaEditarVehiculo(vehiculoSeleccionado);
            } else {
                System.out.println("No se ha seleccionado ningún vehículo para editar."); // Mostrar un mensaje de que no se ha seleccionado ningún vehículo
            }
        });

        principal_view.eliminarButton.setOnAction(event -> {
            Vehiculo vehiculoSeleccionado = listaVehiculos.getSelectionModel().getSelectedItem();
            if (vehiculoSeleccionado != null) {
                // Lógica para eliminar el vehículo seleccionado (por implementar)
                //eliminarVehiculo(vehiculoSeleccionado);
            } else {
                System.out.println("No se ha seleccionado ningún vehículo para eliminar.");// Mostrar un mensaje de que no se ha seleccionado ningún vehículo
            }
        });
    }
    
    private void cargarVehiculos() {
        //List<Vehiculo> vehiculos = Metodos.cargarVehiculos("ruta.txt");
        //listaVehiculos.getItems().addAll(vehiculos);
    }
    
    
}
