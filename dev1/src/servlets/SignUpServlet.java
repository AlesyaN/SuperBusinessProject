package servlets;

import entities.Post;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.util.HashMap;

@MultipartConfig
public class SignUpServlet extends HttpServlet {
    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/main");
        } else {
            Part filePart = request.getPart("file");
            if (userService.register(request, filePart, getServletContext().getRealPath("/files/users"))) {
                Cookie cookie = new Cookie("uid", String.valueOf(userService.getCurrentUser(request).getId()));
                response.addCookie(cookie);
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/sign-up?problem=email_is_not_unique");
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/main");
        } else {
            Template t = Helper
                    .getConfig(request.getServletContext())
                    .getTemplate("signup.ftl");
            HashMap<String, Object> root = new HashMap<>();
            if (request.getParameter("problem") != null && request.getParameter("problem").equals("email_is_not_unique")) {
                root.put("problem", "emailIsNotUnique");
            }
            root.put("form_url", request.getRequestURI());
            root.put("page", "sign-up");
            root.put("user", userService.getCurrentUser(request));
            try {
                t.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }

    }
}
