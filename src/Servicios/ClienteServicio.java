/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Entidades.Cliente;
import Enumeraciones.CondicionImpositiva;
import Enumeraciones.TipoDocumento;
import Validaciones.Validar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
public class ClienteServicio {
    private Cliente cliente = new Cliente();
    private Validar validar = new Validar();
    private DefaultTableModel modelo;
    
    public static String ruta = System.getProperty("user.dir")+"\\src\\Archivos";

    //constructor
    public ClienteServicio() {
    }
    
    //metodo para escribir un nuevo cliente en el archivo
    public void crearCliente(JTextField clienteDomicilio, JComboBox<String> condicionImpositiva, JComboBox<String> tipoDocumento, JTextField nroDocumento){
        //comprobaciones de los campos
        validar.campoVacio(nroDocumento);
        validar.campoVacio(clienteDomicilio);
        
        validar.conversionANumeros(nroDocumento);
        
        if(nroDocumento.getText().isEmpty() == false && clienteDomicilio.getText().isEmpty() == false){
            int nCliente = (int)Math.floor(Math.random()*(5000 - 1 + 1)) + 1;
            String nDocumento = nroDocumento.getText();
            String domicilio = clienteDomicilio.getText();
            
            String condImp;
            switch(condicionImpositiva.getSelectedItem().toString()){
                case "Monotributo":
                    condImp = "B";
                    break;
                    
                case "Responsable Inscripto":
                    condImp = "A";
                    break;
                    
                default:
                    condImp = "X";
                    break;
                    
            }
            String tipoDocu = tipoDocumento.getSelectedItem().toString();
            
            //atributos para escribir en un archivo
            FileWriter archivo = null;
            FileReader Fr = null;
            PrintWriter pw = null;
            
            //escribimos en el archivo
            try {
                archivo = new FileWriter(ruta + "\\ListaClientes.txt", true);
                pw = new PrintWriter(archivo);
                pw.println(nCliente+", "+ domicilio +", "+ condImp+", " + tipoDocu+", " + nDocumento);
                pw.close();
                
                JOptionPane.showMessageDialog(null, "Cliente N°"+nCliente+" creado.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    
    
    public void cargarTablaCliente(JTable tabla){
            modelo = new DefaultTableModel();
            
            modelo.addColumn("N° Cliente");
            modelo.addColumn("Domicilio");
            modelo.addColumn("Cond. Impositiva");
            modelo.addColumn("Tipo de Documento");
            modelo.addColumn("N° Documento");
            
            tabla.setModel(modelo);
            
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            
            try {
                archivo = new File (ruta+"\\ListaClientes.txt");
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
                    filas[2] = delimitar.next();
                    filas[3] = delimitar.next();
                    filas[4] = delimitar.next();
                
                modelo.addRow(filas);
                }
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    
    //Seleccionar cliente
    public Cliente selecCliente(JTable tabla, int fila, JLabel etiq){
        cliente = new Cliente();
    
        if(fila == -1){
            etiq.setText("Seleccione un cliente en la tabla");
        }else{
            String num = tabla.getValueAt(fila, 0).toString();
            
            cliente.setNroDeCliente(Integer.parseInt(num));
            cliente.setDomicilio((String)tabla.getValueAt(fila, 1));
            num = tabla.getValueAt(fila, 4).toString();
            cliente.setNroDeDocumento(Integer.parseInt(num));
            String letra =(String) tabla.getValueAt(fila, 2);
                if(letra.equals("A")){
                    cliente.setCondicionImpositiva(CondicionImpositiva.A);
                }else{
                    if(letra.equals("B")){
                        cliente.setCondicionImpositiva(CondicionImpositiva.B);
                    }else{
                        cliente.setCondicionImpositiva(CondicionImpositiva.X);
                    }
                }
                
                letra = (String) tabla.getValueAt(fila, 3);
                
                switch(letra){
                    case "DNI":
                    cliente.setTipoDeDocumento(TipoDocumento.DNI);
                    break;
                
                    case "DU":
                    cliente.setTipoDeDocumento(TipoDocumento.DU);
                    break;
                
                    case "CUIL":
                    cliente.setTipoDeDocumento(TipoDocumento.CUIL);
                    break;
                
                    case "CUIT":
                     cliente.setTipoDeDocumento(TipoDocumento.CUIT);
                    break;
                    }
                etiq.setText("Cliente N°"+cliente.getNroDeCliente()+" seleccionado.");
        }
        return cliente;
    }
    
}
