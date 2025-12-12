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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "carritoServlet", value = "/tienda/carrito/*")
public class CarritoServlet extends HttpServlet {

    private ProductoDAOImpl productoDAO = new ProductoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<CarritoItem> listaItems = (List<CarritoItem>) request.getSession()
                .getAttribute("carrito");
        if (listaItems == null) {
            listaItems = new ArrayList<>();
        }

        request.setAttribute("listaItems", listaItems);
        request.getRequestDispatcher("/WEB-INF/jsp/carritos/carrito.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        List<CarritoItem> listaItems = (List<CarritoItem>) request.getSession().getAttribute("carrito");

        if (listaItems == null) {
            listaItems = new ArrayList<>();
        }


        if ("/anadir".equals(pathInfo)) {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            Producto prod = productoDAO.find(idProducto).orElse(null);

            //Si esta ya existe el producto aumenta la cantidad
            if (prod != null) {
                CarritoItem itemExistente = listaItems.stream()
                        .filter(item -> item.getProducto().getId_producto() == idProducto)
                        .findFirst()
                        .orElse(null);

                if (itemExistente != null) {
                    itemExistente.setCantidad(itemExistente.getCantidad() + cantidad);
                } else {
                    listaItems.add(new CarritoItem(prod, cantidad));
                }
            }

            request.getSession().setAttribute("carrito", listaItems);
            response.sendRedirect(request.getContextPath());
            return;
        }


        if ("/eliminar".equals(pathInfo)) {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            listaItems.removeIf(item -> item.getProducto().getId_producto() == idProducto);

            request.getSession().setAttribute("carrito", listaItems);
            response.sendRedirect(request.getContextPath() + "/tienda/carrito");
            return;
        }


        if ("/checkout".equals(pathInfo)) {
            Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuario-logado");

            if (usuarioLogado == null) {

                response.sendRedirect(request.getContextPath() + "/tienda/usuarios/login");
                return;
            }

            if (listaItems.isEmpty()) {

                response.sendRedirect(request.getContextPath() + "/tienda/carrito");
                return;
            }


            double total = 0;
            for (CarritoItem item : listaItems) {
                total += item.getTotal();
            }


            Pedido pedido = new Pedido();
            pedido.setId_usuario(usuarioLogado.getId_usuario());
            pedido.setFecha(LocalDateTime.now());
            pedido.setEstado("pendiente");
            pedido.setTotal(total);

            PedidosDAO pedidoDAO = new PedidosDAOImpl();
            pedidoDAO.create(pedido);


            DetallePedidoDAO detalleDAO = new DetallePedidoDAOImpl();
            List<DetallePedido> listaDetalles = new ArrayList<>();
            for (CarritoItem item : listaItems) {
                DetallePedido detalle = new DetallePedido();
                detalle.setId_pedido(pedido.getId_pedido());
                detalle.setId_producto(item.getProducto().getId_producto());
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecio_unitario(item.getProducto().getPrecio());
                detalle.setNombreProducto(item.getProducto().getNombre()); //
                detalleDAO.create(detalle);


                listaDetalles.add(detalle);
            }

            request.getSession().setAttribute("listaDetallePedidoSesion", listaDetalles);
            request.getSession().removeAttribute("carrito");

            response.sendRedirect(request.getContextPath() + "/tienda/pedidos/" + pedido.getId_pedido());
            return;
        }
    }
}