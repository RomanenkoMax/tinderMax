package ua.danit.controller;

import org.apache.commons.io.FileUtils;
import ua.danit.model.User;
import ua.danit.utils.DataBaseUser;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class UserServlet extends HttpServlet {


    private Integer id;
    HashMap<Integer, User> liked = new HashMap<>();

    public UserServlet(Integer next) {
        this.id = next;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        File file = new File("lib/html/like-page1.html");
        String outText = new FileUtils().readFileToString(file);
        DataBaseUser base = new DataBaseUser();

        if (id == base.getBaseUser().size() + 1){
            id = 1;
        }

        outText = String.format(outText, base.getBaseUser().get(id).getName(), base.getBaseUser().get(id).getPhoto());
        writer.print(outText);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = req.getParameter("like");
        Integer nextKey = liked.size() + 1;
        DataBaseUser base = new DataBaseUser();
        if (like.equals("yes")){
            liked.put(nextKey, base.getBaseUser().get(id));
        }
        id++;
        doGet(req, resp);
    }
}
