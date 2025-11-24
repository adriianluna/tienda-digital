package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.*;
import org.iesbelen.model.DetallePedido;
import org.iesbelen.model.Pedido;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "detallesServlet", value = "/tienda/detallePedidos/*")
public class DetallePedidosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String pathInfo = request.getPathInfo();


        if (pathInfo == null || "/".equals(pathInfo)) {
            DetallePedidoDAO detallePedidoDAO = new DetallePedidoDAOImpl();
            List<DetallePedido> listaDetalles = detallePedidoDAO.getAll();

            request.setAttribute("listaDetalles", listaDetalles);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallePedidos/detallePedido.jsp");

        } else {
            pathInfo = pathInfo.replaceAll("/$", "");
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length == 2 && "crear".equals(pathParts[1])) {
                //Para mostrar datos en el select de categorias al crear producto
               /* CategoriasDAO catDAO = new CategoriasDAOImpl();
                request.setAttribute("listaCategoria", catDAO.getAll());*/

                PedidosDAO pedidoDAO = new PedidosDAOImpl();
                List<Pedido> listaPedidos = pedidoDAO.getAll();
                request.setAttribute("listaPedidos", listaPedidos);

                // Traer todos los productos
                ProductoDAO productoDAO = new ProductoDAOImpl();
                List<Producto> listaProductos = productoDAO.getAll();
                request.setAttribute("listaProductos", listaProductos);


                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallePedidos/crear-detalle.jsp");

            }else if(pathParts.length == 2 ){
                ProductoDAO productoDAO = new ProductoDAOImpl();

                try {
                    request.setAttribute("producto",
                            productoDAO.find(Integer.parseInt(pathParts[1])).orElse(null));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallePedidos/detalle-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallePedidos/producto.jsp");
                }
            }

            else if (pathParts.length == 3 && "editar".equals(pathParts[1])) {
                ProductoDAO productoDAO = new ProductoDAOImpl();
                try {
                    request.setAttribute("producto",
                            productoDAO.find(Integer.parseInt(pathParts[2])).orElse(null));
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallePedidos/editar-producto.jsp");

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallePedidos/producto.jsp");
                }

            } else {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detallePedidos/producto.jsp");
            }
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String __method__ = request.getParameter("__method__");

        if (__method__ == null) {
            // Crear un nuevo detalle de pedido
            String idPedidoStr = request.getParameter("idPedido");
            String idProductoStr = request.getParameter("idProducto");
            String cantidadStr = request.getParameter("cantidad");
            String precioUnidadStr = request.getParameter("precioUnidad");

            if (idPedidoStr != null && idProductoStr != null &&
                    cantidadStr != null && precioUnidadStr != null) {

                int idPedido = Integer.parseInt(idPedidoStr);
                int idProducto = Integer.parseInt(idProductoStr);
                int cantidad = Integer.parseInt(cantidadStr);
                double precioUnidad = Double.parseDouble(precioUnidadStr);

                DetallePedido detalle = new DetallePedido();
                detalle.setId_pedido(idPedido);
                detalle.setId_producto(idProducto);
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnidad(precioUnidad);

                DetallePedidoDAO detalleDAO = new DetallePedidoDAOImpl();
                detalleDAO.create(detalle);
            } else {
                System.out.println("Faltan parámetros para crear el detalle de pedido.");
            }

        } else if ("put".equalsIgnoreCase(__method__)) {
            doPut(request, response);
            return;

        } else if ("delete".equalsIgnoreCase(__method__)) {
            doDelete(request, response);
            return;

        } else {
            System.out.println("Opción POST no soportada.");
        }

        response.sendRedirect(request.getContextPath() + "/tienda/detallePedidos");
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DetallePedidoDAO detalleDAO = new DetallePedidoDAOImpl();

        try {
            int idDetalle = Integer.parseInt(request.getParameter("id_detalle"));
            int idPedido = Integer.parseInt(request.getParameter("id_pedido"));
            int idProducto = Integer.parseInt(request.getParameter("id_producto"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            double precioUnidad = Double.parseDouble(request.getParameter("precioUnidad"));

            DetallePedido detalle = new DetallePedido();
            detalle.setId_detalle(idDetalle);
            detalle.setId_pedido(idPedido);
            detalle.setId_producto(idProducto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnidad(precioUnidad);

            detalleDAO.update(detalle);

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/tienda/detallePedidos");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductoDAO productoDAO = new ProductoDAOImpl();

        try {
            int id = Integer.parseInt(request.getParameter("codigo"));
            productoDAO.delete(id);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/tienda/detallePedidos");
    }
}
