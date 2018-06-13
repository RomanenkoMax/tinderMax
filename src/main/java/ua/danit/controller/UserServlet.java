package ua.danit.controller;

import org.apache.commons.io.FileUtils;
import ua.danit.utils.DataBase;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {


    private Integer next;

    public UserServlet(Integer next) {
        this.next = next;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        File file = new File("lib/html/like-page1.html");
        String outText = new FileUtils().readFileToString(file);
        DataBase base = new DataBase();
        outText = String.format(outText, base.getFromBase().get(next).getName(), base.getFromBase().get(next++).getPhoto());
        writer.print(outText);
        if (next == base.getFromBase().size() + 1){
            next = 1;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String like = (String) req.getParameter("like");
        doGet(req, resp);
    }
}
