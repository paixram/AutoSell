package espol.edu.ec.autosell.utils;

import espol.edu.ec.autosell.model.Usuario;
import espol.edu.ec.autosell.model.Vehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
    public void addUser(Usuario usuario) {
        if (usuario != null) {
            elementos.add((E) usuario);
        }
    }
    public void removeUser(Usuario usuario) {
        elementos.remove(usuario);
    }
    public Usuario findUserById(String id) {
        for (E element : elementos) {
            if (element instanceof Usuario) {
                Usuario usuario = (Usuario) element;
                if (usuario.getIdUsuario().equals(id)) {
                    return usuario;
                }
            }
        }
        return null;
    }
    public List<Usuario> getAllUsers() {
        return elementos.stream().filter(e -> e instanceof Usuario).map(e -> (Usuario) e).collect(Collectors.toList());
    }

    public void updateUser(Usuario original, Usuario updated) {
        int index = elementos.indexOf(original);
        if (index != -1) {
            elementos.set(index, (E) updated);
        }
    }
    public static List<String[]> readDataFromFile(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] Ddata = line.split(",");
                data.add(Ddata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static List<Usuario> readUsersFromFile(String filePath){
        List<Usuario> usuarios= new ArrayList<>();
        List<String[]>data=Metodos.readDataFromFile(filePath);
        for(String[]Ddata : data){
            if(Ddata.length>=3){
            
            String idUsuario=Ddata[0];
            String contrasenia=Ddata[1];
            UserRole role = UserRole.valueOf(Ddata[2]); 
            Usuario usuario = new Usuario(idUsuario, contrasenia, role);
            usuarios.add(usuario);
        }
        
    }return usuarios;
    }
    public static void writeUsersToFile(String filePath, List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getIdUsuario() + "," + usuario.getContrasenia() + "," + usuario.getRole().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    

