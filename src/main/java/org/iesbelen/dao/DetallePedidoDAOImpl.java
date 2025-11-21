package org.iesbelen.dao;

import org.iesbelen.model.DetallePedido;
import org.iesbelen.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetallePedidoDAOImpl extends AbstractDAOImpl implements DetallePedidoDAO{
    @Override
    public List<DetallePedido> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        List<DetallePedido> listaDetalles = new ArrayList<>();

        try {
            conn = connectDB();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM detalle_pedido");

            while (rs.next()) {
                DetallePedido detallePedido = new DetallePedido();
                int idx = 1;
                detallePedido.setId_detalle(rs.getInt(idx++));
                detallePedido.setId_pedido(rs.getInt(idx++));
                detallePedido.setId_producto(rs.getInt(idx++));
                detallePedido.setCantidad(rs.getInt(idx));
                detallePedido.setPrecioUnidad(rs.getInt(idx));
                listaDetalles.add(detallePedido);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaDetalles;
    }

    @Override
    public Optional<DetallePedido> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM detalle_pedido WHERE id_detalle = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                DetallePedido detallePedido = new DetallePedido();
                int idx = 1;
                detallePedido.setId_detalle(rs.getInt(idx++));
                detallePedido.setId_pedido(rs.getInt(idx++));
                detallePedido.setId_producto(rs.getInt(idx++));
                detallePedido.setCantidad(rs.getInt(idx++));
                detallePedido.setPrecioUnidad(rs.getDouble(idx));
                return Optional.of(detallePedido);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public void create(DetallePedido detallePedido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad,precio_unitario) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, detallePedido.getId_pedido());
            ps.setInt(2, detallePedido.getId_producto());
            ps.setInt(3, detallePedido.getCantidad());
            ps.setDouble(4, detallePedido.getPrecioUnidad());
            ps.executeUpdate();

            ResultSet rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) detallePedido.setId_detalle(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }

    @Override
    public void update(DetallePedido detallePedido) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE detalle_pedido SET cantidad = ?,precio_unitario = ?  WHERE id_detalle = ?");
            int idx = 1;
            ps.setInt(idx++, detallePedido.getCantidad());
            ps.setDouble(idx++, detallePedido.getPrecioUnidad());
            ps.setInt(idx, detallePedido.getId_detalle());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de Detalle con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }


    }

    @Override
    public void delete(int idUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM detalle_pedido WHERE id_detalle = ?");
            int idx = 1;
            ps.setInt(idx, idUsuario);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de detalle con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
}
