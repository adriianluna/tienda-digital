<%@ page import="org.iesbelen.model.Pedido" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 23/11/2025
  Time: 16:16
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
    <form action="${pageContext.request.contextPath}/tienda/pedidos/editar/<%= ((Pedido)request.getAttribute("pedido")).getId_pedido() %>" method="post">
        <input type="hidden" name="__method__" value="put" />

        <div class="clearfix">
            <div style="float: left; width: 50%">
                <h1>Editar pedido</h1>
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
            Pedido pedido = (Pedido) request.getAttribute("pedido");
            if (pedido != null) {
        %>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>ID</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="id_pedido" value="<%= pedido.getId_pedido() %>" readonly="readonly" />
            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Estado</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <div>
                    <select name="estado" id="estado">
                        <option value="">-- Selecciona un estado --</option>

                        <option value="entregado" <%= "entregado".equals(pedido.getEstado()) ? "selected" : "" %>>Entregado</option>
                        <option value="pendiente" <%= "pendiente".equals(pedido.getEstado()) ? "selected" : "" %>>Pendiente</option>
                        <option value="enviado" <%= "enviado".equals(pedido.getEstado()) ? "selected" : "" %>>Enviado</option>
                        <option value="cancelado" <%= "cancelado".equals(pedido.getEstado()) ? "selected" : "" %>>Cancelado</option>
                    </select>
                </div>
            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Total</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="total" value="<%= pedido.getTotal() %>" />
            </div>
        </div>



        <% 	} else { %>
        <%
            response.sendRedirect(request.getContextPath() + "/tienda/pedidos");
        %>
        <% } %>
    </form>

</div>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>
