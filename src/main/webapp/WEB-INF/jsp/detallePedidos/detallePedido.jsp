<%@ page import="org.iesbelen.model.DetallePedido" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 24/11/2025
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Detalle pedido</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<main>
    <section>
        <div class="container mt-4">
            <h1>Detalle de Pedidos</h1>

            <form action="${pageContext.request.contextPath}/tienda/detallePedidos/crear">
                <input type="submit" value="Crear" class="btn btn-primary mb-3">
            </form>



            <%
                List<DetallePedido> listaDetalles = (List<DetallePedido>) request.getAttribute("listaDetalles");
                if (listaDetalles != null && !listaDetalles.isEmpty()) {
            %>

            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Id Detalle</th>
                    <th>Id Pedido</th>
                    <th>Fecha Pedido</th>
                    <th>Usuario</th>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Precio Unitario</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (DetallePedido detalle : listaDetalles) {
                %>
                <tr>
                    <td><%= detalle.getId_detalle() %></td>
                    <td><%= detalle.getId_pedido() %></td>
                    <td><%= detalle.getFechaPedido() %></td>
                    <td><%= detalle.getNombreProducto() %></td>
                    <td><%= detalle.getNombreProducto() %></td>
                    <td><%= detalle.getCantidad() %></td>
                    <td><%= detalle.getPrecioUnidad() %></td>
                    <td>
                        <!-- Editar -->
                        <form action="${pageContext.request.contextPath}/tienda/detallePedidos/editar/<%= detalle.getId_detalle() %>" method="get" style="display:inline;">
                            <input type="submit" value="Editar" class="btn btn-warning mb-3">
                        </form>

                        <!-- Eliminar -->
                        <form action="${pageContext.request.contextPath}/tienda/detallePedidos/<%= detalle.getId_detalle() %>" method="post" style="display:inline;">
                            <input type="hidden" name="__method__" value="delete"/>
                            <input type="hidden" name="codigo" value="<%= detalle.getId_detalle() %>"/>
                            <input type="submit" value="Eliminar" class="btn btn-danger mb-3">
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <% } else { %>
            <p>No hay registros de pedidos</p>
            <% } %>
        </div>
    </section>
</main>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>
