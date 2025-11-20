package org.iesbelen.dao;

import org.iesbelen.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAOImpl extends AbstractDAOImpl implements ProductoDAO{
    @Override
    public synchronized void create(Producto producto) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();


            //1 alternativas comentadas:
            //ps = conn.prepareStatement("INSERT INTO fabricantes (nombre) VALUES (?)", new String[] {"codigo"});
            //Ver también, AbstractDAOImpl.executeInsert ...
            //Columna fabricante.codigo es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente.
            ps = conn.prepareStatement("INSERT INTO productos (nombre,descripcion,precio,stock,id_categoria,talla,color) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;

            ps.setString(idx++, producto.getNombre());
            ps.setString(idx++, producto.getDescripcion());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setInt(idx++, producto.getStock());
            ps.setInt(idx++,producto.getId_categoria());
            ps.setString(idx++, producto.getTalla());
            ps.setString(idx++, producto.getColor());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de fabricante con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                producto.setId_producto(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public synchronized List<Producto> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Producto> listProd = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM productos");
            while (rs.next()) {
                Producto prod = new Producto();
                int idx = 1;
                prod.setId_producto(rs.getInt(idx++));
                prod.setNombre(rs.getString(idx++));
                prod.setDescripcion(rs.getString(idx++));
                prod.setPrecio(rs.getDouble(idx++));
                prod.setStock(rs.getInt(idx++));
                prod.setId_categoria(rs.getInt(idx));
                prod.setTalla(rs.getString(idx++));
                prod.setColor(rs.getString(idx++));
                listProd.add(prod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listProd;

    }

    @Override
    public synchronized Optional<Producto> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM productos WHERE id_Producto = ?");

            int idx =  1;
            ps.setInt(idx, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Producto prod = new Producto();
                idx = 1;
                prod.setId_producto(rs.getInt(idx++));
                prod.setNombre(rs.getString(idx++));
                prod.setDescripcion(rs.getString(idx++));
                prod.setPrecio(rs.getDouble(idx++));
                prod.setStock(rs.getInt(idx++));
                prod.setId_categoria(rs.getInt(idx++));
                prod.setTalla(rs.getString(idx++));
                prod.setColor(rs.getString(idx++));


                return Optional.of(prod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public synchronized void update(Producto producto) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE productos SET nombre = ?,precio = ?  WHERE id_Producto = ?");
            int idx = 1;
            ps.setString(idx++, producto.getNombre());
            ps.setDouble(idx++, producto.getPrecio());
            ps.setInt(idx, producto.getId_producto());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de productos con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public synchronized void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM productos WHERE id_Producto = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de productos con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public synchronized List<Producto> getByNombre(String filtro) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Producto> productos = new ArrayList<>();

        try {
            conn = connectDB();

            String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + filtro + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Producto prod = new Producto();
                int idx = 1;
                prod.setId_producto(rs.getInt(idx++));
                prod.setNombre(rs.getString(idx++));
                prod.setDescripcion(rs.getString(idx++));
                prod.setPrecio(rs.getDouble(idx++));
                prod.setStock(rs.getInt(idx++));
                prod.setId_categoria(rs.getInt(idx));
                prod.setTalla(rs.getString(idx++));
                prod.setColor(rs.getString(idx++));
                productos.add(prod);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return productos;
    }
}
