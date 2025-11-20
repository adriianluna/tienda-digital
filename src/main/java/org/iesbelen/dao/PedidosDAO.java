package org.iesbelen.dao;



import org.iesbelen.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidosDAO {

    List<Pedido> getAll();
    //Optional<Pedido> find(int id);
    void create(Pedido pedido);
    void update(Pedido pedido);
    void delete(int idPedido);

}
