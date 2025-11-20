package org.iesbelen.dao;

import org.iesbelen.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO {

    List<Usuario> getAll();
    Optional<Usuario> find(int id);
    void create(Usuario usuario);
    void update(Usuario usuario);
    void delete(int idUsuario);
    public Optional<Usuario> findPorNombreYPassword(String nombre, String password);



}
