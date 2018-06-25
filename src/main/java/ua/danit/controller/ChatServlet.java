package ua.danit.controller;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import ua.danit.dao.ChatDAOtoDB;
import ua.danit.dao.LikedDAOtoDB;
import ua.danit.dao.UserDAOtoDB;
import ua.danit.model.Chat;
import ua.danit.model.Liked;
import ua.danit.model.User;
import ua.danit.utils.TemplateConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatServlet extends HttpServlet {

    UserDAOtoDB userDAOtoDB;
    LikedDAOtoDB likedDAOtoDB;
    ChatDAOtoDB chatDAOtoDB;

    public ChatServlet(UserDAOtoDB userDAOtoDB, LikedDAOtoDB likedDAOtoDB, ChatDAOtoDB chatDAOtoDB) {
        this.userDAOtoDB = userDAOtoDB;
        this.likedDAOtoDB = likedDAOtoDB;
        this.chatDAOtoDB = chatDAOtoDB;
    }

    public Integer getChatId(String parameter) {
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < parameter.length(); i++) {
            char ch = parameter.charAt(i);
            if ((ch > 47 && ch < 58)) {
                stringBuilder.append(ch);
            }
        }

        if (stringBuilder.length() > 0) {

            return Integer.parseInt(stringBuilder.toString());

        } else {

            return null;

        }
    }

    private String beautyTime(Long cartTime) {

        Date date = new Date(cartTime);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm");

        return format.format(date);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer chatId = getChatId(req.getPathInfo());

        Chat chat = chatDAOtoDB.get(chatId);
        User userTo = userDAOtoDB.get(chat.getToLogin());
        User userFrom = userDAOtoDB.get(chat.getFromLogin());

        ArrayList<String> messageTo = chatDAOtoDB.getAllMessagesByLodin(chat.getFromLogin(), chat.getToLogin());
        ArrayList<String> messageFrom = chatDAOtoDB.getAllMessagesByLodin(chat.getToLogin(), chat.getFromLogin());

        if (messageTo.size() == 0) {

            Chat chatTo = new Chat(chat.getFromLogin(), chat.getToLogin());
            chatDAOtoDB.put(chatTo);

            Liked liked = new Liked(userFrom.getId(), chatTo.getChatId(), chatTo.getFromLogin());
            likedDAOtoDB.put(liked);

            messageTo.add(chatTo.getMessage());

        }

        Map<String, Object> model = new HashMap<>();
        model.put("chatName", chat.getToLogin());
        model.put("mes1", messageTo.get(0));

        model.put("mes2", messageFrom.get(0));
        model.put("date1", beautyTime(chat.getTime()));


        Template template = new TemplateConfig().getConfig("chat.html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        Writer out = resp.getWriter();

        try {

            template.process(model, out);

        } catch (TemplateException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");


    }
}
