<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 22/11/2025
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>


<main>
    <section>
        <%--<-- código de body del antiguo productos/productos.jsp -->--%>
        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
            <div class="clearfix">
                <div style="float: left; width: 50%">
                    <h1>Productos</h1>
                </div>
                <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

                    <div style="position: absolute; left: 39%; top : 39%;">

                        <form action="${pageContext.request.contextPath}/tienda/productos/crear">
                            <input type="submit" value="Crear">
                        </form>
                    </div>

                </div>
            </div>


            <div class="clearfix">
                <hr/>
            </div>
            <div class="clearfix">
                <div style="float: left;width: 16%">Código</div>
                <div style="float: left;width: 16%">Nombre</div>
                <div style="float: left;width: 16%">Precio</div>
                <div style="float: left;width: 16%;overflow: hidden;">Stock</div>

            </div>
            <div class="clearfix">
                <hr/>
            </div>
            <%
                if (request.getAttribute("listaProducto") != null) {
                    List<Producto> listaProducto = (List<Producto>)request.getAttribute("listaProducto");

                    for (Producto producto : listaProducto) {
            %>

            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 10%"><%= producto.getId_producto()%></div>
                <div style="float: left;width: 30%"><%= producto.getNombre()%></div>
                <div style="float: left;width: 20%"><%= producto.getPrecio()%></div>
                <div style="float: left;width: 20%"><%= producto.getStock()%></div>
                <%--<div style="float: left;width: 20%"><%= producto.getTalla()%></div>
                <div style="float: left;width: 20%"><%= producto.getColor()%></div>--%>
                <div style="float: none;width: auto;overflow: hidden;">
                    <form action="${pageContext.request.contextPath}/tienda/productos/<%= producto.getId_producto()%>" style="display: inline;">
                        <input type="submit" value="Ver Detalle" />
                    </form>
                    <form action="${pageContext.request.contextPath}/tienda/productos/editar/<%= producto.getId_producto()%>" style="display: inline;">
                        <input type="submit" value="Editar" />
                    </form>
                <div style="float: none;width: auto;overflow: hidden;">
                    <form action="${pageContext.request.contextPath}/tienda/productos/borrar/" method="post" style="display: inline;">
                        <input type="hidden" name="__method__" value="delete"/>
                        <input type="hidden" name="codigo" value="<%= producto.getId_producto()%>"/>
                        <input type="submit" value="Eliminar" />
                    </form>
                </div>
            </div>
            <%
                }
            } else {
            %>
            No hay registros de producto
            <% } %>
        </div>
    </section>
</main>
<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>

</body>
</html>
