package org.iesbelen.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.model.Usuario;

import java.io.IOException;

@WebFilter(
        urlPatterns = {
                "/tienda/usuarios/*",
                "/tienda/productos/*",
                "/tienda/pedidos/*",
                "/tienda/detallePedidos/*"
        },
        initParams = {
                @WebInitParam(name = "acceso-concedido-a-rol", value = "admin")  // ← CAMBIO AQUÍ
        })
public class UserFilter extends HttpFilter implements Filter {

    private String rolAcceso;

    @Override
    public void destroy() {
        // Cleanup si es necesario
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String pathInfo = httpRequest.getPathInfo();
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario-logado") : null;

        // Rutas públicas dentro de /usuarios: login, logout, crear
        boolean isPublicRoute = pathInfo != null &&
                (pathInfo.equals("/login") ||
                        pathInfo.equals("/logout") ||
                        pathInfo.equals("/crear"));

        // Si es ruta pública, dejar pasar
        if (isPublicRoute) {
            chain.doFilter(request, response);
            return;
        }

        // Si NO está logueado
        if (usuario == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/tienda/usuarios/login");
            return;
        }

        // Si es ADMINISTRADOR, acceso total
        if ("admin".equalsIgnoreCase(usuario.getRol())) {  // ← CAMBIO AQUÍ
            chain.doFilter(request, response);
        } else {
            // Si no es admin, denegar acceso
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado");
        }
    }

    }