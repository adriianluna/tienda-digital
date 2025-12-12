package org.iesbelen.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.iesbelen.model.Usuario;

import java.io.IOException;

@WebFilter("/tienda/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String pathInfo = httpRequest.getPathInfo();
        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario-logado") : null;

        boolean isPublicRoute = pathInfo != null &&
                (pathInfo.contains("/login") ||
                        pathInfo.contains("/logout") ||
                        pathInfo.contains("/crear"));

        if (isPublicRoute || usuario != null) {
            chain.doFilter(request, response);
        } else {
            // No está logueado, redirigir al login
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/tienda/usuarios/login");
        }
    }
}