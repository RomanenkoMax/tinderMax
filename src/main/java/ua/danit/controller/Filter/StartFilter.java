package ua.danit.controller.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.sendRedirect("/login");

    }

    @Override
    public void destroy() {

    }
}
