package servlets;

import entities.User;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/main");
        } else {
            boolean flag = false;
            Cookie [] cookies = request.getCookies();
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("uid")) {
                    int id = Integer.parseInt(cookie.getValue());
                    User currentUser = userService.getUserById(id);
                    userService.authorize(currentUser, request);
                    flag = true;
                    response.sendRedirect("/main");
                }
            }
            if (!flag) {
                User currentUser = userService.authenticate(request);
                if (currentUser != null) {
                    userService.authorize(currentUser, request);
                    if (request.getParameter("remember") != null) {
                        Cookie cookie = new Cookie("uid", String.valueOf(currentUser.getId()));
                        response.addCookie(cookie);
                    }
                    response.sendRedirect("/main");
                } else {
                    response.sendRedirect("/login?err_mess=too_bad_login");
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/profile");
        } else {
            Template t = Helper
                    .getConfig(request.getServletContext())
                    .getTemplate("login.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            root.put("user", userService.getCurrentUser(request));
            root.put("page", "login");
            try {
                t.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
