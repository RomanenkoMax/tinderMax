package ua.danit.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ua.danit.dao.*;
import ua.danit.model.Liked;
import ua.danit.model.User;

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

    UserDAOtoDB userDAOtoDB;
    LikedDAOtoDB likedDAOtoDB;
    ChatDAOtoDB chatDAOtoDB;


    public LikedServlet(UserDAOtoDB userDAOtoDB, LikedDAOtoDB likedDAOtoDB, ChatDAOtoDB chatDAOtoDB) {
        this.userDAOtoDB = userDAOtoDB;
        this.likedDAOtoDB = likedDAOtoDB;
        this.chatDAOtoDB = chatDAOtoDB;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./lib/html"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        String login = req.getParameter("login");
        List<Integer> likeds = likedDAOtoDB.getAllByLogin(login);
        List<User> users = new ArrayList<>();

        for (Integer liked : likeds) {
            users.add(userDAOtoDB.getById(liked));
        }


        Map<String, Object> model = new HashMap<>();


        model.put("users", users);
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Template template = cfg.getTemplate("people-list.html");

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
