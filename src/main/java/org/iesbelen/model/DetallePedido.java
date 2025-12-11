package org.iesbelen.model;

public class DetallePedido {
    private int id_detalle;
    private int id_pedido;
    private int id_producto;
    private int cantidad;
    private double precio_unitario;

    // Atributos extra para mostrar info (no están en BD)
    private String nombreProducto;
    private double subtotal; // cantidad * precio_unitario

    // Constructores
    public DetallePedido() {}

    // Getters y Setters
    public int getId_detalle() { return id_detalle; }
    public void setId_detalle(int id_detalle) { this.id_detalle = id_detalle; }

    public int getId_pedido() { return id_pedido; }
    public void setId_pedido(int id_pedido) { this.id_pedido = id_pedido; }

    public int getId_producto() { return id_producto; }
    public void setId_producto(int id_producto) { this.id_producto = id_producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(double precio_unitario) { this.precio_unitario = precio_unitario; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public double getSubtotal() { return cantidad * precio_unitario; }
}