package ua.danit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.controller.ChatServlet;
import ua.danit.controller.LikedServlet;
import ua.danit.controller.StaticServlet;
import ua.danit.controller.UserServlet;
import ua.danit.dao.*;
import ua.danit.model.Liked;
import ua.danit.model.User;
import ua.danit.utils.DataBaseLiked;

import java.util.HashMap;

public class ServerApp {
    public static void main(String[] args) throws Exception {

        LikedDAO likedDAO = new LikedDAO();
        UserDAO userDAO = new UserDAO();
        UserDAOtoDB userDAOtoDB = new UserDAOtoDB();
        LikedDAOtoDB likedDAOtoDB = new LikedDAOtoDB();
        ChatDAOtoDB chatDAOtoDB = new ChatDAOtoDB();


        Server server = new Server(8002);
        ServletContextHandler handler = new ServletContextHandler();
        ServletHolder holderUser = new ServletHolder(new UserServlet(userDAOtoDB, likedDAOtoDB, chatDAOtoDB));
        ServletHolder holderLiked = new ServletHolder(new LikedServlet(userDAO, likedDAO));
        ServletHolder holderStatic = new ServletHolder(new StaticServlet());
        ServletHolder holderChat = new ServletHolder(new ChatServlet());


        handler.addServlet(holderUser, "/user");
        handler.addServlet(holderLiked, "/liked");
        handler.addServlet(holderStatic, "/css/*");
        handler.addServlet(holderChat, "/messages");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
