package org.iesbelen.dao;



import org.iesbelen.model.DetallePedido;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoDAO {
    List<DetallePedido> getAll();
    Optional<DetallePedido> find(int id);
    void create(DetallePedido detallePedido);
    void update(DetallePedido detallePedido);
    void delete(int idUsuario);
    //public Optional<DetallePedido> findPorNombreYPassword(String nombre, String password);

}
