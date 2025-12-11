<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.model.Producto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Tienda</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">

</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<h1 class="text-center my-4">Productos disponibles</h1>

<div class="container">
    <div class="row">
        <%
            if (request.getAttribute("listaProducto") != null) {
                List<Producto> listaProducto = (List<Producto>) request.getAttribute("listaProducto");
                for (Producto prod : listaProducto) {
        %>

            <div class="card h-100">
                <img src="<%= prod.getImagen() %>"
                     class="card-img-top imagen-producto">
                <div class="card-body">
                    <h5 class="card-title"><%= prod.getNombre() %></h5>
                    <p class="card-text">Precio: <%= prod.getPrecio() %>€ </p>
                    <p class="card-text">Descripción: <%= prod.getDescripcion() %></p>
                    <p class="card-text">Talla: <%= prod.getTalla() %> </p>
                    <p class="card-text">Color: <%= prod.getColor() %> </p>

                    <!-- Formulario para comprar -->
                    <form action="<%= request.getContextPath() %>/tienda/carrito/anadir" method="post">
                        <input type="hidden" name="idProducto" value="<%= prod.getId_producto() %>"/>
                        <input type="number" name="cantidad" value="1" min="1" class="form-control mb-2" style="width:80px;">
                        <button type="submit" class="btn btn-primary w-100">Comprar</button>
                    </form>
                </div>
            </div>

        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
