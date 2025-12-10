<%@ page import="org.iesbelen.model.Pedido" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Detalle Pedido</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <h1 class="mb-4">Detalle del Pedido</h1>

    <%
        Pedido pedido = (Pedido) request.getAttribute("pedido");
        if (pedido != null) {
    %>

    <form>
        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Código</label>
            <div class="col-sm-6">
                <input type="text" readonly class="form-control" value="<%= pedido.getId_pedido() %>"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">ID Usuario</label>
            <div class="col-sm-6">
                <input type="text" readonly class="form-control" value="<%= pedido.getId_usuario() %>"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Fecha</label>
            <div class="col-sm-6">
                <input type="text" readonly class="form-control" value="<%= pedido.getFecha() %>"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Estado</label>
            <div class="col-sm-6">
                <input type="text" readonly class="form-control" value="<%= pedido.getEstado() %>"/>
            </div>
        </div>

        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label">Total</label>
            <div class="col-sm-6">
                <input type="text" readonly class="form-control" value="<%= pedido.getTotal() %>"/>
            </div>
        </div>

        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/tienda/pedidos" class="btn btn-primary">Volver</a>
        </div>
    </form>

    <%
        } else {
            response.sendRedirect("pedidos/");
        }
    %>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
