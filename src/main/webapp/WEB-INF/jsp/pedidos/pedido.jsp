<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Pedido" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Mis Pedidos</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">

</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <h1 class="text-center mb-4">Mis Pedidos</h1>

    <%
        List<Pedido> listaPedidos = (List<Pedido>) request.getAttribute("listaPedidos");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    %>

    <% if (listaPedidos != null && !listaPedidos.isEmpty()) { %>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID Pedido</th>
            <th>Fecha</th>
            <th>Estado</th>
            <th>Total</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody>
        <% for (Pedido pedido : listaPedidos) { %>
        <tr>
            <td><%= pedido.getId_pedido() %></td>
            <td><%= pedido.getFecha().format(formatter) %></td>
            <td>
                <span class="badge
                    <%= pedido.getEstado().equals("pendiente") ? "bg-warning" :
                        pedido.getEstado().equals("enviado") ? "bg-info" : "bg-success" %>">
                    <%= pedido.getEstado() %>
                </span>
            </td>
            <td>€ <%= String.format("%.2f", pedido.getTotal()) %></td>
            <td>
                <%Usuario usuarioLogado = (Usuario) session.getAttribute("usuario-logado");%>
                <form action="${pageContext.request.contextPath}/tienda/pedidos/<%= pedido.getId_pedido()%>" >
                    <button class="btn btn-sm btn-primary" type="submit" >Ver detalles</button>
                </form>
                <% if ("admin".equalsIgnoreCase(usuarioLogado.getRol())) { %>
                <form action="${pageContext.request.contextPath}/tienda/pedidos/eliminar/<%= pedido.getId_pedido()%>" style="display: inline;">
                    <button class="btn btn-sm btn-danger" type="submit" >Eliminar</button>
                </form>
                <form action="${pageContext.request.contextPath}/tienda/pedidos/editar/<%= pedido.getId_pedido()%>" >
                    <button class="btn btn-sm btn-warning" type="submit" >Editar</button>
                </form>
                <% } %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p class="text-center">No tienes pedidos aún.</p>
    <% } %>

    <div class="text-center mt-4">
        <a href="<%= request.getContextPath() %>/" class="btn btn-secondary">Volver a la tienda</a>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>