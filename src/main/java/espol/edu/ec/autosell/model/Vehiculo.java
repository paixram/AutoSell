/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.edu.ec.autosell.model;

import dumpfmm.Almacenable;
import dumpfmm.FieldOrder;
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
    
    /*
    Jeep Avenger eléctrico:
Precio: Desde $37.500 €
Descripción: El Jeep Avenger es un SUV eléctrico pequeño y elegante que ofrece una conducción divertida y un diseño atractivo. Tiene un motor de 156 CV y una batería de 50 kWh que le da una autonomía de hasta 392 km.
Kilometraje: 10000 km.
Tesla Model Y:
Precio: Desde $43.970 €
Descripción: El Tesla Model Y es un SUV eléctrico de tamaño mediano que ofrece un rendimiento impresionante y una gran autonomía. Tiene un motor de 351 CV y una batería de 75 kWh que le da una autonomía de hasta 507 km.
Kilometraje: 15000 km.

Toyota Hilux:
Precio: Desde $34.000 €
Descripción: La Toyota Hilux es una camioneta pickup robusta y confiable que ofrece una gran capacidad de carga y un buen rendimiento todoterreno. Tiene un motor de 2.4 litros y 150 CV.
Kilometraje: 12000 km.

Ford Ranger:
Precio: Desde $32.200 €
Descripción: La Ford Ranger es una camioneta pickup moderna y versátil que ofrece una conducción cómoda y una amplia gama de tecnologías. Tiene un motor de 2.0 litros y 180 CV.
Kilometraje: 20000 km.

Mitsubishi L200:
Precio: Desde $29.900 €
Descripción: La Mitsubishi L200 es una camioneta pickup económica y duradera que ofrece una gran capacidad de remolque y un buen rendimiento todoterreno. Tiene un motor de 2.4 litros y 150 CV.
Kilometraje: 25000 km.

Nissan Navara:
Precio: Desde $31.500 €
Descripción: La Nissan Navara es una camioneta pickup tecnológica y cómoda que ofrece una conducción segura y una amplia gama de funciones. Tiene un motor de 2.3 litros y 190 CV.
Kilometraje: 8000 km.

Isuzu D-Max:
Precio: Desde $27.500 €
Descripción: La Isuzu D-Max es una camioneta pickup robusta y confiable que ofrece una gran capacidad de carga y un buen rendimiento todoterreno. Tiene un motor de 1.9 litros y 164 CV.
Kilometraje: 9500 km.

Toyota RAV4:
Toyota RAV4 SUV
Precio: Desde $35.200 €
Descripción: El Toyota RAV4 es un SUV compacto popular que ofrece una conducción cómoda, un diseño atractivo y una amplia gama de funciones. Tiene un motor de 2.0 litros y 177 CV.
Kilometraje: 17500 km.

Hyundai Tucson:
Hyundai Tucson SUV
Precio: Desde $30.500 €
Descripción: El Hyundai Tucson es un SUV compacto moderno y tecnológico que ofrece una conducción segura, un interior espacioso y una amplia gama de tecnologías. Tiene un motor de 1.6 litros y 150 CV.
Kilometraje: 20500 km.

Kia Sportage:
Kia Sportage SUV
Precio: Desde $32.700 €
Descripción: El Kia Sportage es un SUV compacto elegante y deportivo que ofrece una conducción divertida, un diseño atractivo y una garantía de 7 años. Tiene un motor de 1.6 litros y 180 CV.
Kilometraje: 19800 km.

    */

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
}
