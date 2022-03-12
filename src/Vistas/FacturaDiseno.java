/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorPrincipal;
import Entidades.Factura;
import Entidades.Producto;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maugouber
 */
public class FacturaDiseno extends javax.swing.JFrame {

    private ControladorPrincipal con1;
    private Factura factura;
    private DefaultTableModel modelo;
    /**
     * Creates new form FacturaDiseno
     */
    public FacturaDiseno(ControladorPrincipal con1, Factura factura) {
        initComponents();
        
        this.con1 = con1;
        this.factura = factura;
        
        cargarDatos();
        cargarTabla();
    }

    public void cargarDatos(){
        letraFactura.setText(factura.getLetra().toString());
        
        //formatear fecha
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
        fecha.setText("Fecha:" +factura.getFechaEmision().format(dateTimeFormatter));
        
        nFactura.setText("N° Factura"+String.valueOf(factura.getnFactura()));
        nTalonario.setText("Talon N°" + String.valueOf(factura.getnTalonario()));
        nCliente.setText("Cliente n° " + String.valueOf(factura.getCliente().getNroDeCliente()));
        documentoCliente.setText("Documento: " + factura.getCliente().getTipoDeDocumento().toString() + " " + String.valueOf(factura.getCliente().getNroDeCliente()));
        condicionImpositiva.setText("Cond. Imp." + factura.getCliente().getCondicionImpositiva().toString());
        totalIvaFactura.setText("$" + factura.totalIva());
        totalFactura.setText("$" + factura.totalFactura());
        
        
    }
    
    public void cargarTabla(){
        modelo = new DefaultTableModel();
        
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio Unit.");
        modelo.addColumn("% IVA");
        modelo.addColumn("Cant");
        modelo.addColumn("Precio venta");
        modelo.addColumn("Precio neto");
        modelo.addColumn("Monto de IVA");
        
        jTable1.setModel(modelo);
        
        Object[] filas = new Object[7];
        for (Producto producto : factura.getDetalle()) {
            filas[0] = producto.getNombre();
            filas[1] = producto.getPrecio();
            filas[2] = factura.porcentaje();
            filas[3] = producto.getCantidad();
            filas[4] = (producto.totalProducto() * factura.porcentaje()) + producto.totalProducto();
            filas[5] = producto.totalProducto();
            filas[6] = (producto.totalProducto() * factura.porcentaje());
            
            modelo.addRow(filas);
        }
    }
    
    public BufferedImage crearImagen(){
        BufferedImage imagen = new BufferedImage(formatoFactura1.getWidth(), formatoFactura1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagen.createGraphics();
        formatoFactura1.paint(g);
        return imagen;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formatoFactura1 = new Vistas.formatoFactura();
        jLabel1 = new javax.swing.JLabel();
        letraFactura = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        nFactura = new javax.swing.JLabel();
        nTalonario = new javax.swing.JLabel();
        nCliente = new javax.swing.JLabel();
        domicilioCliente = new javax.swing.JLabel();
        documentoCliente = new javax.swing.JLabel();
        condicionImpositiva = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        totalIvaFactura = new javax.swing.JLabel();
        totalFactura = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        formatoFactura1.setBackground(new java.awt.Color(255, 255, 255));
        formatoFactura1.setPreferredSize(new java.awt.Dimension(507, 618));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Factura");

        letraFactura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        letraFactura.setText("A");
        letraFactura.setToolTipText("");

        fecha.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        fecha.setText("Fecha: 09/03/2022");

        nFactura.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nFactura.setText("Factura N° 10101010");

        nTalonario.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nTalonario.setText("Talonario N°: 615");
        nTalonario.setToolTipText("");

        nCliente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nCliente.setText("Cliente N°: 615");

        domicilioCliente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        domicilioCliente.setText("Domicilio: San Martin 512");
        domicilioCliente.setToolTipText("");

        documentoCliente.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        documentoCliente.setText("Documento: DNI 38453804");
        documentoCliente.setToolTipText("");

        condicionImpositiva.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        condicionImpositiva.setText("Cond. Imp.: A");
        condicionImpositiva.setToolTipText("");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Total IVA");
        jLabel10.setToolTipText("");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Total");
        jLabel11.setToolTipText("");

        totalIvaFactura.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        totalIvaFactura.setText("$21");
        totalIvaFactura.setToolTipText("");

        totalFactura.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        totalFactura.setText("$121");
        totalFactura.setToolTipText("");

        javax.swing.GroupLayout formatoFactura1Layout = new javax.swing.GroupLayout(formatoFactura1);
        formatoFactura1.setLayout(formatoFactura1Layout);
        formatoFactura1Layout.setHorizontalGroup(
            formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatoFactura1Layout.createSequentialGroup()
                .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatoFactura1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(letraFactura)
                        .addGap(119, 119, 119)
                        .addComponent(nFactura))
                    .addGroup(formatoFactura1Layout.createSequentialGroup()
                        .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formatoFactura1Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(jLabel1))
                            .addGroup(formatoFactura1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(nCliente)
                                .addGap(101, 101, 101)
                                .addComponent(nTalonario)
                                .addGap(76, 76, 76)
                                .addComponent(condicionImpositiva)))
                        .addGap(0, 41, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator1)
            .addGroup(formatoFactura1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(domicilioCliente)
                .addGap(110, 110, 110)
                .addComponent(documentoCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2)
            .addComponent(jScrollPane1)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatoFactura1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalIvaFactura)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(totalFactura))
                .addGap(53, 53, 53))
        );
        formatoFactura1Layout.setVerticalGroup(
            formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatoFactura1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(letraFactura)
                        .addComponent(nFactura))
                    .addComponent(fecha))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nCliente)
                    .addComponent(nTalonario)
                    .addComponent(condicionImpositiva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(domicilioCliente)
                    .addComponent(documentoCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formatoFactura1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalIvaFactura)
                    .addComponent(totalFactura))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(formatoFactura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(formatoFactura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 270, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel condicionImpositiva;
    private javax.swing.JLabel documentoCliente;
    private javax.swing.JLabel domicilioCliente;
    private javax.swing.JLabel fecha;
    private Vistas.formatoFactura formatoFactura1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel letraFactura;
    private javax.swing.JLabel nCliente;
    private javax.swing.JLabel nFactura;
    private javax.swing.JLabel nTalonario;
    private javax.swing.JLabel totalFactura;
    private javax.swing.JLabel totalIvaFactura;
    // End of variables declaration//GEN-END:variables
}
