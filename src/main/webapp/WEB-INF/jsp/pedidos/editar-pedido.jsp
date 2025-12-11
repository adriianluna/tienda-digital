<%@ page import="org.iesbelen.model.Pedido" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Editar Pedido</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <h1 class="mb-4">Editar Pedido</h1>

    <%
        Pedido pedido = (Pedido) request.getAttribute("pedido");
        if (pedido != null) {
    %>

    <form action="${pageContext.request.contextPath}/tienda/pedidos/editar/" method="post">
        <input type="hidden" name="__method__" value="put" />

        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">ID</label>
            <div class="col-sm-6">
                <input type="text" name="id_pedido" class="form-control" value="<%= pedido.getId_pedido() %>" readonly/>
            </div>
        </div>

        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Estado</label>
            <div class="col-sm-6">
                <select name="estado" id="estado" class="form-select">
                    <option value="">-- Selecciona un estado --</option>
                    <option value="entregado" <%= "entregado".equals(pedido.getEstado()) ? "selected" : "" %>>Entregado</option>
                    <option value="pendiente" <%= "pendiente".equals(pedido.getEstado()) ? "selected" : "" %>>Pendiente</option>
                    <option value="enviado" <%= "enviado".equals(pedido.getEstado()) ? "selected" : "" %>>Enviado</option>
                    <option value="cancelado" <%= "cancelado".equals(pedido.getEstado()) ? "selected" : "" %>>Cancelado</option>
                </select>
            </div>
        </div>

        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Total</label>
            <div class="col-sm-6">
                <input type="number" name="total" class="form-control" value="<%= pedido.getTotal() %>" readonly/>
            </div>
        </div>

        <div class="mt-4">
            <button type="submit" class="btn btn-success">Guardar</button>
            <a href="${pageContext.request.contextPath}/tienda/pedidos" class="btn btn-secondary ms-2">Cancelar</a>
        </div>
    </form>

    <%
        } else {
            response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
        }
    %>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
