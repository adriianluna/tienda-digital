package org.iesbelen.model;

public class Categorias {

    public int id_categoria;
    public String nombre;
    public String descripcion;

    public int getId_categoria() {
        return id_categoria;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
