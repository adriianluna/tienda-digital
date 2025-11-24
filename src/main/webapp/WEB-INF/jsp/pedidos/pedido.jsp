<%@ page import="org.iesbelen.model.Pedido" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesbelen.dao.UsuarioDAO" %>
<%@ page import="org.iesbelen.dao.UsuarioDAOImpl" %><%--
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

    <title>Pedios</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<main>
    <section>

        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
            <div class="clearfix">
                <div style="float: left; width: 50%">
                    <h1>Pedidos</h1>
                </div>
                <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

                    <div style="position: absolute; left: 39%; top : 39%;">

                        <form action="${pageContext.request.contextPath}/tienda/pedidos/crear">
                            <input type="submit" value="Crear">
                        </form>
                    </div>

                </div>
            </div>


            <div class="clearfix">
                <hr/>
            </div>
            <div class="clearfix">
                <div style="float: left;width: 16%">Código pedido</div>
                <div style="float: left;width: 16%">Nombre usuario</div>
                <div style="float: left;width: 16%">Estado</div>
                <div style="float: left;width: 16%">Total</div>


            </div>
            <div class="clearfix">
                <hr/>
            </div>
                <%
                if (request.getAttribute("listaPedido") != null) {
                    List<Pedido> listaPedido = (List<Pedido>)request.getAttribute("listaPedido");

                    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                    request.setAttribute("listaUsuarios", usuarioDAO.getAll());

                    for (Pedido pedido : listaPedido) {


                    //Para mostar el nombre de usuario
                    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
                    String nombreUsuario = "";

                    for (Usuario u : usuarios) {
                        if (u.getId_usuario() == pedido.getId_usuario()) {
                        nombreUsuario = u.getNombre();
                        break;
                        }
                    }
            %>

            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 10%"><%= pedido.getId_pedido()%></div>
                <div style="float: left;width: 30%"><%= nombreUsuario + "("+pedido.getId_usuario() + ")"%></div>
                <div style="float: left;width: 20%"><%= pedido.getEstado()%></div>
                <div style="float: left;width: 20%"><%= pedido.getTotal()%></div>
                <%--<div style="float: left;width: 20%"><%= producto.getTalla()%></div>
                <div style="float: left;width: 20%"><%= producto.getColor()%></div>--%>
                <div style="float: none;width: auto;overflow: hidden;">
                    <form action="${pageContext.request.contextPath}/tienda/pedidos/<%= pedido.getId_pedido()%>" style="display: inline;">
                        <input type="submit" value="Ver Detalle" />
                    </form>
                    <form action="${pageContext.request.contextPath}/tienda/pedidos/editar/<%= pedido.getId_pedido()%>" style="display: inline;">
                        <input type="submit" value="Editar" />
                    </form>
                    <div style="float: none;width: auto;overflow: hidden;">
                        <form action="${pageContext.request.contextPath}/tienda/pedidos/borrar/" method="post" style="display: inline;">
                            <input type="hidden" name="__method__" value="delete"/>
                            <input type="hidden" name="codigo" value="<%= pedido.getId_pedido()%>"/>
                            <input type="submit" value="Eliminar" />
                        </form>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                No hay registros de pedidos
                <% } %>
            </div>
    </section>
</main>
<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>
