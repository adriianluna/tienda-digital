<%@ page import="org.iesbelen.model.Producto" %>
<%@ page import="org.iesbelen.dao.ProductoDAO" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 22/11/2025
  Time: 12:22
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
<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
    <div class="clearfix">
        <div style="float: left; width: 50%">
            <h1>Detalles Productos(Mostrar toda la informacion)</h1>
        </div>
        <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

            <div style="position: absolute; left: 39%; top : 39%;">

                <form action="${pageContext.request.contextPath}/tienda/productos" >
                    <input type="submit" value="Volver" />
                </form>
            </div>

        </div>
    </div>

    <div class="clearfix">
        <hr/>
    </div>

    <% 	Producto prod = (Producto) request.getAttribute("producto");
        if (prod != null) {
    %>

    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Código</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= prod.getId_producto() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Nombre</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= prod.getNombre() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Descripcion</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= prod.getDescripcion() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Precio</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= prod.getPrecio() %>" readonly="readonly"/>
        </div>
    </div><div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
        <label>Stock</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
        <input value="<%= prod.getStock() %>" readonly="readonly"/>
    </div>
</div><div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
        <label>Id categoria</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
        <input value="<%= prod.getId_categoria() %>" readonly="readonly"/>
    </div>
</div>
    <div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
        <label>Talla</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
        <input value="<%= prod.getTalla() %>" readonly="readonly"/>
    </div>
</div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Color</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= prod.getColor() %>" readonly="readonly"/>
        </div>
    </div>
</div>
<div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
        <label>Url imagen</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
        <input value="<%= prod.getImagen() %>" readonly="readonly"/>
    </div>
</div>




    <% 	} else { %>

    response.sendRedirect("productos/");

    <% 	} %>

</div>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>

</body>
</html>
