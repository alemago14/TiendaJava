# TiendaJava

Una compañía de Comercial requiere realizar un sistema de facturación para poder adecuarse a la ley y suscribirse al mecanismo de facturación electrónica vigente. Tu compañía fue seleccionada para realizar dicho sistema y se te fue asignado el módulo de facturación del sistema.
Los clientes ingresan al sistema y tienen la posibilidad de crear sus pedidos, el cliente ingresara los pro-ductos uno a uno. Una vez finalizada la carga el sistema le indicará el total bruto del pedido y pedirá confirmación para ingresarlo, una vez confirmado el pedido quedará en estado pendiente hasta que el proceso nocturno de facturación lo facture.
El cliente tendrá la posibilidad de cancelar un pedido, si el pedido se encuentra en estado pendiente este se eliminará y si se encuentra facturado se creará una nota de crédito con motivo “cancelación de pedi-do” y monto igual al de la factura.
Todos días a las 8 de la noche los pedidos son procesados y facturados para ser despachados al día si-guiente por un repartidor. El proceso de facturación obtendrá la categoría de IVA del cliente del pedido para poder calcular el precio final de la factura.
Luego del proceso de facturación el sistema genera un reporte para ser enviado a Arba en archivo txt que incluye tanto las facturas como las notas de crédito generadas en el día con el siguiente formato Cliente-Tipo de Documento-Letra-Nro-Fecha de emisión-Monto

#Modelo de clases

•	Cliente
    o	Nro. de cliente
    o	Domicilio
    o	Condición impositiva
    o	Tipo de documento (DNI CUIT ETC)
    o	Nro. de documento
•	Producto
    o	Código
    o	Nombre
    o	precio
•	Pedido
    o	Nro. de pedido
    o	Cliente
    o	Detalles
        	Producto
•	Factura
    o	Cabecera
        	Fecha de emisión
        	Nro. de Factura
        	Código de emisión (nro. de talonario
        	Letra
        	Cliente
    o	Detalle
        	Producto
        	Precio Unitario
        	% de IVA correspondiente según Categoría de IVA del cliente
        	Cantidad
        	Precio de Venta
        	Precio NETO
        	Monto de IVA
    o	Pie de Factura
        	Total
        	Total IVA
•	Nota de Crédito
    o	Cabecera
        	Fecha de emisión
        	Nro. de Nota de crédito
        	Código de emisión (nro. de talonario
        	Letra
        	Cliente
    o	Pie de Nota de credito
        	Total
