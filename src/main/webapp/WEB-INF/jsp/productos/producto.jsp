<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h1>Productos</h1>
        <form action="${pageContext.request.contextPath}/tienda/productos/crear">
            <button class="btn btn-success">Crear Producto</button>
        </form>
    </div>

    <!-- Buscador -->
    <form class="mb-3" action="${pageContext.request.contextPath}/tienda/productos" method="get">
        <div class="input-group">
            <input type="text" name="filtro" class="form-control" placeholder="Buscar producto por nombre..." value="<%= request.getParameter("filtro") != null ? request.getParameter("filtro") : "" %>">
            <button type="submit" class="btn btn-primary">Buscar</button>
        </div>
    </form>

    <%
        List<Producto> listaProducto = (List<Producto>) request.getAttribute("listaProducto");
        if (listaProducto != null && !listaProducto.isEmpty()) {
    %>

    <div class="table-responsive">
        <table class="table table-striped table-hover align-middle">
            <thead class="table-dark">
            <tr>
                <th scope="col">Código</th>
                <th scope="col">Nombre</th>
                <th scope="col">Descripción</th>
                <th scope="col" class="text-end">Precio</th>
                <th scope="col" class="text-end">Stock</th>
                <th scope="col">Categoría</th>
                <th scope="col">Talla</th>
                <th scope="col">Color</th>
                <th scope="col">Imagen</th>
                <th scope="col">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Producto producto : listaProducto) {
            %>
            <tr>
                <td><%= producto.getId_producto() %></td>
                <td><%= producto.getNombre() %></td>
                <td><%= producto.getDescripcion() %></td>
                <td class="text-end"><%= producto.getPrecio() %>€</td>
                <td class="text-end"><%= producto.getStock() %></td>
                <td><%= producto.getId_categoria() %></td>
                <td><%= producto.getTalla() %></td>
                <td><%= producto.getColor() %></td>
                <td><%= producto.getImagen() %></td>
                <td>
                    <div class="d-flex gap-1">
                        <form action="${pageContext.request.contextPath}/tienda/productos/<%= producto.getId_producto() %>" method="get">
                            <button class="btn btn-info btn-sm">Ver</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/tienda/productos/editar/<%= producto.getId_producto() %>" method="get">
                            <button class="btn btn-warning btn-sm">Editar</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/tienda/productos/borrar/" method="post" onsubmit="return confirm('¿Estás seguro de eliminar este producto?');">
                            <input type="hidden" name="__method__" value="delete"/>
                            <input type="hidden" name="codigo" value="<%= producto.getId_producto() %>"/>
                            <button class="btn btn-danger btn-sm">Eliminar</button>
                        </form>
                    </div>
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
    <div class="alert alert-info">No hay registros de productos.</div>
    <% } %>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf" %>
</body>
</html>
