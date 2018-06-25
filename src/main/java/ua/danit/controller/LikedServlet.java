package ua.danit.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ua.danit.dao.*;
import ua.danit.model.User;
import ua.danit.utils.TemplateConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LikedServlet extends HttpServlet {

    private UserDAOtoDB userDAOtoDB;
    private LikedDAOtoDB likedDAOtoDB;
    private ChatDAOtoDB chatDAOtoDB;


    public LikedServlet(UserDAOtoDB userDAOtoDB, LikedDAOtoDB likedDAOtoDB, ChatDAOtoDB chatDAOtoDB) {
        this.userDAOtoDB = userDAOtoDB;
        this.likedDAOtoDB = likedDAOtoDB;
        this.chatDAOtoDB = chatDAOtoDB;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        List<Integer> likeds = likedDAOtoDB.getAllUserIdByLogin(login);
        HashMap<Integer, User> users = new HashMap<>();

        for (Integer liked : likeds) {
            users.put(likedDAOtoDB.getChatIdByUserId(userDAOtoDB.getById(liked).getId()), userDAOtoDB.getById(liked));
        }

        Map<String, Object> model = new HashMap<>();

        model.put("users", users);

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
