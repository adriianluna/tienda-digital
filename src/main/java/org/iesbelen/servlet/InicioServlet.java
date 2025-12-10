package org.iesbelen.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.ProductoDAO;
import org.iesbelen.dao.ProductoDAOImpl;
import org.iesbelen.model.Producto;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "inicioServlet", value = "")
public class InicioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductoDAO productoDao = new ProductoDAOImpl();
        List<Producto> listaProducto = productoDao.getAll();

        request.setAttribute("listaProducto", listaProducto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productos/tienda.jsp");
        dispatcher.forward(request, response);
    }
}

