package org.iesbelen.dao;

import org.iesbelen.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAOImpl extends AbstractDAOImpl implements UsuarioDAO{
    @Override
    public synchronized List<Usuario> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;
        List<Usuario> listUsuario = new ArrayList<>();

        try {
            conn = connectDB();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM usuarios");

            while (rs.next()) {
                Usuario usuario = new Usuario();
                int idx = 1;
                usuario.setId_usuario(rs.getInt(idx++));
                usuario.setNombre(rs.getString(idx++));
                usuario.setEmail(rs.getString(idx++));
                usuario.setPassword(rs.getString(idx++));
                usuario.setRol(rs.getString(idx));
                listUsuario.add(usuario);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listUsuario;
    }

    @Override
    public synchronized Optional<Usuario> find(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("SELECT * FROM usuarios WHERE id_usuario = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                int idx = 1;
                usuario.setId_usuario(rs.getInt(idx++));
                usuario.setNombre(rs.getString(idx++));
                usuario.setPassword(rs.getString(idx++));
                usuario.setRol(rs.getString(idx));
                return Optional.of(usuario);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }

    @Override
    public synchronized void create(Usuario usuario) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("INSERT INTO usuarios (nombre, password, rol) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol());
            ps.executeUpdate();

            ResultSet rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) usuario.setId_usuario(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }

    @Override
    public synchronized void update(Usuario usuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connectDB();
            ps = conn.prepareStatement("UPDATE usuarios SET nombre=?, password=?, rol=? WHERE id_usuario=?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol());
            ps.setInt(4, usuario.getId_usuario());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }
    }

    @Override
    public synchronized void delete(int idUsuario) {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = connectDB();
            ps = conn.prepareStatement("DELETE FROM usuarios WHERE id_usuario=?");
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, null);
        }

    }

    //Para el futuro loin
    @Override
    public synchronized Optional<Usuario> findPorNombreYPassword(String nombre, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
            ps = conn.prepareStatement(sql);

            //  guarda contraseña hasheada:
            //String hash = Utilidades.hashPassword(password);
            ps.setString(1, nombre);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                int idx = 1;
                usuario.setId_usuario(rs.getInt(idx++));
                usuario.setNombre(rs.getString(idx++));
                usuario.setPassword(rs.getString(idx++));
                usuario.setRol(rs.getString(idx));
                return Optional.of(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();
    }
}
