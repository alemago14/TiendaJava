/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Controladores.ControladorPrincipal;
import Entidades.Cliente;
import Entidades.Factura;
import Entidades.Pedido;
import Entidades.Producto;
import Enumeraciones.CondicionImpositiva;
import Enumeraciones.Letra;
import Enumeraciones.TipoDocumento;
import static Servicios.ClienteServicio.ruta;
import Vistas.FacturaDiseno;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author Maugouber
 */
public class FacturaServicio {

    private Factura factura;
    private Pedido pedido;
    private Cliente cliente;
    private Producto producto;
    private ArrayList<Producto> productos = new ArrayList<>();

    public static String ruta = System.getProperty("user.dir") + "\\src\\Archivos";

    //constructor 
    public FacturaServicio() {
    }

    //metodopara ller el archivo de pedidos a facturar y enviar la factura al jpanel para imprimir
    public void leerFacturas(ControladorPrincipal con1) {
        File directorio = new File("C:\\Facturas");

        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        String nCliente;
        int conteo = 0;

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        File archivoClientes = null;
        FileReader fr2 = null;
        BufferedReader br2 = null;

        FileReader fr3 = null;
        BufferedReader br3 = null;

        try {
            archivo = new File(ruta + "\\ListaPedidos.txt");
            archivoClientes = new File(ruta + "\\ListaClientes.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            Scanner scanner = new Scanner(archivo);

            while ((linea = br.readLine()) != null) {
                conteo = conteo + 1;
                cliente = new Cliente();

                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");

                String nPedido = delimitar.next();
                nCliente = delimitar.next();
                String cadProductos = delimitar.next();

                //buscamos el cliente
                String lineaCliente;
                Scanner scannerCliente = new Scanner(archivoClientes);
                fr2 = new FileReader(archivoClientes);
                br2 = new BufferedReader(fr2);
                while ((linea = br2.readLine()) != null) {
                    productos = new ArrayList<>();
                    Scanner delimitarC = new Scanner(linea);
                    delimitarC.useDelimiter("\\s*,\\s*");
                    String nArchivo = delimitarC.next();
                    if (nCliente.equals(nArchivo)) {
                        cliente.setNroDeCliente(Integer.parseInt(nArchivo));
                        cliente.setDomicilio(delimitarC.next());

                        switch (delimitarC.next()) {
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

                        switch (delimitarC.next()) {
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
                        System.out.println(cliente.toString());

                        String[] pConf = cadProductos.split(":");
                        String nProducto = "";
                        String nCantProd = "";
                        for (String string : pConf) {
                            String[] pConf2 = string.split("-");
                            for (String string1 : pConf2) {
                                if (string1.length() >= 3) {
                                    nProducto = string1;
                                }
                                if (string1.length() >= 1 && string1.length() < 3) {
                                    nCantProd = string1;
                                }

                            }
                            if (!nProducto.isEmpty() && !nCantProd.isEmpty()) {
                                producto = leerProductos(nProducto, nCantProd);

                                productos.add(producto);

                            }
                        }
                        /*Armar factura aqui*/
                        factura = armarFactura(cliente, productos, nPedido, conteo);
                        System.out.println(factura.toString());
                        imprimirPdf(con1);

                    }

                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Las facturas se han guardado en \"C:\\\\Facturas\\\\\"");
        pasarAFacturados();
        hacerTicket();

    }

    public Producto leerProductos(String pro, String can) {
        int num = Integer.parseInt(pro);
        Producto producto19 = new Producto();

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(ruta + "\\ListaProductos.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            Scanner scanner = new Scanner(archivo);

            while ((linea = br.readLine()) != null) {
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                String nProducto = delimitar.next();

                if (nProducto.equals(pro)) {
                    producto19.setCodigo(num);
                    producto19.setNombre(delimitar.next());
                    producto19.setPrecio(Float.parseFloat(delimitar.next()));
                    producto19.setCantidad(Integer.parseInt(can));

                }
            }

            return producto19;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return null;
    }

    public Factura armarFactura(Cliente cliente1, ArrayList<Producto> prods, String nPed, int conteo) {
        factura = new Factura();

        factura.setnFactura(Integer.parseInt(nPed));
        factura.setFechaEmision(LocalDateTime.now());
        factura.setLetra(Letra.A);
        factura.setCliente(cliente1);
        factura.setDetalle(prods);
        factura.setnTalonario(conteo);

        return factura;

    }

    public void imprimirPdf(ControladorPrincipal con1) throws IOException {
        FacturaDiseno fd = new FacturaDiseno(con1, factura);
        fd.setVisible(true);

        BufferedImage imagen = fd.crearImagen();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] bytes = baos.toByteArray();

        File archivo = new File("C:\\Facturas\\" + factura.getnTalonario() + factura.getnFactura() + ".png");
        ImageIO.write(imagen, "PNG", archivo);

        fd.dispose();
    }

    //metodo que una vez hechas las facturas pasa los pedidos a pedidos facturas
    public void pedidosFacturados() {
        File archivoPendientes = null;
        FileReader fr = null;
        BufferedReader br = null;

        File archivoFacturados = new File(ruta + "\\PedidosFacturados.txt");
        FileReader fr2 = null;
        BufferedReader br2 = null;

        try {
            br = new BufferedReader(new FileReader(ruta + "\\ListaPedidos.txt"));
            PrintWriter pw = null;
            String linea, num2;

            while ((linea = br.readLine()) != null) {
                pw = new PrintWriter(archivoFacturados);
                pw.println(linea);
                pw.close();
            }
        } catch (Exception e) {
        }
        archivoPendientes.delete();
    }

    public Cliente devolverCliente(String numCliente) {
        Cliente cliente15 = new Cliente();

        File archivoClientes = null;
        FileReader fr2 = null;
        BufferedReader br2 = null;

        archivoClientes = new File(ruta + "\\ListaClientes.txt");

        try {
            //buscamos el cliente
            String lineaCliente;
            Scanner scannerCliente = new Scanner(archivoClientes);
            fr2 = new FileReader(archivoClientes);
            br2 = new BufferedReader(fr2);
            while ((lineaCliente = br2.readLine()) != null) {
                Scanner delimitarC = new Scanner(lineaCliente);
                delimitarC.useDelimiter("\\s*,\\s*");
                String nArchivo = delimitarC.next();
                if (numCliente.equals(nArchivo)) {
                    cliente15.setNroDeCliente(Integer.parseInt(nArchivo));
                    cliente15.setDomicilio(delimitarC.next());

                    switch (delimitarC.next()) {
                        case "A":
                            cliente15.setCondicionImpositiva(CondicionImpositiva.A);
                            break;

                        case "B":
                            cliente15.setCondicionImpositiva(CondicionImpositiva.B);
                            break;

                        case "X":
                            cliente15.setCondicionImpositiva(CondicionImpositiva.X);
                            break;
                    }

                    switch (delimitarC.next()) {
                        case "DNI":
                            cliente15.setTipoDeDocumento(TipoDocumento.DNI);
                            break;

                        case "DU":
                            cliente15.setTipoDeDocumento(TipoDocumento.DU);
                            break;

                        case "CUIL":
                            cliente15.setTipoDeDocumento(TipoDocumento.CUIL);
                            break;

                        case "CUIT":
                            cliente15.setTipoDeDocumento(TipoDocumento.CUIT);
                            break;
                    }
                    cliente15.setNroDeDocumento(Integer.parseInt(delimitarC.next()));
                }
            }
        } catch (Exception e) {
        }

        return cliente15;
    }

    public void pasarAFacturados() {
        File pedidosFac = new File(ruta + "\\PedidosFacturados.txt");
        File pedidos = new File(ruta + "\\ListaPedidos.txt");

        FileWriter archivo = null;
        FileReader fr = null;
        PrintWriter pw = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(pedidos);
            br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                archivo = new FileWriter(pedidosFac, true);
                pw = new PrintWriter(archivo);
                pw.println(linea);
                pw.close();
            }
        } catch (Exception e) {
        }

    }

    public void hacerTicket() {
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fec = fecha.format(dateTimeFormatter);

        File ticket = new File("C:\\Facturas\\ticket" + fec + ".txt");
        File pedFac = new File(ruta + "\\PedidosFacturados.txt");
        File pedCan = new File(ruta + "\\PedidosCanceladosados.txt");

        FileWriter archivo = null;
        FileReader Fr = null;
        PrintWriter pw = null;

        if (pedFac.exists()) {
            try {
                pw = new PrintWriter(ticket);
                pw.println("Facturas del dia");

                String linea, numCliente, monto;

                try {
                    BufferedReader br = new BufferedReader(new FileReader(ruta + "\\PedidosFacturados.txt"));
                    while ((linea = br.readLine()) != null) {
                        Scanner delimitar = new Scanner(linea);
                        delimitar.useDelimiter("\\s*,\\s*");

                        String vacio = delimitar.next();
                        numCliente = delimitar.next();
                        vacio = delimitar.next();
                        vacio = delimitar.next();
                        vacio = delimitar.next();
                        monto = delimitar.next();

                        cliente = devolverCliente(numCliente);

                        try {

                            pw.println("Clinte N°: " + cliente.toString());
                            pw.println("Fecha: " + (fecha.format(dateTimeFormatter2)));
                            pw.println("Monto: $" + monto);

                            pw.println("---------------------------------------------------------");

                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e) {
                }
                if (pedCan.exists()) {
                    try {
                        BufferedReader br2 = new BufferedReader(new FileReader(ruta + "\\PedidosCancelados.txt"));
                        while ((linea = br2.readLine()) != null)  {
                            Scanner delimitar = new Scanner(linea);
                            delimitar.useDelimiter("\\s*,\\s*");
                            while ((linea = br2.readLine()) != null) {
                                delimitar = new Scanner(linea);
                                delimitar.useDelimiter("\\s*,\\s*");

                                numCliente = delimitar.next();
                                monto = delimitar.next();

                                cliente = devolverCliente(numCliente);
                                try {
                                    
                                    pw = new PrintWriter(ticket);
                                    pw.println("Clinte N°: " + cliente.toString());
                                    pw.println("Fecha: " + fecha.format(dateTimeFormatter2));
                                    pw.println("Monto: $" + monto);

                                    pw.println("---------------------------------------------------------");
                                    
                                } catch (Exception e) {
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                } else {
                }
                pw.close();
            } catch (Exception e) {
            }
            JOptionPane.showMessageDialog(null, "Ticket Arba guardado en C:/Facturas");
        } else {
            JOptionPane.showMessageDialog(null, "No hay pedidos facturados");
        }
    }
}
