package org.iesbelen.model;

public class Pedido {

    public int id_pedido;
    public int id_usuario;
    public String fecha;
    public String estado;
    public double total;

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public void setFecha(String fecha) {

        this.fecha = fecha;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public int getId_pedido() {
        return id_pedido;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public String getFecha() {
        return fecha;
    }
    public String getEstado() {
        return estado;
    }
    public double getTotal() {
        return total;
    }
}
