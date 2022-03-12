/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Controladores.ControladorPrincipal;
import Entidades.Cliente;
import Entidades.NotaDeCredito;
import Enumeraciones.CondicionImpositiva;
import Enumeraciones.Letra;
import Enumeraciones.TipoDocumento;
import static Servicios.ClienteServicio.ruta;
import Vistas.newNotaCredito;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;


/**
 *
 * @author Maugouber
 */
public class NotaCreditoServicio {

    private NotaDeCredito notaCredito;
    private ArrayList<NotaDeCredito> notas = new ArrayList<>();
    private Date fecha;
    private Cliente cliente = new Cliente();
    
    public NotaCreditoServicio() {
    }
    
    //leer los pedidos cancelados y guardarlos en un arreglo
    public void leerPedidosCan(ControladorPrincipal con1){
        String nCliente;
        int nNOta = 0;
        
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        File archivoClientes = null;
        FileReader fr2 = null;
        BufferedReader br2 = null;
        
        try {
            archivo = new File (ruta+"\\PedidosCancelados.txt");
            archivoClientes = new File (ruta+"\\ListaClientes.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            Scanner scanner = new Scanner(archivo);
            
            while ((linea = br.readLine()) != null) {
                nNOta = nNOta + 1;
                cliente = new Cliente();
                notaCredito = new NotaDeCredito();
                
                
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                
                nCliente = delimitar.next();
                
                notaCredito.setTotal(delimitar.next());
                notaCredito.setnTalonario(Integer.parseInt(delimitar.next()));
                notaCredito.setFechaEmision(LocalDateTime.now());
                notaCredito.setNroNota(nNOta);
                notaCredito.setLetra(Letra.A);
                
                String lineaCliente;
                Scanner scannerCliente = new Scanner(archivoClientes);
                fr2 = new FileReader(archivoClientes);
                br2 = new BufferedReader(fr2);
                while ((linea = br2.readLine()) != null) {                    
                    Scanner delimitarC = new Scanner(linea);
                    delimitarC.useDelimiter("\\s*,\\s*");
                    String nArchivo = delimitarC.next();
                    if(nCliente.equals(nArchivo)){
                        cliente.setNroDeCliente(Integer.parseInt(nArchivo));
                        cliente.setDomicilio(delimitarC.next());
                        
                        switch(delimitarC.next()){
                            case "A":
                                cliente.setCondicionImpositiva(CondicionImpositiva.A);
                                break;
                                
                            case "B":
                                cliente.setCondicionImpositiva(CondicionImpositiva.B);
                                break;
                                
                            case "X":
                                cliente.setCondicionImpositiva(CondicionImpositiva.X);
                                break;
                        }
                        
                        switch(delimitarC.next()){
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
                        cliente.setNroDeDocumento(Integer.parseInt(delimitarC.next()));
                    }
                    
                }
                notaCredito.setCliente(cliente);
                notas.add(notaCredito);
                newNotaCredito cuadro = new newNotaCredito(con1,notaCredito);
                cuadro.setVisible(true);
               
                
                BufferedImage imagen = cuadro.crearImagen();
                File archivoNota = new File("C:\\Facturas\\Nota :"+notaCredito.getNroNota()+".png");
                ImageIO.write(imagen,"PNG" , archivo);
                
                cuadro.dispose();
                
            }
        } catch (Exception e) {
        }
      archivo.delete();
    }
    
         
        //seleccionador de impresora
        
    
    
}
