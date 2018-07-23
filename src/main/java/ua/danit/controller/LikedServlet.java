package ua.danit.controller;


import freemarker.template.Template;
import freemarker.template.TemplateException;
import ua.danit.dao.*;
import ua.danit.model.User;
import ua.danit.utils.GetLoginFromCookie;
import ua.danit.utils.TemplateConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LikedServlet extends HttpServlet {

    private UserDAOtoDB userDAOtoDB;
    private ChatDAOtoDB chatDAOtoDB;


    public LikedServlet(UserDAOtoDB userDAOtoDB, ChatDAOtoDB chatDAOtoDB) {
        this.userDAOtoDB = userDAOtoDB;
        this.chatDAOtoDB = chatDAOtoDB;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        String login = new GetLoginFromCookie().getByName(cookies, "id");


        List<User> likedUsers = userDAOtoDB.getLikedUsers();

        Map<String, Object> model = new HashMap<>();

        model.put("users", likedUsers);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Template template = new TemplateConfig().getConfig("people-list.html");

        Writer out = resp.getWriter();

        try {
            template.process(model, out);

        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
