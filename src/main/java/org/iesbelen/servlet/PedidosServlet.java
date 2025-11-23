package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.*;
import org.iesbelen.model.Pedido;
import org.iesbelen.model.Producto;
import org.iesbelen.model.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "pedidosServlet", value = "/tienda/pedidos/*")
public class PedidosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();


        if (pathInfo == null || "/".equals(pathInfo)) {
            PedidosDAO pedidosDAO= new PedidosDAOImpl();
            List<Pedido> listaPedido = pedidosDAO.getAll();

            request.setAttribute("listaPedido", listaPedido);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");

        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
              //Para poder seleccionar el usuario al crearlo
                UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                List<Usuario> listaUsuarios = usuarioDAO.getAll();
                request.setAttribute("listaUsuarios", listaUsuarios);
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/crear-pedido.jsp");

            }/*else if(pathParts.length == 2 ){
                ProductoDAO productoDAO = new ProductoDAOImpl();

                try {
                    request.setAttribute("producto",
                            productoDAO.find(Integer.parseInt(pathParts[1])).orElse(null));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/detalle-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/producto.jsp");
                }
            }*/

            else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                ProductoDAO productoDAO = new ProductoDAOImpl();
                try {
                    request.setAttribute("pedido",
                            productoDAO.find(Integer.parseInt(pathParts[2])).orElse(null));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/editar-pedido.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
                }

            } else {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
            }
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");
        PedidosDAO pedidosDAO = new PedidosDAOImpl();
        String pathInfo = request.getPathInfo();

        if (__method__ == null) {
            Pedido nuevoPedido = new Pedido();
            nuevoPedido.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            nuevoPedido.setFecha(request.getParameter("fecha"));
            nuevoPedido.setEstado(request.getParameter("estado"));
            nuevoPedido.setTotal(Double.parseDouble(request.getParameter("total")));
            pedidosDAO.create(nuevoPedido);

        } else if ("put".equalsIgnoreCase(__method__)) {
            doPut(request, response);
            return;

        } else if ("delete".equalsIgnoreCase(__method__)) {
            doDelete(request, response);
            return;

        } else {
            System.out.println("Opción POST no soportada.");
        }
        response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PedidosDAO pedidosDAO = new PedidosDAOImpl();

        try {
            //Cambiar creo que solo hay q poner los metoso para el upodate puse de mas
            int id = Integer.parseInt(request.getParameter("id_pedido"));
            Pedido pedido = new Pedido();
            pedido.setId_pedido(id);
            // producto.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
            pedido.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
            pedido.setFecha(request.getParameter("fecha"));
            pedido.setEstado(request.getParameter("estado"));
            pedido.setTotal(Double.parseDouble(request.getParameter("total")));
            pedidosDAO.update(pedido);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PedidosDAO pedidosDAO = new PedidosDAOImpl();

        try {
            int id = Integer.parseInt(request.getParameter("codigo"));
            pedidosDAO.delete(id);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
    }
}
