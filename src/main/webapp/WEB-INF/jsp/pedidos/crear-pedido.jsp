<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.dao.UsuarioDAO" %>
<%@ page import="org.iesbelen.dao.UsuarioDAOImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Crear pediddos</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
    <form action="${pageContext.request.contextPath}/tienda/pedidos/crear/" method="post">
        <div class="clearfix">
            <div style="float: left; width: 50%">
                <h1>Crear Pedidos</h1>
            </div>
            <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

                <div style="position: absolute; left: 39%; top : 39%;">
                    <input type="submit" value="Crear"/>
                </div>

            </div>
        </div>
        <div class="clearfix">
            <hr/>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Id_usuario
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <select name="id_usuario" id="id_usuario">
                    <option value="">-- Selecciona un usuario --</option>
                    <%



                        List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
                        if (listaUsuarios != null) {
                            for (Usuario u : listaUsuarios) {
                    %>
                    <option value="<%= u.getId_usuario() %>"><%= u.getNombre() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Fecha
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="fecha" type="date"/>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Estado
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <select name="estado" id="estado">

                    <option value="">--Selecciona una talla</option>
                    <option value="pendiente">Pendiente</option>
                    <option value="enviado">Enviado</option>
                    <option value="entregado">Entregado</option>
                    <option value="cancelado">Cancelado</option>

                </select>
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Total
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="total" type="number"/>
            </div>
        </div>

    </form>
</div>
<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>
