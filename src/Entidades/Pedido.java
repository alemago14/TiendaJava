/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;

/**
 *
 * @author Maugouber
 */
public class Pedido {
    //atributos
    private int nroDePedido;
    private Cliente cliente;
    private ArrayList<Producto> detalle;
    
    //constructores

    public Pedido() {
    }

    public Pedido(int nroDePedido, Cliente cliente, ArrayList<Producto> detalle) {
        this.nroDePedido = nroDePedido;
        this.cliente = cliente;
        this.detalle = detalle;
    }
    
    //getters y setters

    public int getNroDePedido() {
        return nroDePedido;
    }

    public void setNroDePedido(int nroDePedido) {
        this.nroDePedido = nroDePedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Producto> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<Producto> detalle) {
        this.detalle = detalle;
    }
    
    //metodos
    public float totalIva(){
        float suma = 0;
        suma = sumaProductos();
        double ttalIVA = 0;
        
        //calculamos el total de impuestos segun la condicion impositiva
        switch(cliente.getCondicionImpositiva()){
            case A:
                ttalIVA = suma * 0.1005;
                break;
                
            case B:
                ttalIVA = suma * 0.21;
                break;
                
            case X:
                ttalIVA = suma * 0.70;
                break;
        }
        
        return (float)ttalIVA;
    }
    
    public float totalFactura(){
        float total = sumaProductos();
        
        float iva = totalIva();
        return total + iva;
    }
    
    public float sumaProductos(){
        float total = 0;
        for (Producto producto : detalle) {
            total = total + producto.getPrecio();
        }
        return total;
    }
}

