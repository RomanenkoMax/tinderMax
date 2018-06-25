package ua.danit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.controller.*;
import ua.danit.controller.Filter.LoginFilter;
import ua.danit.dao.*;
import ua.danit.model.Liked;
import ua.danit.model.User;
import ua.danit.utils.DataBaseLiked;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.HashMap;

public class ServerApp {
    public static void main(String[] args) throws Exception {

        UserDAOtoDB userDAOtoDB = new UserDAOtoDB();
        LikedDAOtoDB likedDAOtoDB = new LikedDAOtoDB();
        ChatDAOtoDB chatDAOtoDB = new ChatDAOtoDB();

        Server server = new Server(8002);
        ServletContextHandler handler = new ServletContextHandler();
        ServletHolder holderUser = new ServletHolder(new UserServlet(userDAOtoDB, likedDAOtoDB, chatDAOtoDB));
        ServletHolder holderLiked = new ServletHolder(new LikedServlet(userDAOtoDB, likedDAOtoDB, chatDAOtoDB));
        ServletHolder holderChat = new ServletHolder(new ChatServlet(userDAOtoDB, likedDAOtoDB, chatDAOtoDB));
        ServletHolder holderLogin = new ServletHolder(new LoginServlet(userDAOtoDB));
        ServletHolder holderStatic = new ServletHolder(new StaticServlet());




        handler.addServlet(holderUser, "/user");
        handler.addServlet(holderLiked, "/liked");
        handler.addServlet(holderChat, "/messages/*");
        handler.addServlet(holderStatic, "/css/*");
        handler.addServlet(holderLogin, "/login");

        FilterHolder filterHolder = new FilterHolder(LoginFilter.class);
        handler.addFilter(filterHolder, "/user", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST) );
        handler.addFilter(filterHolder, "/liked", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST) );
        handler.addFilter(filterHolder, "/messages/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST) );

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
