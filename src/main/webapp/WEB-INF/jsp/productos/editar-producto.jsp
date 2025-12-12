<%@ page import="org.iesbelen.model.Producto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Editar Producto</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <h1 class="mb-4">Editar Producto</h1>

    <form action="${pageContext.request.contextPath}/tienda/productos/editar/<%= ((Producto)request.getAttribute("producto")).getId_producto() %>" method="post" class="row g-3">
        <input type="hidden" name="__method__" value="put" />

        <%
            Producto prod = (Producto) request.getAttribute("producto");
            if (prod != null) {
        %>

        <div class="col-md-6">
            <label class="form-label">ID</label>
            <input type="text" class="form-control" name="id_producto" value="<%= prod.getId_producto() %>" readonly>
        </div>

        <div class="col-md-6">
            <label class="form-label">Nombre</label>
            <input type="text" class="form-control" name="nombre" value="<%= prod.getNombre() %>">
        </div>

        <div class="col-md-6">
            <label class="form-label">Descripción</label>
            <input type="text" class="form-control" name="descripcion" value="<%= prod.getDescripcion() %>">
        </div>

        <div class="col-md-3">
            <label class="form-label">Precio</label>
            <input type="number" class="form-control" name="precio" value="<%= prod.getPrecio() %>">
        </div>

        <div class="col-md-3">
            <label class="form-label">Stock</label>
            <input type="number" class="form-control" name="stock" value="<%= prod.getStock() %>">
        </div>

        <div class="col-md-6">
            <label class="form-label">Categoría (ID)</label>
            <input type="text" class="form-control" name="categoria" value="<%= prod.getId_categoria() %>" readonly>
        </div>

        <div class="col-md-3">
            <label class="form-label">Talla</label>
            <select name="talla" class="form-select">
                <option value="" <%= (prod.getTalla() == null || prod.getTalla().isEmpty()) ? "selected" : "" %>>--Selecciona una talla--</option>
                <option value="s" <%= "s".equals(prod.getTalla()) ? "selected" : "" %>>S</option>
                <option value="m" <%= "m".equals(prod.getTalla()) ? "selected" : "" %>>M</option>
                <option value="xl" <%= "xl".equals(prod.getTalla()) ? "selected" : "" %>>XL</option>
            </select>

        </div>

        <div class="col-md-3">
            <label class="form-label">Color</label>
            <select name="color" class="form-select">
                <option value="" <%= (prod.getColor() == null || prod.getColor().isEmpty()) ? "selected" : "" %>>--Selecciona un color--</option>
                <option value="rojo" <%= "rojo".equals(prod.getColor()) ? "selected" : "" %>>Rojo</option>
                <option value="azul" <%= "azul".equals(prod.getColor()) ? "selected" : "" %>>Azul</option>
                <option value="verde" <%= "verde".equals(prod.getColor()) ? "selected" : "" %>>Verde</option>
            </select>

        </div>
        <div class="col-md-6">
            <label class="form-label">Url imagen</label>
            <input type="text" class="form-control" name="imagen" value="<%= prod.getImagen() %>">
        </div>
        <div class="col-12 mt-3">
            <button type="submit" class="btn btn-success">Guardar Cambios</button>
            <a href="${pageContext.request.contextPath}/tienda/productos" class="btn btn-secondary ms-2">Cancelar</a>
        </div>

        <%
            } else {
                response.sendRedirect(request.getContextPath() + "/tienda/productos");
            }
        %>
    </form>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>
