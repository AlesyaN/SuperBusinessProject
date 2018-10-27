package servlets;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class CryptoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template t = Helper
                .getConfig(request.getServletContext())
                .getTemplate("crypto.ftl");
        HashMap<String, Object> root = new HashMap<>();
        root.put("form_url", request.getRequestURI());
        root.put("page", "crypto");
        try {
            t.process(root, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
