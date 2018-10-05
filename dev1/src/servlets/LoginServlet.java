package servlets;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/main");
        } else {
            User currentUser = userService.authenticate(request);
            if (currentUser != null) {
                userService.authorize(currentUser, request);
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/login?err_mess=too_bad_login");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/profile");
        } else {
            response.setContentType("text/html");
            response.getWriter().println("<form method='post'>" +
                    "<input type='text' name='login'><br>" +
                    "<input type='password' name='password'><br>" +
                    "<input type='submit' name='submit'>"+
                    "</form>");
        }
    }
}
