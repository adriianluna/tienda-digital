package org.iesbelen.dao;

import org.iesbelen.model.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidosDAO {
    void create(Pedido pedido);
    List<Pedido> getAll();
    Optional<Pedido> find(int id);
    List<Pedido> findByUsuario(int idUsuario);
    void update(Pedido pedido);
    void delete(int id);
}