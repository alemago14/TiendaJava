/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cliente;
import Entidades.Pedido;
import Entidades.Producto;
import static Servicios.ClienteServicio.ruta;
import Validaciones.Validar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maugouber
 */
public class ProductoServicio {
    private Producto producto;
    private Pedido pedido;
    private ArrayList<Producto> productos = new ArrayList<>();
    private Validar validar = new Validar();
    
    private DefaultTableModel modelo;
    
    //constructor

    public ProductoServicio() {
    }
    
    
    //metodo para escribir productos nuevos en el archivo
    public void crearProducto(JTextField nombre, JTextField precio){
        //comprobamos que los campos no esten vacios
        validar.campoVacio(precio);
        validar.campoVacio(nombre);
        
        validar.conversionANumeros(precio);
        
        if(nombre.getText().isEmpty() == false && precio.getText().isEmpty() == false){
            //atributos para escribir archivo
            int nProducto = (int)Math.floor(Math.random()*(5000000 - 1 + 1)) + 1;
            
            FileWriter archivo = null;
            FileReader Fr = null;
            PrintWriter pw = null;
            
            try {
                archivo = new FileWriter(ruta + "\\ListaProductos.txt", true);
                pw = new PrintWriter(archivo);
                pw.println(nProducto+", "+nombre.getText() + ", " + precio.getText());
                pw.close();
                
                JOptionPane.showMessageDialog(null, "Producto N°"+nProducto+" creado.");
           
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    
    //Llenar la tabla con todos los productos
    public void llenarTablaProductos(JTable tabla){
        modelo = new DefaultTableModel();
        
        modelo.addColumn("N° Producto");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        
        tabla.setModel(modelo);
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        try {
            archivo = new File (ruta+"\\ListaProductos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            Scanner scanner = new Scanner(archivo);
            Object[] filas = new Object[3];
            
            while ((linea=br.readLine()) != null) {                
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                    filas[0] = delimitar.next();
                    filas[1] = delimitar.next();
                    filas[2] = delimitar.next();
                    
                modelo.addRow(filas);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        
    }
    
    
    public void seleccionarProducto(int fila, JTable tabla, JLabel etiq, JComboBox cantProd){
        producto = new Producto();
        
        if(fila == -1){
            etiq.setText("Seleccione un producto en la tabla");
        }else{
            String num = tabla.getValueAt(fila, 0).toString();
            producto.setCodigo(Integer.parseInt(num));
            
            producto.setNombre(tabla.getValueAt(fila, 1).toString());
            
            num = tabla.getValueAt(fila, 2).toString();
            producto.setPrecio(Float.parseFloat(num));
            
            producto.setCantidad(Integer.parseInt(cantProd.getSelectedItem().toString()));
            
            productos.add(producto);
            
            etiq.setText("Monto actual (sin iva): $" + sumaP());
        }
    }
    
    //calcular la suma de los prod sin imp
    public float sumaP(){
        float suma = 0;
        
        for (Producto producto1 : productos) {
            suma = suma + (producto1.getPrecio() * producto.getCantidad());
        }
        
        return suma;
    }
    
    //calcular pedido con impuesto para confirmarlo o cancelarlo
    public void sumarProductos(JLabel etiq, Cliente cliente){
        float suma = sumaP();
        float iva;
        
        switch(cliente.getCondicionImpositiva()){
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
        
        etiq.setVisible(true);
        etiq.setText("Monto total: $" + suma);
    }
    
    //cancelar pedido antes de pasar a lista de facturacion
    public void cancPedido(){
        JOptionPane.showMessageDialog(null, "Se cancelo el pedido");
        productos.clear();
    }
    
    //confirmar pedido y pasarlo a facturacion
    public Pedido subirPedido(Cliente cliente){
        pedido = new Pedido();
        
        int nPedido = (int)Math.floor(Math.random()*(5000000 - 1 + 1)) + 1;
        
        pedido.setCliente(cliente);
        pedido.setDetalle(productos);
        pedido.setNroDePedido(nPedido);
        
        JOptionPane.showMessageDialog(null, "Pedido N°" + nPedido +" creado tiene hasta las 20 hs para cancelarlo.");
        
        return pedido;
        
    }
    
    public void resetearProductos(){
        productos.clear();
    }
    
}
