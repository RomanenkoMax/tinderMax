package ua.danit.controller;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ua.danit.dao.ChatDAOtoDB;
import ua.danit.dao.UserDAOtoDB;
import ua.danit.model.Chat;
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
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatServlet extends HttpServlet {

    UserDAOtoDB userDAOtoDB;
    ChatDAOtoDB chatDAOtoDB;

    public ChatServlet(UserDAOtoDB userDAOtoDB, ChatDAOtoDB chatDAOtoDB) {
        this.userDAOtoDB = userDAOtoDB;
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

    private String beautyTime(Long time) {

        Date date = new Date(time);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH.mm");

        return format.format(date);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String toid = String.valueOf(getChatId(req.getPathInfo()));

        Cookie[] cookies = req.getCookies();

        String fromid = new GetLoginFromCookie().getByName(cookies, "id");


        User userTo = userDAOtoDB.getById(Integer.parseInt(toid));
        User userFrom = userDAOtoDB.getById(Integer.parseInt(fromid));

        TreeMap<Long, Chat> map = new TreeMap<>();
        Map<String, Chat> map1 = new LinkedHashMap<>();


        HashMap<Long, Chat> messageFrom = chatDAOtoDB.getChatByLogins(fromid, toid);
        HashMap<Long, Chat> messageTo = chatDAOtoDB.getChatByLogins(toid, fromid);


        if (messageTo.size() == 0) {

            Chat chatTo = new Chat(fromid, toid);
            chatDAOtoDB.put(chatTo);

            messageTo.put(chatTo.getTime(), chatTo);

        }

        for (Long time : messageFrom.keySet()) {

            map.put(time, messageFrom.get(time));

        }

        for (Long time : messageTo.keySet()) {

            map.put(time, messageTo.get(time));

        }



        

        Map<String, Object> model = new HashMap<>();
        model.put("chatName", userTo.getLogin());
        model.put("chatMap", map);
        model.put("chatId", toid);
        model.put("loginTo", toid);
        model.put("loginFrom", fromid);


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
        String loginTo = req.getParameter("loginTo");
        String loginFrom = req.getParameter("loginFrom");


        Chat chat = new Chat(loginTo, loginFrom);
        chat.setMessage(message);

        chatDAOtoDB.put(chat);

        doGet(req, resp);
    }
}
