<%@ page import="org.iesbelen.model.Pedido" %>
<%@ page import="org.iesbelen.model.DetallePedido" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Detalle del Pedido</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <%
        Pedido pedido = (Pedido) request.getAttribute("pedido");
        List<DetallePedido> listaDetalles = (List<DetallePedido>) request.getAttribute("listaDetalles");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    %>

    <div class="alert alert-success text-center">
        <h2>¡Pedido realizado con éxito!</h2>
        <p>Tu pedido ha sido registrado.</p>
    </div>

    <div class="card mb-4">
        <div class="card-header">
            <h4>Información del Pedido</h4>
        </div>
        <div class="card-body">
            <p><strong>Codigo de pedido:</strong> <%= pedido.getId_pedido() %></p>
            <p><strong>Fecha:</strong> <%= pedido.getFecha().format(formatter) %></p>
            <p><strong>Estado:</strong>
                <span class="badge bg-warning"><%= pedido.getEstado() %></span>
            </p>
            <p><strong>Total:</strong> € <%= String.format("%.2f", pedido.getTotal()) %></p>
        </div>
    </div>

    <h4>Productos del Pedido</h4>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Producto</th>
            <th>Precio unitario</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
        </thead>
        <tbody>
        <% for (DetallePedido detalle : listaDetalles) { %>
        <tr>
            <td><%= detalle.getNombreProducto() %></td>
            <td>€ <%= String.format("%.2f", detalle.getPrecio_unitario()) %></td>
            <td><%= detalle.getCantidad() %></td>
            <td>€ <%= String.format("%.2f", detalle.getSubtotal()) %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="text-center mt-4">
        <a href="<%= request.getContextPath() %>/tienda/pedidos" class="btn btn-primary">Ver todos mis pedidos</a>
        <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Volver a la tienda</a>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>