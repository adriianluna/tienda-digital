package org.iesbelen.model;

public class DetallePedido {

    public int id_detalle;
    public int id_pedido;
    public int id_producto;
    public int cantidad;
    public double precioUnidad;

    //Para JOin
    private String fechaPedido;
    private String nombreProducto;

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getId_detalle() {
        return id_detalle;
    }
    public int getId_pedido() {
        return id_pedido;
    }
    public int getId_producto() {
        return id_producto;
    }
    public double getPrecioUnidad() {
        return precioUnidad;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }
    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }
}
