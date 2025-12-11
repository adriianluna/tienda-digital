package org.iesbelen.dao;

import org.iesbelen.model.DetallePedido;
import java.util.List;

public interface DetallePedidoDAO {
    void create(DetallePedido detalle);
    List<DetallePedido> findByPedido(int idPedido);
    void delete(int id);
}