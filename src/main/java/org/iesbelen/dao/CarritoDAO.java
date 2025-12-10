package org.iesbelen.dao;

import org.iesbelen.model.CarritoItem;

import java.util.List;

public interface CarritoDAO {

    List<CarritoItem> getAll(int idUsuario);

    void add(int idUsuario, CarritoItem item);

    void delete(int idUsuario, int idProducto);
}
