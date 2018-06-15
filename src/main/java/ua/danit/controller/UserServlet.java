package ua.danit.controller;

import org.apache.commons.io.FileUtils;
import ua.danit.dao.LikedDAO;
import ua.danit.dao.UserDAO;
import ua.danit.model.Liked;
import ua.danit.utils.DataBaseUser;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {


    private Integer id;
    UserDAO userDAO = new UserDAO();
    LikedDAO likedDAO = new LikedDAO();

    public UserServlet(Integer next) {
        this.id = next;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        File file = new File("lib/html/like-page1.html");
        String outText = new FileUtils().readFileToString(file);

        if (id == userDAO.id + 1){
            id = 1;
        }

        outText = String.format(outText, userDAO.get(id).getName(), userDAO.get(id).getPhoto());
        writer.print(outText);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = req.getParameter("like");


        if (like.equals("yes")){
            Liked inst = new Liked();
            inst.setLikedId(++likedDAO.id);
            inst.setUserId(userDAO.get(id).getId());
            inst.setChatId(1);
            likedDAO.save(inst);
        }
        id++;
        doGet(req, resp);
    }
}
