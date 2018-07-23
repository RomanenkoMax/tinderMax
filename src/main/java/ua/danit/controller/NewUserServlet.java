package ua.danit.controller;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ua.danit.dao.UserDAOtoDB;
import ua.danit.model.User;
import ua.danit.utils.TemplateConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@WebServlet(urlPatterns = "/newUser")
public class NewUserServlet  extends HttpServlet{

    private UserDAOtoDB userDAOtoDB;

    public NewUserServlet(UserDAOtoDB userDAOtoDB) {
        this.userDAOtoDB = userDAOtoDB;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if ("logout".equals(req.getParameter("logout"))){

            Cookie[] cookies = req.getCookies();
            for (Cookie c : cookies) {
                c.setMaxAge(0);
            }
            userDAOtoDB.updateLike();
            resp.sendRedirect("/login");
        }


        Template template = new TemplateConfig().getConfig("loginNewUser.html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Writer out = resp.getWriter();

        try {

            template.process(new HashMap<>(), out);

        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userDAOtoDB.updateLike();

        Cookie[] cookie = req.getCookies();
        for (Cookie c : cookie) {
            c.setMaxAge(0);
        }

        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String photo = req.getParameter("photo");

        userDAOtoDB.put(new User(login, pass, photo));

        resp.sendRedirect("/login");

    }
}
