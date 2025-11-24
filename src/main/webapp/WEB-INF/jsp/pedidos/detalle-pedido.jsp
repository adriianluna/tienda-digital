<%@ page import="org.iesbelen.model.Pedido" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 24/11/2025
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Detalle Pedido</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
    <div class="clearfix">
        <div style="float: left; width: 50%">
            <h1>Detalles Pedidos(Mostrar toda la informacion)</h1>
        </div>
        <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

            <div style="position: absolute; left: 39%; top : 39%;">

                <form action="${pageContext.request.contextPath}/tienda/pedidos" >
                    <input type="submit" value="Volver" />
                </form>
            </div>

        </div>
    </div>

    <div class="clearfix">
        <hr/>
    </div>

    <% 	Pedido pedido = (Pedido) request.getAttribute("pedido");
        if (pedido != null) {
    %>

    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Código</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= pedido.getId_pedido() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Id_usuario</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= pedido.getId_usuario() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Fecha</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= pedido.getFecha() %>" readonly="readonly"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            <label>Estado</label>
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input value="<%= pedido.getEstado() %>" readonly="readonly"/>
        </div>
    </div><div style="margin-top: 6px;" class="clearfix">
    <div style="float: left;width: 50%">
        <label>Total</label>
    </div>
    <div style="float: none;width: auto;overflow: hidden;">
        <input value="<%= pedido.getTotal() %>" readonly="readonly"/>
    </div>
</div>
    <% 	} else { %>

    response.sendRedirect("pedidos/");

    <% 	} %>

</div>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>

</body>
</html>
