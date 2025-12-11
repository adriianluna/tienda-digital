package org.iesbelen.dao;

import org.iesbelen.model.DetallePedido;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAOImpl extends AbstractDAOImpl implements DetallePedidoDAO {

    @Override
    public synchronized void create(DetallePedido detalle) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            String sql = "INSERT INTO detalle_pedidos (id_pedido, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, detalle.getId_pedido());
            ps.setInt(2, detalle.getId_producto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecio_unitario());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }

    @Override
    public List<DetallePedido> findByPedido(int idPedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DetallePedido> listaDetalles = new ArrayList<>();

        try {
            conn = connectDB();
            String sql = "SELECT dp.*, p.nombre as nombre_producto " +
                    "FROM detalle_pedidos dp " +
                    "JOIN productos p ON dp.id_producto = p.id_producto " +
                    "WHERE dp.id_pedido = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();

            while (rs.next()) {
                DetallePedido detalle = new DetallePedido();
                detalle.setId_detalle(rs.getInt("id_detalle"));
                detalle.setId_pedido(rs.getInt("id_pedido"));
                detalle.setId_producto(rs.getInt("id_producto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio_unitario(rs.getDouble("precio_unitario"));
                detalle.setNombreProducto(rs.getString("nombre_producto"));
                listaDetalles.add(detalle);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return listaDetalles;
    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("DELETE FROM detalle_pedidos WHERE id_detalle = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }
}