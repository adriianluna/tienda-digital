<%@ page import="org.iesbelen.model.Pedido" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.dao.UsuarioDAO" %>
<%@ page import="org.iesbelen.dao.UsuarioDAOImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Pedidos</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1>Pedidos</h1>
        <a href="${pageContext.request.contextPath}/tienda/pedidos/crear" class="btn btn-primary">Crear Pedido</a>
    </div>

    <%
        List<Pedido> listaPedido = (List<Pedido>) request.getAttribute("listaPedido");
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        List<Usuario> usuarios = usuarioDAO.getAll();
    %>

    <%
        if (listaPedido != null && !listaPedido.isEmpty()) {
    %>

    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead class="table-dark">
            <tr>
                <th>ID Pedido</th>
                <th>Nombre Usuario</th>
                <th>Estado</th>
                <th>Total</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Pedido pedido : listaPedido) {
                    String nombreUsuario = "";
                    for (Usuario u : usuarios) {
                        if (u.getId_usuario() == pedido.getId_usuario()) {
                            nombreUsuario = u.getNombre();
                            break;
                        }
                    }
            %>
            <tr>
                <td><%= pedido.getId_pedido() %></td>
                <td><%= nombreUsuario + " (" + pedido.getId_usuario() + ")" %></td>
                <td><%= pedido.getEstado() %></td>
                <td><%= pedido.getTotal() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/tienda/pedidos/<%= pedido.getId_pedido() %>" class="btn btn-info btn-sm me-1">Ver</a>
                    <a href="${pageContext.request.contextPath}/tienda/pedidos/editar/<%= pedido.getId_pedido() %>" class="btn btn-warning btn-sm me-1">Editar</a>
                    <form action="${pageContext.request.contextPath}/tienda/pedidos/borrar/" method="post" class="d-inline">
                        <input type="hidden" name="__method__" value="delete"/>
                        <input type="hidden" name="codigo" value="<%= pedido.getId_pedido() %>"/>
                        <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <%
    } else {
    %>
    <div class="alert alert-info">No hay registros de pedidos</div>
    <%
        }
    %>
</main>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>
