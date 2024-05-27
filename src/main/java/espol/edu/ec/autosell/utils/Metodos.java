package espol.edu.ec.autosell.utils;

import espol.edu.ec.autosell.model.Vehiculo;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Metodos<E> {
    private List<E> elementos;

    public Metodos(List<E> elementos) {
        this.elementos = elementos;
    }
    
    public void createVehiculo(E v){
        elementos.add(v);
    }
    
    public void removeVehiculo(E v){
        elementos.remove(v);
        
    }
    
    public void editVehiculo(E original, E editado) {
        int index = elementos.indexOf(original);
        if (index != -1) {
            elementos.set(index, editado);
    }
}

    public List<E> getElementos() {
        return elementos;
    }
    
    public List<Vehiculo> findVehiculos(String marca, String modelo, Double precioMin, Double precioMax, Integer kmMin, Integer kmMax) {
        return elementos.stream().filter(e -> e instanceof Vehiculo).map(e -> (Vehiculo) e).filter(v -> (marca == null || v.getMarca().equalsIgnoreCase(marca)) && (modelo == null || v.getModelo().equalsIgnoreCase(modelo)) && (precioMin == null || v.getPrecio() >= precioMin) && (precioMax == null || v.getPrecio() <= precioMax) && (kmMin == null || v.getKm() >= kmMin) && (kmMax == null || v.getKm() <= kmMax)).collect(Collectors.toList());
    }
    
    public void ordenarVehiculos(Comparator<E> comparator) {
        elementos.sort(comparator);
        
        
    }
    
    
}
