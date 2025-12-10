package org.iesbelen.dao;

import org.iesbelen.model.CarritoItem;
import org.iesbelen.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarritoDAOImpl extends AbstractDAOImpl implements CarritoDAO {

    @Override
    public synchronized List<CarritoItem> getAll(int idUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarritoItem> lista = new ArrayList<>();

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM carrito WHERE id_usuario = ?");
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();

            ProductoDAOImpl productoDAO = new ProductoDAOImpl();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad");

                Optional<Producto> prodOpt = productoDAO.find(idProducto);
                if (prodOpt.isPresent()) {
                    CarritoItem item = new CarritoItem(prodOpt.get(), cantidad);
                    lista.add(item);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return lista;
    }

    @Override
    public synchronized void add(int idUsuario, CarritoItem item) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement(
                    "INSERT INTO carrito (id_usuario, id_producto, cantidad) VALUES (?, ?, ?)");
            ps.setInt(1, idUsuario);
            ps.setInt(2, item.getProducto().getId_producto());
            ps.setInt(3, item.getCantidad());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Insert de carrito con 0 filas insertadas.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }

    @Override
    public synchronized void delete(int idUsuario, int idProducto) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement(
                    "DELETE FROM carrito WHERE id_usuario = ? AND id_producto = ?");
            ps.setInt(1, idUsuario);
            ps.setInt(2, idProducto);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("Delete de carrito con 0 filas eliminadas.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }
}
