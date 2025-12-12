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
                "/tienda/detallePedidos/*"
        },
        initParams = {
                @WebInitParam(name = "acceso-concedido-a-rol", value = "admin")
        })
public class UserFilter extends HttpFilter implements Filter {

    private String rolAcceso;

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String servletPath = httpRequest.getServletPath();
        String pathInfo = httpRequest.getPathInfo();
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario-logado") : null;

        if (servletPath.startsWith("/tienda/usuarios") &&
                ("/login".equals(pathInfo) ||
                        "/logout".equals(pathInfo) ||
                        "/crear".equals(pathInfo))) {

            chain.doFilter(request, response);
            return;
        }


        if (usuario == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/tienda/usuarios/login");
            return;
        }


        String rol = usuario.getRol();


        if ("admin".equalsIgnoreCase(rol)) {
            chain.doFilter(request, response);
            return;
        }


        if ("cliente".equalsIgnoreCase(rol)) {

            boolean puedeEntrarCliente =
                    servletPath.startsWith("/tienda/pedidos") ||
                            servletPath.startsWith("/tienda/detalle-pedidos");

            if (puedeEntrarCliente) {
                chain.doFilter(request, response);
                return;
            }

            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado");
            return;
        }

        httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso no permitido");
    }


}