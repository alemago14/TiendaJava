/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;
import Enumeraciones.CondicionImpositiva;
import Enumeraciones.TipoDocumento;

/**
 *
 * @author Maugouber
 */
public class Cliente {
    //atributos
    private int nroDeCliente;
    private String domicilio;
    private CondicionImpositiva condicionImpositiva;
    private TipoDocumento tipoDeDocumento;
    private int nroDeDocumento;
    
    //contructores

    public Cliente() {
    }

    public Cliente(int nroDeCliente, String domicilio, CondicionImpositiva condicionImpositiva, TipoDocumento tipoDeDocumento, int nroDeDocumento) {
        this.nroDeCliente = nroDeCliente;
        this.domicilio = domicilio;
        this.condicionImpositiva = condicionImpositiva;
        this.tipoDeDocumento = tipoDeDocumento;
        this.nroDeDocumento = nroDeDocumento;
    }
    
    //getters y setters

    public int getNroDeCliente() {
        return nroDeCliente;
    }

    public void setNroDeCliente(int nroDeCliente) {
        this.nroDeCliente = nroDeCliente;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public CondicionImpositiva getCondicionImpositiva() {
        return condicionImpositiva;
    }

    public void setCondicionImpositiva(CondicionImpositiva condicionImpositiva) {
        this.condicionImpositiva = condicionImpositiva;
    }

    public TipoDocumento getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public void setTipoDeDocumento(TipoDocumento tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public int getNroDeDocumento() {
        return nroDeDocumento;
    }

    public void setNroDeDocumento(int nroDeDocumento) {
        this.nroDeDocumento = nroDeDocumento;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nroDeCliente=" + nroDeCliente + ", domicilio=" + domicilio + ", condicionImpositiva=" + condicionImpositiva + ", tipoDeDocumento=" + tipoDeDocumento + ", nroDeDocumento=" + nroDeDocumento + '}';
    }
    
    
}
