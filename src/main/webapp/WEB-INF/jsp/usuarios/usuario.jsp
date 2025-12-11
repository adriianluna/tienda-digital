<%@ page import="java.util.List" %>
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
        <div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
            <div class="clearfix">
                <div style="float: left; width: 50%">
                    <h1>Usuarios</h1>
                </div>
                <div style="float: none;width: auto;overflow: hidden;min-height: 80px;position: relative;">

                    <div style="position: absolute; left: 39%; top : 39%;">

                        <form action="${pageContext.request.contextPath}/tienda/usuarios/crear">
                            <input type="submit" value="Crear">
                        </form>
                    </div>

                </div>
            </div>


            <div class="clearfix">
                <hr/>
            </div>
            <div class="clearfix">
                <div style="float: left;width: 10%">Código</div>
                <div style="float: left;width: 15%">Nombre</div>
                <div style="float: left;width: 20%">email</div>
                <div style="float: left;width: 19%">Rol</div>

            </div>
            <div class="clearfix">
                <hr/>
            </div>
                <%
                if (request.getAttribute("listaUsuario") != null) {
                    List<Usuario> listaUsuario = (List<Usuario>)request.getAttribute("listaUsuario");

                    for (Usuario usuario : listaUsuario) {
            %>

            <div style="margin-top: 6px;" class="clearfix">
                <div style="float: left;width: 10%"><%= usuario.getId_usuario()%></div>
                <div style="float: left;width: 20%"><%= usuario.getNombre()%></div>
                <div style="float: left;width: 40%"><%= usuario.getEmail()%></div>
                <div style="float: left;width: 10%"><%= usuario.getRol()%></div>

                <div style="float: none;width: auto;overflow: hidden;">
                    <%--<form action="${pageContext.request.contextPath}/tienda/productos/<%= usuario.getId_usuario()%>" style="display: inline;">
                        <input type="submit" value="Ver Detalle" />
                    </form>--%>
                    <form action="${pageContext.request.contextPath}/tienda/usuarios/editar/<%= usuario.getId_usuario()%>" style="display: inline;">
                        <input type="submit" value="Editar" />
                    </form>
                    <div style="float: none;width: auto;overflow: hidden;">
                        <form action="${pageContext.request.contextPath}/tienda/usuarios/borrar/" method="post" style="display: inline;">
                            <input type="hidden" name="__method__" value="delete"/>
                            <input type="hidden" name="codigo" value="<%= usuario.getId_usuario()%>"/>
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
