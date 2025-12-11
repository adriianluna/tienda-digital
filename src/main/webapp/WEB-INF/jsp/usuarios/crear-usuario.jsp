<%@ page import="org.iesbelen.model.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Crear Usuario</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8">

            <h1 class="mb-4">Crear Usuario</h1>

            <!-- Mostrar error si existe -->
            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <%= request.getAttribute("error") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
            <% } %>

            <form action="${pageContext.request.contextPath}/tienda/usuarios" method="post">

                <!-- Nombre de usuario -->
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre de usuario</label>
                    <input type="text"
                           class="form-control"
                           id="nombre"
                           name="nombre"
                           value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>"
                           required>
                </div>

                <!-- Email -->
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email"
                           class="form-control"
                           id="email"
                           name="email"
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                           required>
                </div>

                <!-- Contraseña -->
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password"
                           class="form-control"
                           id="password"
                           name="password"
                           minlength="4"
                           required>
                    <div class="form-text">Mínimo 4 caracteres</div>
                </div>

                <!-- Rol (solo visible para admins) -->
                <%
                    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario-logado");
                     esAdmin = usuarioLogado != null && "administrador".equalsIgnoreCase(usuarioLogado.getRol());
                %>

                <% if (esAdmin) { %>
                <div class="mb-3">
                    <label for="rol" class="form-label">Rol</label>
                    <select name="rol" id="rol" class="form-select">
                        <option value="cliente">Cliente</option>
                        <option value="administrador">Administrador</option>
                    </select>
                </div>
                <% } else { %>
                <!-- Usuario público siempre será cliente -->
                <input type="hidden" name="rol" value="cliente">
                <% } %>

                <!-- Botón Crear -->
                <div class="d-grid mb-3">
                    <button type="submit" class="btn btn-success">Crear Usuario</button>
                </div>

                <!-- Link a login -->
                <div class="text-center">
                    <p>¿Ya tienes cuenta?
                        <a href="${pageContext.request.contextPath}/tienda/usuarios/login">Inicia sesión aquí</a>
                    </p>
                </div>

            </form>

        </div>
    </div>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>