package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Producto;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public class ProductosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();


        if (pathInfo == null || "/".equals(pathInfo)) {
            ProductoDAO productoDao = new ProductoDAOImpl();
            List<Producto> listaProducto = productoDao.getAll();

            request.setAttribute("listaProducto", listaProducto);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");

        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/crear-producto.jsp");

            }else if(pathParts.length == 2 ){
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                try {
                    request.setAttribute("usuario",
                            usuarioDAO.find(Integer.parseInt(pathParts[1])).orElse(null));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/detalle-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");
                }
            }

            else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                try {
                    request.setAttribute("usuario",
                            usuarioDAO.find(Integer.parseInt(pathParts[2])).orElse(null));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/editar-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");
                }

            } else {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");
            }
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");
        ProductoDAO productoDAO = new ProductoDAOImpl();
        String pathInfo = request.getPathInfo();

        if (__method__ == null) {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(request.getParameter("nombreProducto"));
            nuevoProducto.setDescripcion(request.getParameter("descripcion"));
            nuevoProducto.setPrecio(Double.parseDouble(request.getParameter("precio")));
            nuevoProducto.setStock(Integer.parseInt(request.getParameter("stock")));
            nuevoProducto.setId_categoria(Integer.parseInt(request.getParameter("categoriaId")));
            nuevoProducto.setTalla(request.getParameter("talla"));
            nuevoProducto.setTalla((request.getParameter("color")));
            productoDAO.create(nuevoProducto);

        } else if ("put".equalsIgnoreCase(__method__)) {
            doPut(request, response);
            return;

        } else if ("delete".equalsIgnoreCase(__method__)) {
            doDelete(request, response);
            return;

        } else {
            System.out.println("Opción POST no soportada.");
        }
        response.sendRedirect(request.getContextPath() + "/tienda/productos");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductoDAO productoDAO = new ProductoDAOImpl();

        try {
            //Cambiar creo que solo hay q poner los metoso para el upodate puse de mas
            int id = Integer.parseInt(request.getParameter("id_producto"));
            Producto producto = new Producto();
            producto.setId_producto(id);
            producto.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
            producto.setNombre(request.getParameter("nombre"));
            producto.setDescripcion(request.getParameter("descripcion"));
            producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
            producto.setStock(Integer.parseInt(request.getParameter("stock")));
            producto.setId_categoria(Integer.parseInt(request.getParameter("categoriaId")));
            producto.setTalla(request.getParameter("talla"));
            producto.setTalla((request.getParameter("color")));
            productoDAO.update(producto);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/tienda/productos");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductoDAO productoDAO = new ProductoDAOImpl();

        try {
            int id = Integer.parseInt(request.getParameter("id_producto"));
            productoDAO.delete(id);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/tienda/productos");
    }
}
