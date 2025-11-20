package org.iesbelen.model;

public class Producto {

    public int id_producto;
    public String nombre;
    public String descripcion;
    public double precio;
    public int stock;
    public int id_categoria;
    public String talla;
    public String color;

    public int getId_producto() {
        return id_producto;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public int getStock() {
        return stock;
    }
    public int getId_categoria() {
        return id_categoria;
    }
    public String getTalla() {
        return talla;
    }
    public String getColor() {
        return color;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;

        Producto producto = (Producto) o;

        return getId_producto() == producto.getId_producto();
    }

    @Override
    public int hashCode() {
        return getId_producto();
    }

}
