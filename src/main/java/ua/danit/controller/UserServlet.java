package ua.danit.controller;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ua.danit.dao.UserDAOtoDB;
import ua.danit.model.User;
import ua.danit.utils.GetLoginFromCookie;
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
import java.util.Map;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private UserDAOtoDB userDAOtoDB;

    public UserServlet(UserDAOtoDB userDAOtoDB) {

        this.userDAOtoDB = userDAOtoDB;

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cookie[] cookies = req.getCookies();

        String id = new GetLoginFromCookie().getByName(cookies, "id");


        Map<String, Object> variables = new HashMap<>();

        User user = userDAOtoDB.getNotLikedUser();

        if (user == null) {
            resp.sendRedirect("/liked");
            return;
        }

        variables.put("photo", user.getPhoto());
        variables.put("name", user.getLogin());
        variables.put("id", String.valueOf(user.getId()));
        variables.put("myId", String.valueOf(id));



        Template template = new TemplateConfig().getConfig("like-page.html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Writer out = resp.getWriter();

        try {

            template.process(variables, out);

        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter("choice");
        String id = req.getParameter("id");
        String myLogin = req.getParameter("myId");

        userDAOtoDB.setLiked(Integer.parseInt(id), "yes".equals(choice));

        doGet(req, resp);


    }
}
