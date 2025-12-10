<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.dao.UsuarioDAO" %>
<%@ page import="org.iesbelen.dao.UsuarioDAOImpl" %>
<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Crear Pedidos</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <h1 class="mb-4">Crear Pedido</h1>

    <form action="${pageContext.request.contextPath}/tienda/pedidos/crear/" method="post">
        <div class="mb-3 row">
            <label for="id_usuario" class="col-sm-2 col-form-label">Usuario</label>
            <div class="col-sm-6">
                <select name="id_usuario" id="id_usuario" class="form-select" required>
                    <option value="">-- Selecciona un usuario --</option>
                    <%
                        List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
                        if (listaUsuarios != null) {
                            for (Usuario u : listaUsuarios) {
                    %>
                    <option value="<%= u.getId_usuario() %>"><%= u.getNombre() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="fecha" class="col-sm-2 col-form-label">Fecha</label>
            <div class="col-sm-6">
                <input name="fecha" type="date" id="fecha" class="form-control" required/>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="estado" class="col-sm-2 col-form-label">Estado</label>
            <div class="col-sm-6">
                <select name="estado" id="estado" class="form-select" required>
                    <option value="">-- Selecciona un estado --</option>
                    <option value="pendiente">Pendiente</option>
                    <option value="enviado">Enviado</option>
                    <option value="entregado">Entregado</option>
                    <option value="cancelado">Cancelado</option>
                </select>
            </div>
        </div>

        <div class="mb-3 row">
            <label for="total" class="col-sm-2 col-form-label">Total</label>
            <div class="col-sm-6">
                <input name="total" type="number" id="total" class="form-control" min="0" step="0.01" required/>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-sm-8">
                <button type="submit" class="btn btn-success w-100">Crear Pedido</button>
            </div>
        </div>
    </form>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
