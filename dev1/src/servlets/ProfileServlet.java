package servlets;

import entities.Post;
import entities.User;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class ProfileServlet extends HttpServlet {
    UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("logout") != null && !request.getParameter("logout").equals("")) {
            userService.logOut(request);
            response.sendRedirect("/main");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        if (request.getPathInfo() == null) {
            if (user == null) {
                response.sendRedirect("/login");
            } else {
                Template t = Helper
                        .getConfig(request.getServletContext())
                        .getTemplate("profile.ftl");
                HashMap<String, Object> root = new HashMap<>();
                root.put("form_url", request.getRequestURI());
                root.put("user", userService.getCurrentUser(request));
                root.put("page", "profile");
                root.put("someuser", userService.getCurrentUser(request));
                try {
                    t.process(root, response.getWriter());
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Template t = Helper
                    .getConfig(request.getServletContext())
                    .getTemplate("profile.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            root.put("someuser", userService.getUserById(Integer.parseInt(request.getPathInfo().substring(1))));
            root.put("page", "profile");
            root.put("user", userService.getCurrentUser(request));
            try {
                t.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
