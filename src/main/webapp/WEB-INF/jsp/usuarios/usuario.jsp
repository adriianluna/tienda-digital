<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>

<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<div class="container my-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Usuarios</h2>
        <a href="${pageContext.request.contextPath}/tienda/usuarios/crear" class="btn btn-success">
            Crear Usuario
        </a>
    </div>

    <%
        List<Usuario> lista = (List<Usuario>) request.getAttribute("listaUsuario");
        if (lista != null && !lista.isEmpty()) {
    %>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Rol</th>
            <th style="width: 180px;">Acciones</th>
        </tr>
        </thead>

        <tbody>
        <% for (Usuario u : lista) { %>
        <tr>
            <td><%= u.getId_usuario() %></td>
            <td><%= u.getNombre() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRol() %></td>

            <td>
                <div class="d-flex gap-2">

                    <a href="${pageContext.request.contextPath}/tienda/usuarios/editar/<%= u.getId_usuario() %>"
                       class="btn btn-warning btn-sm">
                        Editar
                    </a>

                    <form action="${pageContext.request.contextPath}/tienda/usuarios/borrar/"
                          method="post"
                          onsubmit="return confirm('¿Seguro que quieres eliminar este usuario?');">
                        <input type="hidden" name="__method__" value="delete">
                        <input type="hidden" name="codigo" value="<%= u.getId_usuario() %>">
                        <button class="btn btn-danger btn-sm">Eliminar</button>
                    </form>

                </div>
            </td>
        </tr>
        <% } %>
        </tbody>

    </table>

    <% } else { %>

    <div class="alert alert-info text-center">
        No hay usuarios registrados.
    </div>

    <% } %>

</div>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>

</body>
</html>
