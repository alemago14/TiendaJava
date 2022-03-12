/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Enumeraciones.Letra;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Maugouber
 */
public class Factura {
    
    //atributos
    private LocalDateTime fechaEmision;
    private int nFactura;
    private int nTalonario;
    private Letra letra;
    private Cliente cliente;
    private ArrayList<Producto> detalle;
    
    //contructores

    public Factura() {
    }

    public Factura(LocalDateTime fechaEmision, int nFactura, int nTalonario, Letra letra, Cliente cliente, ArrayList<Producto> detalle) {
        this.fechaEmision = fechaEmision;
        this.nFactura = nFactura;
        this.nTalonario = nTalonario;
        this.letra = letra;
        this.cliente = cliente;
        this.detalle = detalle;
    }
    
    
    //getters y setters

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getnFactura() {
        return nFactura;
    }

    public void setnFactura(int nFactura) {
        this.nFactura = nFactura;
    }

    public int getnTalonario() {
        return nTalonario;
    }

    public void setnTalonario(int nTalonario) {
        this.nTalonario = nTalonario;
    }

    public Letra getLetra() {
        return letra;
    }

    public void setLetra(Letra letra) {
        this.letra = letra;
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

    @Override
    public String toString() {
        return "Factura{" + "fechaEmision=" + fechaEmision + ", nFactura=" + nFactura + ", nTalonario=" + nTalonario + ", letra=" + letra + ", cliente=" + cliente + ", detalle=" + detalle + '}';
    }
    
    public float totalIva(){
    float ttalIVA = 0;
        
        switch(cliente.getCondicionImpositiva()){
            case A:
                ttalIVA = (float) (sumaP() * 0.1005);
                break;
                
            case B:
                ttalIVA = (float) (sumaP() * 0.21);
                break;
                
            case X:
                ttalIVA = (float) (sumaP() * 0.70);
                break;
        }
        
        return (float)ttalIVA;
    }
    
    
    public float sumaP(){
        float suma = 0;
        for (Producto producto : detalle) {
            suma = suma + producto.totalProducto();
        }
        return suma;
    }
    
    public float totalFactura(){
        float total = sumaP() + totalIva();
        return total;
    }
    
    public float porcentaje(){
        float porc = 0;
        switch(cliente.getCondicionImpositiva()){
            case A:
                porc = (float) 0.1005;
                break;
                
            case B:
                porc = (float) 0.21;
                break;
                
            case X:
                porc = (float) 0.70;
                break;
        }
        return porc;
    }
}
