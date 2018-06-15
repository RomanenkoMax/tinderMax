package ua.danit.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class LikedServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("./lib/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

//        Map<String, Object> model = new HashMap<>();
//        model.put("path", req.getRequestURI());
//        model.put("requrl", req.getRequestURL().toString());
//        String qs = req.getQueryString();
//        model.put("params", qs==null ? "" : qs);
//        model.put("user", "Andy");
//        Product latest = new Product("http://www.ua", "new site");
//        model.put("latestProduct", latest);
//        model.put("items", new RemoteData().get());
//
//        Template template = cfg.getTemplate("people-list-template.ftlh");
//        Writer out = resp.getWriter();
//        System.out.println(base.get(2) == null ? "no value yet" : base.get(2));
//        base.put(1, "Hello from ServletA");
//
//        try {
//            template.process(model, out);
//            String s = base.get(0);
//            base.put(0, "the value modified from servletA");
//            resp.getWriter().write(s);
//
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
