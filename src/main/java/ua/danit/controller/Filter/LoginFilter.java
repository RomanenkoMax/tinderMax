package ua.danit.controller.Filter;

import ua.danit.dao.UserDAOtoDB;
import ua.danit.utils.GetLoginFromCookie;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        GetLoginFromCookie getLoginFromCookie = new GetLoginFromCookie();
        Cookie[] cookies = req.getCookies();
        UserDAOtoDB userDAOtoDB = new UserDAOtoDB();

        if (cookies != null){

            if (userDAOtoDB.get(getLoginFromCookie.getLogin(cookies, "login")) == null){
                resp.sendRedirect("/login");
            } else {
                chain.doFilter(req, resp);
            }

        } else {
            resp.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

    }
}
