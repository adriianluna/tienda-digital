<%@ page import="org.iesbelen.model.Producto" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 22/11/2025
  Time: 12:23
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
    <form action="${pageContext.request.contextPath}/tienda/productos/editar/<%= ((Producto)request.getAttribute("producto")).getId_producto() %>" method="post">
        <input type="hidden" name="__method__" value="put" />

        <div class="clearfix">
            <div style="float: left; width: 50%">
                <h1>Editar Producto</h1>
            </div>
            <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">
                <div style="position: absolute; left: 39%; top: 39%;">
                    <input type="submit" value="Guardar" />
                </div>
            </div>
        </div>

        <div class="clearfix">
            <hr/>
        </div>

        <%
            Producto prod = (Producto) request.getAttribute("producto");
            if (prod != null) {
        %>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>ID</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="id_producto" value="<%= prod.getId_producto() %>" readonly="readonly"/>
            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Nombre</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="nombre" value="<%= prod.getNombre() %>" />
            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Descripcion</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input  name="descripcion" value="<%= prod.getDescripcion() %>" />
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Precio</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input type="number" name="precio" value="<%= prod.getPrecio() %>" />
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>stock</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input type="number" name="stock" value="<%= prod.getStock() %>" />
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Categoria</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input  name="categoria" value="<%= prod.getId_categoria() %>" readonly="readonly"/>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Talla
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <select name="talla" id="talla">

                    <option value="">--Selecciona una talla</option>
                    <option value="s">S</option>
                    <option value="m">M</option>
                    <option value="xl">XL</option>
                </select>

            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Color</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <select name="color" id="color">

                    <option value="">--Selecciona un color</option>
                    <option value="rojo">Rojo</option>
                    <option value="azul">Azul</option>
                    <option value="verde">Verde</option>
                </select>

            </div>
        </div>
        <% 	} else { %>
        <%
            response.sendRedirect(request.getContextPath() + "/tienda/productos");
        %>
        <% } %>
    </form>

</div>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>
