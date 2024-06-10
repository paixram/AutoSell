            /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.controller;

import dumpfmm.Response;
import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.model.Vehiculo;
import espol.edu.ec.autosell.utils.CircularLinkedList;
import espol.edu.ec.autosell.utils.Malloc;
import espol.edu.ec.autosell.view.PrincipalView;
import java.util.LinkedHashMap;
/**
 *
 * @author Luizzz
 */
public class PrincipalController {
    private PrincipalView principal_view;
    private int currentIndex = 0;
    private Malloc<LinkedHashMap<String,Object>> vehiculos;
    
    public PrincipalController(PrincipalView pv, Malloc<LinkedHashMap<String,Object>> vehiculos) {
        principal_view = pv;
        this.vehiculos = vehiculos;
        initialize();
    }
    
    public void obtenerVehiculo(){
        String query = "FROM Vehiculo GET ..";
        Response resp = App.database.executeQuery(query);
        this.vehiculos = resp.data();
         
    }
    
    public void initialize() {
        generatePublications();
    }
//    public void initialize() {
//        principal_view.getAnteriorButton().setOnAction(e -> showPreviousVehicle());
//        principal_view.getSiguienteButton().setOnAction(e -> showNextVehicle());
//        principal_view.getCrearButton().setOnAction(e -> createVehicle());
//        principal_view.getEditarButton().setOnAction(e -> editVehicle());
//        principal_view.getEliminarButton().setOnAction(e -> deleteVehicle());
//        updateView();
//    }
        /*principal_view.anteriorButton.setOnAction(event -> {
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
    }
    private void cargarVehiculos() {
        //List<Vehiculo> vehiculos = Metodos.cargarVehiculos("ruta.txt");
        //listaVehiculos.getItems().addAll(vehiculos);
    }*/
//    private void showPreviousVehicle() {
//        if (currentIndex > 0) {
//            currentIndex--;
//            updateView();
//        }
//    }
//
//    private void showNextVehicle() {
//        if (currentIndex < vehiculos.size() - 1) {
//            currentIndex++;
//            updateView();
//        }
//    }

//    private void updateView() {
//    if (!vehiculos.isEmpty() && currentIndex >= 0 && currentIndex < vehiculos.size()) {
//        Vehiculo currentVehicle = vehiculos.get(currentIndex);
//        principal_view.updateVehicleDetails((List<Vehiculo>) currentVehicle);
//    } else {
//        if (vehiculos.isEmpty()) {
//            System.out.println("No hay vehículos disponibles para mostrar.");
//        } else {
//            System.out.println("El índice está fuera de los límites de la lista de vehículos.");
//        }
//    }


    public void addVehicle(Vehiculo vehicule) {
        String query = "FROM Vehiculo SET" + vehicule.getId() + ",\"" +vehicule.getMarca() + ",\"" + vehicule.getModelo() + ",\"" + vehicule.getPrecio() + ",\"" + vehicule.getKm() + ",\"" + vehicule.getFotos();
        Response resp = App.database.executeQuery(query);
        this.vehiculos = resp.data();
    }
    
    public static CircularLinkedList<Vehiculo> generatePublications() {
        Malloc<Vehiculo> vehiculos = Vehiculo.getAll();
        //principal_view.
        CircularLinkedList<Vehiculo> ve = new CircularLinkedList<>();
        
        for(Vehiculo v : vehiculos) {
            ve.add(v);
        }
        
        return ve;
        
    }

//    public void editVehicle() {
//        Vehiculo vehiculoActual = vehiculos.get(currentIndex);
//        
//        updateView();
//    }
//
//    public void deleteVehicle() {
//        if (!vehiculos.isEmpty()) {
//            vehiculos.remove(currentIndex);
//            if (currentIndex >= vehiculos.size()) {
//                currentIndex = vehiculos.size() - 1;
//            }
//            updateView();
//        }
//    }
//    public void sortByPrice() {
//        vehiculos.sort(Comparator.comparingDouble(Vehiculo::getPrecio));
//        currentIndex = 0;
//        updateView();
//    }
//
//    public void sortByKilometraje() {
//        vehiculos.sort(Comparator.comparingDouble(Vehiculo::getKm));
//        currentIndex = 0;
//        updateView();
//    }
//     public void applySelectedFilter() {
//        String selectedFilter = principal_view.getSelectedFilter();
//        
//        switch (selectedFilter) {
//            case "Marca y Modelo":
//                filterByMarcaModelo();
//                break;
//            case "Precio":
//                filterByPrecio();
//                break;
//            case "Kilometraje":
//                filterByKilometraje();
//                break;
//            
//        }
//    }
//    private void filterByMarcaModelo() {
//        String marca = principal_view.getMarca();
//        String modelo = principal_view.getModelo();
//        
//        List<Vehiculo> filteredList = vehiculos.stream()
//            .filter(v -> v.getMarca().equalsIgnoreCase(marca) && v.getModelo().equalsIgnoreCase(modelo))
//            .collect(Collectors.toList());
//        
//        updateFilteredView(filteredList);
//    }
//    
//    private void filterByPrecio() {
//        double minPrecio = principal_view.getMinPrecio();
//        double maxPrecio = principal_view.getMaxPrecio();
//        
//        List<Vehiculo> filteredList = vehiculos.stream()
//            .filter(v -> v.getPrecio() >= minPrecio && v.getPrecio() <= maxPrecio)
//            .collect(Collectors.toList());
//        
//        updateFilteredView(filteredList);
//    }
//    
//    private void filterByKilometraje() {
//        double minKm = principal_view.getMinKilometraje();
//        double maxKm = principal_view.getMaxKilometraje();
//        
//        List<Vehiculo> filteredList = vehiculos.stream()
//            .filter(v -> v.getKm() >= minKm && v.getKm() <= maxKm)
//            .collect(Collectors.toList());
//        
//        updateFilteredView(filteredList);
//    } 
//     public void updateFilteredView(List<Vehiculo> filteredVehicles) {
//        
//        principal_view.updateVehicleDetails(filteredVehicles);
//    }
}
