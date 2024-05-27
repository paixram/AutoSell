/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.model;

import java.util.List;

/**
 *
 * @author José Miguel
 */
public class Vehiculo {
    private String id;
    private String marca;
    private String modelo;
    private double precio;
    private int km;
    private List<String> fotos;

    public Vehiculo(String id, String marca, String modelo, double precio, int km, List<String> fotos) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.km = km;
        this.fotos = fotos;
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

    public double getPrecio() {
        return precio;
    }

    public int getKm() {
        return km;
    }

    public List<String> getFotos() {
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

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
    
}
