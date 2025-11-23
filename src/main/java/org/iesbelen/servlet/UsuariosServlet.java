package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.*;
import org.iesbelen.model.Usuario;
import org.iesbelen.model.Utilidades;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet(name = "usuariosServlet", value = "/tienda/usuarios/*")
public class UsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();


        if (pathInfo == null || "/".equals(pathInfo)) {
            UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
            List<Usuario> listaUsuario = usuarioDAO.getAll();

            request.setAttribute("listaUsuario", listaUsuario);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuario.jsp");

        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {


                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/crear-usuario.jsp");

            }else if(pathParts.length == 2 ){
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

                try {
                    request.setAttribute("usuario",
                            usuarioDAO.find(Integer.parseInt(pathParts[1])).orElse(null));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/detalle-usuario.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuario.jsp");
                }
            }

            else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                try {
                    request.setAttribute("usuario",
                            usuarioDAO.find(Integer.parseInt(pathParts[2])).orElse(null));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/editar-usuario.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuario.jsp");
                }

            } else {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuario.jsp");
            }
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        String pathInfo = request.getPathInfo();

        if (__method__ == null) {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(request.getParameter("nombre"));
            nuevoUsuario.setEmail(request.getParameter("email"));
            //Hash para la contraseña
            try {
                String hash = Utilidades.hashPassword(request.getParameter("password"));
                nuevoUsuario.setPassword(hash);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            //nuevoUsuario.setPassword(request.getParameter("password"));
            nuevoUsuario.setRol(request.getParameter("rol"));
            usuarioDAO.create(nuevoUsuario);

        } else if ("put".equalsIgnoreCase(__method__)) {
            doPut(request, response);
            return;

        } else if ("delete".equalsIgnoreCase(__method__)) {
            doDelete(request, response);
            return;

        } else {
            System.out.println("Opción POST no soportada.");
        }
        response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        try {
            //Cambiar creo que solo hay q poner los metoso para el upodate puse de mas
            int id = Integer.parseInt(request.getParameter("id_usuario"));
            Usuario usuario = new Usuario();
            usuario.setId_usuario(id);
            // producto.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
            usuario.setNombre(request.getParameter("nombre"));
            usuario.setEmail(request.getParameter("email"));
            try {
                String hash = Utilidades.hashPassword(request.getParameter("password"));
                usuario.setPassword(hash);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            //usuario.setPassword(request.getParameter("password"));
            usuario.setRol(request.getParameter("rol"));
            usuarioDAO.update(usuario);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        try {
            int id = Integer.parseInt(request.getParameter("codigo"));
            usuarioDAO.delete(id);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
    }
}
