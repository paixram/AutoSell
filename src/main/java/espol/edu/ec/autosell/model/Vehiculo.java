/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.model;

import dumpfmm.Almacenable;
import dumpfmm.FieldOrder;
import espol.edu.ec.autosell.App;
import espol.edu.ec.autosell.utils.Malloc;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author José Miguel
 */
public class Vehiculo implements Almacenable {
    
    @FieldOrder(order = 1)
    private String id;
    
    @FieldOrder(order = 2)
    private String marca;
    
    @FieldOrder(order = 3)
    private String modelo;
    
    @FieldOrder(order = 4)
    private int precio;
    
    @FieldOrder(order = 5)
    private int km;
    
    @FieldOrder(order = 6)
    private String fotos;
    
    @FieldOrder(order = 7)
    String Descripcion;

    public Vehiculo(String id, String marca, String modelo, int precio, int km, String fotos, String Descripcion) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.km = km;
        this.fotos = fotos;
        this.Descripcion = Descripcion;
    }

   
    
    public Vehiculo() {
    
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio + ", kilometraje=" + km + ", fotos=" + fotos + '}';
    }

    public String getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getPrecio() {
        return precio;
    }

    public int getKm() {
        return km;
    }

    public String getFotos() {
        return fotos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getDescripcion() {
        return Descripcion;
    }
    
    public static Malloc<Vehiculo> getAll() {
        // Obtener en la query
        String query = "FROM Vehiculo GET ..";
        Malloc<LinkedHashMap<String, Object>> data = App.database.executeQuery(query).data();
        
        Malloc<Vehiculo> vehiculos = new Malloc();
        for(LinkedHashMap<String, Object> v : data) {
            Vehiculo new_v = new Vehiculo((String)v.get("id"), (String)v.get("marca"), (String)v.get("modelo"), (int)v.get("precio"), (int)v.get("km"), (String)v.get("fotos"), (String)v.get("Descripcion"));
            vehiculos.add(new_v);
            System.out.println("Vehiculo agregado: " + new_v.toString());
        }
        
        return vehiculos;
    }
    
  

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
