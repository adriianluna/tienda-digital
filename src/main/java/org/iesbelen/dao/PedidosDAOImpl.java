package org.iesbelen.dao;

import org.iesbelen.model.Pedido;
import org.iesbelen.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidosDAOImpl extends AbstractDAOImpl implements PedidosDAO {
    @Override
    public List<Pedido> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        List<Pedido> listaPedido = new ArrayList<>();

        try {
            conn = connectDB();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM pedidos ");

            while (rs.next()) {
                Pedido pedido = new Pedido();
                int idx = 1;
                pedido.setId_pedido(rs.getInt(idx++));
                pedido.setId_usuario(rs.getInt(idx++));
                pedido.setFecha(rs.getString(idx++));
                pedido.setEstado(rs.getString(idx++));
                pedido.setTotal(rs.getDouble(idx));
                listaPedido.add(pedido);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaPedido;
    }

    /*@Override
    public Optional<Pedido> find(int id) {
        return Optional.empty();
    }*/

    @Override
    public synchronized void create(Pedido pedido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("INSERT INTO pedidos (id_usuario,fecha, estado,total) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, pedido.getId_usuario());
            ps.setString(2, pedido.getFecha());
            ps.setString(3, pedido.getEstado());
            ps.setDouble(4, pedido.getTotal());
            ps.executeUpdate();

            ResultSet rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) pedido.setId_pedido(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }

    }

    @Override
    public synchronized void update(Pedido pedido) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE pedidos SET estado = ?,total = ?  WHERE id_pedido = ?");
            int idx = 1;
            ps.setString(idx++, pedido.getEstado());
            ps.setDouble(idx++, pedido.getTotal());
            ps.setInt(idx, pedido.getId_pedido());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de pedido con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
    @Override
    public synchronized void delete(int idPedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM pedidos WHERE id_pedido = ?");
            int idx = 1;
            ps.setInt(idx, idPedido);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de pedido con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
}
