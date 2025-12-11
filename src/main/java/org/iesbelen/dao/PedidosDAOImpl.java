package org.iesbelen.dao;

import org.iesbelen.model.Pedido;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidosDAOImpl extends AbstractDAOImpl implements PedidosDAO {

    @Override
    public synchronized void create(Pedido pedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            String sql = "INSERT INTO pedidos (id_usuario, fecha, estado, total) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, pedido.getId_usuario());
            ps.setTimestamp(2, Timestamp.valueOf(pedido.getFecha()));
            ps.setString(3, pedido.getEstado());
            ps.setDouble(4, pedido.getTotal());

            ps.executeUpdate();

            // Obtener el ID generado
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId_pedido(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public List<Pedido> getAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pedido> listaPedidos = new ArrayList<>();

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM pedidos ORDER BY fecha DESC");
            rs = ps.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                pedido.setEstado(rs.getString("estado"));
                pedido.setTotal(rs.getDouble("total"));
                listaPedidos.add(pedido);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return listaPedidos;
    }

    @Override
    public Optional<Pedido> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM pedidos WHERE id_pedido = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                pedido.setEstado(rs.getString("estado"));
                pedido.setTotal(rs.getDouble("total"));
                return Optional.of(pedido);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public List<Pedido> findByUsuario(int idUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Pedido> listaPedidos = new ArrayList<>();

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM pedidos WHERE id_usuario = ? ORDER BY fecha DESC");
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId_pedido(rs.getInt("id_pedido"));
                pedido.setId_usuario(rs.getInt("id_usuario"));
                pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                pedido.setEstado(rs.getString("estado"));
                pedido.setTotal(rs.getDouble("total"));
                listaPedidos.add(pedido);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return listaPedidos;
    }

    @Override
    public void update(Pedido pedido) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            String sql = "UPDATE pedidos SET estado = ?, total = ? WHERE id_pedido = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, pedido.getEstado());
            ps.setDouble(2, pedido.getTotal());
            ps.setInt(3, pedido.getId_pedido());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("DELETE FROM pedidos WHERE id_pedido = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }
}