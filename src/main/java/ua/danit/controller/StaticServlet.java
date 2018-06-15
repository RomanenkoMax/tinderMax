package ua.danit.controller;

import com.google.common.io.ByteStreams;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "lib/style/css" + req.getPathInfo();
        URL file = getClass().getClassLoader().getResource(url);
        InputStream in = file.openStream();
        ServletOutputStream out = resp.getOutputStream();
        ByteStreams.copy(in, out);
        in.close();
        out.close();
    }
}
