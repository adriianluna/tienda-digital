<%@ page import="org.iesbelen.model.Categoria" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: adria
  Date: 22/11/2025
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <title>Crear product</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath()%>/style.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/fragmentos/header.jspf" %>
<%@ include file="/WEB-INF/jsp/fragmentos/nav.jspf" %>
<div id="contenedora" style="float:none; margin: 0 auto;width: 900px;" >
<form action="${pageContext.request.contextPath}/tienda/productos/crear/" method="post">
    <div class="clearfix">
        <div style="float: left; width: 50%">
            <h1>Crear Producto</h1>
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
            Nombre
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input name="nombre" />
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            Descripcion
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input name="descripcion" />
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            Precio
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input name="precio" type="number"/>
        </div>
    </div>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            Stock
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <input name="stock" type="number"/>
        </div>
    </div>



    <%--ID CATEGODIRA--%>
    <div style="margin-top: 6px;" class="clearfix">
        <div style="float: left;width: 50%">
            Id_categoria
        </div>
        <div style="float: none;width: auto;overflow: hidden;">
            <div>
                <select name="categoria" id="categoria">
                    <option value="">-- Selecciona una categorias --</option>
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

                <%


                %>
            </div>
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
            Colores
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
   <%--<div style="float: none;width: auto;overflow: hidden;">
        <input name="id_categoria" />
    </div>--%>

</form>
</div>
<%@ include file ="/WEB-INF/jsp/fragmentos/footer.jspf"%>

</body>
</html>
