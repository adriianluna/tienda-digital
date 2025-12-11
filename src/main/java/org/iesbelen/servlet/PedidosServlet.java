package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.*;
import org.iesbelen.model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "pedidosServlet", value = "/tienda/pedidos/*")
public class PedidosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();

        PedidosDAO pedidoDAO = new PedidosDAOImpl();
        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario-logado");

        if (pathInfo == null || "/".equals(pathInfo)) {

            List<Pedido> listaPedidos;

            if ("admin".equalsIgnoreCase(usuarioLogado.getRol())) {
                listaPedidos = pedidoDAO.getAll();
            } else {
                listaPedidos = pedidoDAO.findByUsuario(usuarioLogado.getId_usuario());
            }

            request.setAttribute("listaPedidos", listaPedidos);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");

        } else {

            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");


            // /tienda/pedidos/{id}ver detalle
            if (pathParts.length == 2) {
                try {
                    int idPedido = Integer.parseInt(pathParts[1]);
                    Pedido pedido = pedidoDAO.find(idPedido).orElse(null);

                    if (pedido != null) {
                        List<DetallePedido> listaDetalles;

                        listaDetalles = (List<DetallePedido>) request.getSession()
                                .getAttribute("listaDetallePedidoSesion");

                        //Si estan en la sesion es porue ya hemos hecho un checkout
                        if (listaDetalles != null) {
                            request.getSession().removeAttribute("listaDetallePedidoSesion");
                        } else {
                            DetallePedidoDAO detalleDAO = new DetallePedidoDAOImpl();
                            listaDetalles = detalleDAO.findByPedido(idPedido);

                            ProductoDAO productoDAO = new ProductoDAOImpl();
                            for (DetallePedido detalle : listaDetalles) {
                                Producto producto = productoDAO.find(detalle.getId_producto()).orElse(null);
                                if (producto != null) {
                                    detalle.setNombreProducto(producto.getNombre());
                                }
                            }
                        }

                        request.setAttribute("pedido", pedido);
                        request.setAttribute("listaDetalles", listaDetalles);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/detalle-pedido.jsp");
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
                }

            } else {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
            }

            // /tienda/pedidos/eliminar/{id}
            if (pathParts.length == 3 && "eliminar".equals(pathParts[1])) {
                try {
                    int idEliminar = Integer.parseInt(pathParts[2]);
                    pedidoDAO.delete(idEliminar);

                    response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
                    return;

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
                    return;
                }
            }

            // /tienda/pedidos/editar/{id}
            if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                try {
                    int idEditar = Integer.parseInt(pathParts[2]);
                    Pedido pedidoEditar = pedidoDAO.find(idEditar).orElse(null);

                    if (pedidoEditar != null) {
                        request.setAttribute("pedido", pedidoEditar);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/editar-pedido.jsp");
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
                    }

                    dispatcher.forward(request, response);
                    return;

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pedidos/pedido.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            }

        }

        dispatcher.forward(request, response);
    }

   /* @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
            int idPedido = Integer.parseInt(pathParts[2]);
            String estado = request.getParameter("estado");

            PedidosDAO pedidoDAO = new PedidosDAOImpl();
            Pedido pedido = pedidoDAO.find(idPedido).orElse(null);

            if (pedido != null) {
                pedido.setEstado(estado);
                pedidoDAO.update(pedido);
            }

            response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
        }
    }*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");
        if ("put".equalsIgnoreCase(__method__)) {

            doPut(request, response);
        } else if ("delete".equalsIgnoreCase(__method__)) {

            doDelete(request, response);
        } else {
            System.out.println("Opción POST no soportada para pedidos.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPedidoStr = request.getParameter("id_pedido");
        String estado = request.getParameter("estado");

        if (idPedidoStr != null && estado != null) {
            try {
                int idPedido = Integer.parseInt(idPedidoStr);
                PedidosDAO pedidoDAO = new PedidosDAOImpl();
                Pedido pedido = pedidoDAO.find(idPedido).orElse(null);

                if (pedido != null) {
                    pedido.setEstado(estado);
                    pedidoDAO.update(pedido);
                }

            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        PedidosDAO pedidosDAO = new PedidosDAOImpl();
        String codigo = request.getParameter("id_pedido");

        try {
            int id = Integer.parseInt(codigo);

            pedidosDAO.delete(id);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}