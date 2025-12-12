
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
    <form action="${pageContext.request.contextPath}/tienda/usuarios/editar/<%= ((Usuario)request.getAttribute("usuario")).getId_usuario() %>" method="post">
        <input type="hidden" name="__method__" value="put" />

        <div class="clearfix">
            <div style="float: left; width: 50%">
                <h1>Editar Usuario</h1>
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
            Usuario usu = (Usuario) request.getAttribute("usuario");
            if (usu != null) {
        %>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>ID</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="id_usuario" value="<%= usu.getId_usuario() %>" readonly="readonly"/>
            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Nombre</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="nombre" value="<%= usu.getNombre() %>" />
            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Email</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="email" value="<%= usu.getEmail() %>" />
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Password</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input type="password" name="password" value="<%= usu.getPassword() %>" />
            </div>
        </div>
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                <label>Rol</label>
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <div>
                    <select name="rol" id="rol">
                        <option value="">-- Selecciona una rol --</option>

                        <option value="admin" <%= "admin".equals(usu.getRol()) ? "selected" : "" %>>Administrador</option>
                        <option value="cliente" <%= "cliente".equals(usu.getRol()) ? "selected" : "" %>>Cliente</option>
                    </select>
                </div>
            </div>
        </div>
        <% 	} else { %>
        <%
            response.sendRedirect(request.getContextPath() + "/tienda/usuarios");
        %>
        <% } %>
    </form>

</div>

<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>

</body>
</html>
