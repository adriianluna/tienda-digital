<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Crear Producto</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>

<main class="container my-5">
    <h1 class="mb-4">Crear Producto</h1>
    <form action="${pageContext.request.contextPath}/tienda/productos/crear/" method="post" class="row g-3">

        <!-- Nombre -->
        <div class="col-12">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" name="nombre" id="nombre" required>
        </div>

        <!-- Descripción -->
        <div class="col-12">
            <label for="descripcion" class="form-label">Descripción</label>
            <input type="text" class="form-control" name="descripcion" id="descripcion">
        </div>

        <!-- Precio -->
        <div class="col-12">
            <label for="precio" class="form-label">Precio</label>
            <input type="number" class="form-control" name="precio" id="precio" min="0" step="0.01">
        </div>

        <!-- Stock -->
        <div class="col-12">
            <label for="stock" class="form-label">Stock</label>
            <input type="number" class="form-control" name="stock" id="stock" min="0">
        </div>

        <!-- Categoría -->
        <div class="col-12">
            <label for="categoria" class="form-label">Categoría</label>
            <select name="categoria" id="categoria" class="form-select">
                <option value="">-- Selecciona una categoría --</option>
                <%
                    List<Categoria> listaCat = (List<Categoria>) request.getAttribute("listaCategoria");
                    if (listaCat != null) {
                        for (Categoria cat : listaCat) {
                %>
                <option value="<%= cat.getId_categoria() %>"><%= cat.getNombre() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <!-- Talla -->
        <div class="col-12">
            <label for="talla" class="form-label">Talla</label>
            <select name="talla" id="talla" class="form-select">
                <option value="">-- Selecciona una talla --</option>
                <option value="s">S</option>
                <option value="m">M</option>
                <option value="xl">XL</option>
            </select>
        </div>

        <!-- Color -->
        <div class="col-12">
            <label for="color" class="form-label">Color</label>
            <select name="color" id="color" class="form-select">
                <option value="">-- Selecciona un color --</option>
                <option value="rojo">Rojo</option>
                <option value="azul">Azul</option>
                <option value="verde">Verde</option>
            </select>
        </div>

        <!-- Imagen -->
        <div class="col-12">
            <label for="imagen" class="form-label">URL imagen</label>
            <input type="text" class="form-control" name="imagen" id="imagen">
        </div>

        <!-- Botón Crear -->
        <div class="col-12">
            <button type="submit" class="btn btn-success">Crear Producto</button>
        </div>
    </form>
</main>

<%@ include file="/WEB-INF/jsp/fragmentos/footer.jspf"%>
</body>
</html>