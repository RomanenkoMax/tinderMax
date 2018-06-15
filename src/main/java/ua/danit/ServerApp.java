package ua.danit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.controller.LikedServlet;
import ua.danit.controller.StaticServlet;
import ua.danit.controller.UserServlet;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8002);
        ServletContextHandler handler = new ServletContextHandler();
        ServletHolder holderUser = new ServletHolder(new UserServlet(1));
        ServletHolder holderLiked = new ServletHolder(new LikedServlet());
        //ServletHolder holderStatic = new ServletHolder(new StaticServlet());

        handler.addServlet(holderUser, "/user");
        handler.addServlet(holderLiked, "/liked");
        //handler.addServlet(holderStatic, "/css/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
