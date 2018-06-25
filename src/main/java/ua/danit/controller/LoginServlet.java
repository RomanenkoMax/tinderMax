package ua.danit.controller;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ua.danit.dao.UserDAOtoDB;
import ua.danit.model.User;
import ua.danit.utils.TemplateConfig;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


public class LoginServlet extends HttpServlet {

    UserDAOtoDB userDAOtoDB;

    public LoginServlet(UserDAOtoDB userDAOtoDB) {
        this.userDAOtoDB = userDAOtoDB;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Object> model = new HashMap<>();

        Template template = new TemplateConfig().getConfig("login.html");

        Writer out = resp.getWriter();

        try {
            template.process(model, out);

        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userLogin = req.getParameter("login");
        String userPass = req.getParameter("password");

        User user = userDAOtoDB.get(userLogin);

        if (user.getPassword().equals(userPass) && userDAOtoDB.get(userLogin) != null){
            Cookie cookie = new Cookie("login", userLogin);
            cookie.setMaxAge(120);
            resp.addCookie(cookie);
        }

        resp.sendRedirect("/user");
    }
}
