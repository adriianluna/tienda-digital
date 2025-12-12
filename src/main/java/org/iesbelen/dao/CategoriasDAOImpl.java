package org.iesbelen.dao;

import org.iesbelen.model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriasDAOImpl extends AbstractDAOImpl implements CategoriasDAO {


    @Override
    public void create(Categoria categoria) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO categorias (nombre,descripcion) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, categoria.getNombre());
            ps.setString(idx++, categoria.getDescripcion());


            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de categorias con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                categoria.setId_categoria(rsGenKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public List<Categoria> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Categoria> listaCat = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM categorias");
            while (rs.next()) {
                Categoria cat = new Categoria();
                int idx = 1;
                cat.setId_categoria(rs.getInt(idx++));
                cat.setNombre(rs.getString(idx++));
                cat.setDescripcion(rs.getString(idx++));

                listaCat.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaCat;

    }

    @Override
    public Optional<Categoria> find(int idCategoria) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM categorias WHERE id_categoria = ?");

            int idx =  1;
            ps.setInt(idx, idCategoria);

            rs = ps.executeQuery();

            if (rs.next()) {
                Categoria cat = new Categoria();
                idx = 1;
                cat.setId_categoria(rs.getInt(idx++));
                cat.setNombre(rs.getString(idx++));
                cat.setDescripcion(rs.getString(idx++));

                return Optional.of(cat);
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
    public void update(Categoria categoria) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE categorias SET nombre = ?,descripcion = ?  WHERE id_categoria = ?");
            int idx = 1;
            ps.setInt(idx++, categoria.getId_categoria());
            ps.setString(idx++, categoria.getNombre());
            ps.setString(idx++, categoria.getDescripcion());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de categoria con 0 registros actualizados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM categorias WHERE id_categoria = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de categorias con 0 registros eliminados.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }
}
