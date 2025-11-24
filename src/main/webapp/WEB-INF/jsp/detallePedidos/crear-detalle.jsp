<%@ page import="org.iesbelen.model.DetallePedido" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="org.iesbelen.model.Pedido" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 24/11/2025
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Crear detalle</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<div class="container mt-4">
    <h1>Crear Detalle de Pedido</h1>

    <form action="${pageContext.request.contextPath}/tienda/detallePedidos/crear/" method="post">

        <!-- Seleccionar pedido -->
        <div class="mb-3">
            <label for="idPedido" class="form-label">Pedido</label>
            <select name="idPedido" id="idPedido" class="form-select" required>
                <option value="">-- Selecciona un pedido --</option>
                <%
                    List<Pedido> listaPedidos = (List<Pedido>) request.getAttribute("listaPedidos");
                    if (listaPedidos != null) {
                        for (Pedido pedido : listaPedidos) {
                %>
                <option value="<%= pedido.getId_pedido() %>">
                    <%= pedido.getId_pedido() %> - <%= pedido.getEstado() %> - <%= pedido.getTotal() %>€
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <!-- Seleccionar producto -->
        <div class="mb-3">
            <label for="idProducto" class="form-label">Producto</label>
            <select name="idProducto" id="idProducto" class="form-select" required>
                <option value="">-- Selecciona un producto --</option>
                <%
                    List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaProductos");
                    if (listaProductos != null) {
                        for (Producto producto : listaProductos) {
                %>
                <option value="<%= producto.getId_producto() %>">
                    <%= producto.getNombre() %> - <%= producto.getPrecio() %>€
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <!-- Cantidad -->
        <div class="mb-3">
            <label for="cantidad" class="form-label">Cantidad</label>
            <input type="number" name="cantidad" id="cantidad" class="form-control" min="1" required>
        </div>

        <!-- Precio unitario -->
        <div class="mb-3">
            <label for="precioUnidad" class="form-label">Precio Unitario</label>
            <input type="number" step="0.01" name="precioUnidad" id="precioUnidad" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-primary">Crear Detalle</button>
    </form>
</div>
<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>
