package ua.danit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.danit.controller.*;
import ua.danit.controller.Filter.LoginFilter;
import ua.danit.controller.Filter.StartFilter;
import ua.danit.dao.*;
import javax.servlet.*;
import java.util.EnumSet;


public class ServerApp {
    public static void main(String[] args) throws Exception {

        UserDAOtoDB userDAOtoDB = new UserDAOtoDB();
        ChatDAOtoDB chatDAOtoDB = new ChatDAOtoDB();

        String port = null;

        port = args.length > 0 ? args[0] : "8002";

        new Server(Integer.parseInt(port)) {{
            setHandler(new ServletContextHandler() {{


                addServlet(new ServletHolder(new UserServlet(userDAOtoDB)), "/user");
                addServlet(new ServletHolder(new LikedServlet((userDAOtoDB), chatDAOtoDB)), "/liked");
                addServlet(new ServletHolder(new ChatServlet(userDAOtoDB, chatDAOtoDB)), "/messages/*");
                addServlet(new ServletHolder(new LoginServlet(userDAOtoDB)), "/login");
                addServlet(new ServletHolder(new StaticServlet()), "/css/*");
                addServlet(new ServletHolder(new NewUserServlet(userDAOtoDB)), "/newUser");

                addFilter(new FilterHolder(LoginFilter.class), "/user", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
                addFilter(new FilterHolder(LoginFilter.class), "/liked", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
                addFilter(new FilterHolder(LoginFilter.class), "/messages/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
                addFilter(new FilterHolder(StartFilter.class), "/", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

            }});
            start();
            join();
        }};
    }
}


