package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.CarritoDAOImpl;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.CarritoItem;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "carritoServlet", value = "/tienda/carrito/*")
public class CarritoServlet extends HttpServlet {

    private CarritoDAOImpl carritoDAO = new CarritoDAOImpl();
    private ProductoDAOImpl productoDAO = new ProductoDAOImpl();

   /* Para cuando lo haga con base de datos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo != null) pathInfo = pathInfo.replaceAll("/$", "");

        // Mostrar carrito
        List<CarritoItem> listaItems = carritoDAO.getAll(0); // Usuario fijo 0 por ahora
        request.setAttribute("listaItems", listaItems);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/carritos/carrito.jsp");
        dispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo != null) pathInfo = pathInfo.replaceAll("/$", "");

        if ("/anadir".equals(pathInfo)) {
            try {
                int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                Optional<Producto> prodOpt = productoDAO.find(idProducto);
                if (prodOpt.isPresent()) {
                    CarritoItem item = new CarritoItem(prodOpt.get(), cantidad);
                    carritoDAO.add(0, item); // Usuario fijo 0
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            // Redirigir a tienda para seguir comprando
            //NO añadimos nada porque queremos mantenernos en la misma pagina
            response.sendRedirect(request.getContextPath() );
        }
    }*/
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

        //Para mantener lso datos
       List<CarritoItem> listaItems = (List<CarritoItem>) request.getSession()
               .getAttribute("carrito");
       if (listaItems == null) {
           listaItems = new ArrayList<>();
       }

       request.setAttribute("listaItems", listaItems);


       request.getRequestDispatcher("/WEB-INF/jsp/carritos/carrito.jsp").forward(request, response);
   }

    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       //Lista de la sesion
        List<CarritoItem> listaItems = (List<CarritoItem>) request.getSession().getAttribute("carrito");
        if (listaItems == null) {
            listaItems = new ArrayList<>();
        }

        String pathInfo = request.getPathInfo();
        if ("/anadir".equals(pathInfo)) {
            try {
                int idProducto = Integer.parseInt(request.getParameter("idProducto"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                Optional<Producto> prodOpt = productoDAO.find(idProducto);
                if (prodOpt.isPresent()) {
                    CarritoItem item = new CarritoItem(prodOpt.get(), cantidad);

                    // Guardamos en sesión
                   *//* List<CarritoItem> listaItems = (List<CarritoItem>) request.getSession()
                            .getAttribute("carrito");
                    if (listaItems == null) {
                        listaItems = new ArrayList<>();
                    }*//*

                    // Si el producto ya existe, sumamos cantidad
                    boolean encontrado = false;
                    for (CarritoItem ci : listaItems) {
                        if (ci.getProducto().getId_producto() == idProducto) {
                            ci.setCantidad(ci.getCantidad() + cantidad);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        listaItems.add(item);
                    }

                    request.getSession().setAttribute("carrito", listaItems);
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            // Redirigimos de nuevo a tienda.jsp
            response.sendRedirect(request.getContextPath());
        }else if ("/eliminar".equals(pathInfo)) {


        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        // eliminar el producto de la lista
        listaItems.removeIf(item -> item.getProducto().getId_producto() == idProducto);
    }
        // Guardar la lista de nuevo en la sesión
        request.getSession().setAttribute("carrito", listaItems);

        // Redirigir a la página del carrito
        response.sendRedirect(request.getContextPath() + "/tienda/carrito");
    }*/

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

            if (prod != null) {

                boolean encontrado = false;

                // Verificar si ya está en el carrito
                for (CarritoItem item : listaItems) {
                    if (item.getProducto().getId_producto() == idProducto) {
                        item.setCantidad(item.getCantidad() + cantidad);
                        encontrado = true;
                        break;
                    }
                }

                // Si no estaba, añadir nuevo
                if (!encontrado) {
                    listaItems.add(new CarritoItem(prod, cantidad));
                }
            }

            // Guardar carrito actualizado
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
    }


}
