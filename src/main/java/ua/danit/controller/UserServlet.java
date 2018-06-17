package ua.danit.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.io.FileUtils;
import ua.danit.dao.LikedDAO;
import ua.danit.dao.UserDAO;
import ua.danit.dao.UserDAOtoDB;
import ua.danit.model.Liked;
import ua.danit.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    UserDAOtoDB userDAOtoDB;
    List<User> users;
    Iterator<User> iterator;

    public UserServlet(UserDAOtoDB userDAOtoDB) {
        this.userDAOtoDB = userDAOtoDB;
        users = userDAOtoDB.getAll();
        iterator = users.listIterator();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./lib/html"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        Map<String, Object> model = new HashMap<>();

        if (iterator.hasNext()){
            User user = iterator.next();
            model.put("name", user.getName());
            model.put("photo", user.getPhoto());

            Template template = cfg.getTemplate("like-page.html");
            Writer out = resp.getWriter();

            try {

                template.process(model, out);

            } catch (TemplateException e) {
                e.printStackTrace();
            }

        } else {
            resp.sendRedirect("/liked");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = req.getParameter("like");


        if (like.equals("yes")) {
//
            doGet(req, resp);
        } else {
            doGet(req, resp);
        }


    }
}
