/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Enumeraciones.Letra;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Maugouber
 */
public class NotaDeCredito {
    //atributos
    private LocalDateTime fechaEmision;
    private int nroNota;
    private int nTalonario;
    private Letra letra;
    private Cliente cliente = new Cliente();
    private String total;
    
    //constructor

    public NotaDeCredito() {
    }

    public NotaDeCredito(LocalDateTime fechaEmision, int nroNota, int nTalonario, Letra letra, Cliente cliente, String total) {
        this.fechaEmision = fechaEmision;
        this.nroNota = nroNota;
        this.nTalonario = nTalonario;
        this.letra = letra;
        this.cliente = cliente;
        this.total = total;
    }
    
    //getter y setters

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getNroNota() {
        return nroNota;
    }

    public void setNroNota(int nroNota) {
        this.nroNota = nroNota;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "NotaDeCredito{" + "fechaEmision=" + fechaEmision + ", nroNota=" + nroNota + ", nTalonario=" + nTalonario + ", letra=" + letra + ", cliente=" + cliente.getNroDeCliente() + ", total=" + total + '}';
    }
    
    
    
    
}
