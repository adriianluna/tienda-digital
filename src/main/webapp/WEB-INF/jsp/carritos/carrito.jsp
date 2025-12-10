<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.CarritoItem" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Carrito de compras</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <h1 class="text-center mb-4">Carrito de compras</h1>

    <%
        List<CarritoItem> listaItems = (List<CarritoItem>) request.getAttribute("listaItems");
        double total = 0;
    %>

    <%
        if (listaItems != null && !listaItems.isEmpty()) {
    %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Producto</th>
            <th>Precio unitario</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (CarritoItem item : listaItems) {
                double subtotal = item.getProducto().getPrecio() * item.getCantidad();
                total += subtotal;
        %>
        <tr>
            <td><%= item.getProducto().getNombre() %></td>
            <td>€ <%= item.getProducto().getPrecio() %></td>
            <td><%= item.getCantidad() %></td>
            <td>€ <%= subtotal %></td>
            <td>
                <form action="<%= request.getContextPath() %>/tienda/carrito/eliminar" method="post">
                    <input type="hidden" name="idProducto" value="<%= item.getProducto().getId_producto() %>"/>
                    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
        <tfoot>
        <tr>
            <th colspan="3" class="text-end">Total:</th>
            <th colspan="2">€ <%= total %></th>
        </tr>
        </tfoot>
    </table>
    <%
    } else {
    %>
    <p class="text-center">El carrito está vacío.</p>
    <%
        }
    %>

    <div class="text-center mt-4">
        <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Seguir comprando</a>
        <% if (listaItems != null && !listaItems.isEmpty()) { %>
        <a href="#" class="btn btn-success">Finalizar compra</a>
        <% } %>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
