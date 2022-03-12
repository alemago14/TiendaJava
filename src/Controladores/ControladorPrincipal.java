/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Servicios.ProductoServicio;
import Entidades.Cliente;
import Entidades.Pedido;
import Servicios.ClienteServicio;
import Servicios.FacturaServicio;
import Servicios.NotaCreditoServicio;
import Servicios.PedidoServicio;
import Vistas.ListaCliente;
import Vistas.ListaPedidos;
import Vistas.ListaPedidosFacturados;
import Vistas.ListaProductos;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Maugouber
 */
public class ControladorPrincipal {
    private Cliente cliente = new Cliente();
    private Pedido pedido = new Pedido();
    
    private ClienteServicio clienteServicio = new ClienteServicio();
    private ProductoServicio productoServicio = new ProductoServicio();
    private PedidoServicio pedidoServicio = new PedidoServicio();
    private NotaCreditoServicio notaServicio = new NotaCreditoServicio();
    private FacturaServicio facturaServicio = new FacturaServicio();
    
    private Timer timer = new Timer();
    
    //Timer temporizador = new Timer();
    
    //constructor
    public ControladorPrincipal() {
    }
    
    //metodo que abre la ventana de clientes
    public void cuadrodeClientes(){
        ListaCliente cuadro = new ListaCliente(this);
        cuadro.setVisible(true);
        productoServicio.resetearProductos();
    }
    
    //escribir nuevo cliente en un archivo txt
    public void nuevoCliente(JTextField clienteDomicilio, JComboBox<String> condicionImpositiva, JComboBox<String> tipoDocumento, JTextField nroDocumento){
        clienteServicio.crearCliente(clienteDomicilio, condicionImpositiva, tipoDocumento, nroDocumento);
    }
    
    //carga la tabla del cuadro clientes
    public void cargarClientes(JTable tabla){
        clienteServicio.cargarTablaCliente(tabla);
    }
    
    //carga del cliente seleccionado
    public void seleccCliente(JTable tabla, int fila, JLabel etiq){
        cliente = new Cliente();
        cliente = clienteServicio.selecCliente(tabla, fila, etiq);
    }
    
    //abrir ventana de productos
    public void cuadroProductos(){
        ListaProductos cuadro = new ListaProductos(cliente, this);
        cuadro.setVisible(true);
    }
    
    //ecribir nuevo producto en archivo
    public void nuevoProducto(JTextField nombre, JTextField precio){
        productoServicio.crearProducto(nombre, precio);
    }
    
    //lenar tabla de productos
    public void llenarTablaProductos(JTable tabla){
        productoServicio.llenarTablaProductos(tabla);
    }
    
    //agregar productos al pedido
    public void selecProductos(JTable tabla, int fila, JLabel etiq, JComboBox cantProductos){
        productoServicio.seleccionarProducto(fila, tabla, etiq, cantProductos);
    }
    
    //mostrar total pedido para confirmar
    public void sumarProductos(JLabel etiq, Cliente cliente){
        productoServicio.sumarProductos(etiq, cliente);
    }
    
    //cancelar pedido antes de pasar a lista de facturacion
    public void cancPedido(){
        productoServicio.cancPedido();
    }
    
    //aceptar pedido para pasarlo a la lista de pedidos
    public void subirPedido(Cliente cliente){
        pedido = new Pedido();
        pedido = productoServicio.subirPedido(cliente);
        
        pedidoServicio.nuevoPedido(pedido);
    }
    
    
    //mostrar cuadro de pedidos
    public void cuadroPedidos(){
        ListaPedidos cuadro = new ListaPedidos(this);
        cuadro.setVisible(true);
    }
    
    //mostrar todos los pedidos
    public void cargarPedidos(JTable tabla){
        pedidoServicio.cargarPedidos(tabla);
    }
    
    public void cancelarPedido(JTable tabla, int fila){
        
        pedidoServicio.borrarPedido(tabla, fila);
    }
    
    public void notas(){
        notaServicio.leerPedidosCan(this);
    }
    
    public void fact(){
        facturaServicio.leerFacturas(this);
    }
    
    //mostrar cuadro de pedidos facturados
    public void cuadroPedidosFacturados(){
        ListaPedidosFacturados cuadro = new ListaPedidosFacturados(this);
        cuadro.setVisible(true);
    }
    
    public void cargarTablaFacturados(JTable tabla){
        pedidoServicio.cargarPedidosFacturados(tabla);
    }
    
    public void cancelarPedidoFacturado(int fila, JTable tabla){
        pedidoServicio.cancelarPedidoFacturad(fila, tabla);
        pedidoServicio.borrarPedido(tabla, fila);
    }
    
    
            
            
    
    public void facturar(){
        facturaServicio.leerFacturas(this);
    }
    
    public void autoFacturar(){
        timer = new Timer();
        
        Date date = new Date();         
        Calendar hora20 = Calendar.getInstance();
        hora20.set(Calendar.HOUR_OF_DAY, 20); // 8 pm del día
        hora20.set(Calendar.MINUTE,00); // Con 0 minutos
        hora20.set(Calendar.SECOND, 0); // Con 0 segundos

        date = hora20.getTime();
        
        
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                facturar();
            }
        };
        
        timer.schedule(tarea, date, 86400000);

    }
    
    
}
