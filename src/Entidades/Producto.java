/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Maugouber
 */
public class Producto {
    //atributos
    private int codigo;
    private int cantidad;
    private String nombre;
    private Float precio;
    
    //constructores

    public Producto() {
    }

    public Producto(int codigo, String nombre, Float precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    //getters y setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", cantidad=" + cantidad + ", nombre=" + nombre + ", precio=" + precio + '}';
    }
    
    public float totalProducto(){
        float pTotal = cantidad * precio;
        return pTotal;
    }
    
    
}
