<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Usuario</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;">

    <% if (request.getAttribute("error") != null) { %>
    <div style="color: red; background-color: #ffe6e6; padding: 10px; margin: 10px 0;">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <!-- IMPORTANTE: La acción debe ser /tienda/usuarios SIN /crear -->
    <form action="${pageContext.request.contextPath}/tienda/usuarios/crear" method="post">

        <div class="clearfix">
            <div style="float: left; width: 50%">
                <h1>Crear Usuario</h1>
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

        <!-- IMPORTANTE: name="nombre" (no "usuario") -->
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Nombre de usuario
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="nombre" required />
            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Email
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="email" type="email" required />
            </div>
        </div>

        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Contraseña
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <input name="password" type="password" required />
            </div>
        </div>

        <!-- El rol es opcional, si no se pone será "cliente" por defecto -->
        <div style="margin-top: 6px;" class="clearfix">
            <div style="float: left;width: 50%">
                Rol
            </div>
            <div style="float: none;width: auto;overflow: hidden;">
                <select name="rol">
                    <option value="cliente">Cliente</option>
                    <option value="admin">Administrador</option>
                </select>
            </div>
        </div>


        <div style="margin-top: 20px;">
            <p>¿Ya tienes cuenta?
                <a href="${pageContext.request.contextPath}/tienda/usuarios/login">Inicia sesión aquí</a>
            </p>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>