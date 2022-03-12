/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Pedido;
import Entidades.Producto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maugouber
 */
public class PedidoServicio {
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    private DefaultTableModel modelo = new DefaultTableModel();
    private Pedido pedido;
    
    public static String ruta = System.getProperty("user.dir")+"\\src\\Archivos";
    
    //constructor

    public PedidoServicio() {
    }
    
    //agregar pedido a la lista de pedidos a un archivo
    public void nuevoPedido(Pedido pedido){
        pedidos.add(pedido);
        
        FileWriter archivo = null;
        FileReader Fr = null;
        PrintWriter pw = null;
        
        String codigoProductos = "";
        for (Producto producto : pedido.getDetalle()) {
            codigoProductos = codigoProductos + ":"+ producto.getCodigo()+"-"+producto.getCantidad();
        }
        try {
            archivo = new FileWriter(ruta + "\\ListaPedidos.txt", true);
                pw = new PrintWriter(archivo);
                pw.println(pedido.getNroDePedido() +", "+ pedido.getCliente().getNroDeCliente()+", "+codigoProductos+", "+pedido.getDetalle().size()+", "+sumaP(pedido)+", "+sumarProductos(pedido));
                pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //mostrar tabla de pedidos
    public void cargarPedidos(JTable tabla){
        modelo = new DefaultTableModel();
        
        modelo.addColumn("N° Pedido");
        modelo.addColumn("N° Clientes");
        modelo.addColumn("Cant Productos");
        modelo.addColumn("Monto neto");
        modelo.addColumn("Monto total");
        
        tabla.setModel(modelo);
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File (ruta+"\\ListaPedidos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            Scanner scanner = new Scanner(archivo);
            Object[] filas = new Object[5];
            
            while ((linea=br.readLine()) != null) {                
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                    filas[0] = delimitar.next();
                    filas[1] = delimitar.next();
                    String cad = delimitar.next();
                    filas[2] = delimitar.next();
                    filas[3] = delimitar.next();
                    filas[4] = delimitar.next();
                    
                modelo.addRow(filas);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    
    //mostrar tabla de pedidos facturados
    public void cargarPedidosFacturados(JTable tabla){
        modelo = new DefaultTableModel();
        
        modelo.addColumn("N° Pedido");
        modelo.addColumn("N° Clientes");
        modelo.addColumn("Cant Productos");
        modelo.addColumn("Monto neto");
        modelo.addColumn("Monto total");
        
        tabla.setModel(modelo);
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File (ruta+"\\PedidosFacturados.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            Scanner scanner = new Scanner(archivo);
            Object[] filas = new Object[5];
            
            while ((linea=br.readLine()) != null) {                
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                    filas[0] = delimitar.next();
                    filas[1] = delimitar.next();
                    String cad = delimitar.next();
                    filas[2] = delimitar.next();
                    filas[3] = delimitar.next();
                    filas[4] = delimitar.next();
                    
                modelo.addRow(filas);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
    
    //sumar productos sin impuestos
    public float sumaP(Pedido pedido1){
        float suma = 0;
        for (Producto producto : pedido1.getDetalle()) {
            suma = suma + (producto.getPrecio()*producto.getCantidad());
        }
        
        return suma;
    }
    
    //sumar total factura con impuestos
    public float sumarProductos(Pedido pedido1){
        float suma = sumaP(pedido1);
        float iva = 0;
        
        switch(pedido1.getCliente().getCondicionImpositiva()){
            case A:
                iva = (float)(suma * 0.1005);
                suma = suma + iva;
                break;
                
            case B:
                iva = (float)(suma * 0.21);
                suma = suma + iva;
                break;
                
            case X:
                iva = (float)(suma * 0.7);
                suma = suma + iva;
                break;
        }
        
        return suma;
    }
    
    
    //Cancelar pedido - lo borraremos del archivo de pedidos facturados y lo pasamos al archivo de pedidos cancelados
    public void cancelarPedidoFacturad(int fila, JTable tabla){
        if(fila == -1){
            JOptionPane.showMessageDialog(null, "Seleccione un pedido para cancelarlo");
        }else{
            String cadena = tabla.getValueAt(fila, 1).toString() + ", " + tabla.getValueAt(fila, 4).toString()+", "+(fila + 1);
            
            //escribimos el archivo de pedidos cancelados
            FileWriter archivo = null;
            FileReader fr = null;
            PrintWriter pw = null;
            try {
                archivo = new FileWriter(ruta + "\\PedidosCancelados.txt", true);
                pw = new PrintWriter(archivo);
                pw.println(cadena);
                pw.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            
           
        }
        
        
    }

    public void borrarPedido(JTable tabla, int fila){
        String num = tabla.getValueAt(fila, 0).toString();
        
        File archivoNuevo = new File(ruta + "\\auxiliar.txt");
        File archivo = new File(ruta + "\\ListaPedidos.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta + "\\ListaPedidos.txt"));
            PrintWriter pw = null;
            String linea, num2;
            Scanner scanner = new Scanner(archivo);
            
            while ((linea = br.readLine()) != null) {                
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                num2 = delimitar.next();
                if(num.equals(num2)){
                    continue;
                }else{
                    pw = new PrintWriter(archivoNuevo);
                    pw.println(linea);
                    pw.close();
                }
            }
            
        } catch (Exception e) {
        }
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta + "\\auxiliar.txt"));
            PrintWriter pw = null;
            String linea, num2;
            Scanner scanner = new Scanner(archivoNuevo);
            
            while ((linea = br.readLine()) != null) {                
                
                    pw = new PrintWriter(archivo);
                    pw.println(linea);
                    pw.close();
            }
            
            JOptionPane.showMessageDialog(null, "Pedido cancelado");

        } catch (Exception e) {
        }
        archivoNuevo.delete();
    }

}
