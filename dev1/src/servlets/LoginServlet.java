package servlets;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
                response.sendRedirect("/main");
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
            PrintWriter pw = response.getWriter();
            pw.println("<a href='/main'>Main</a>");
            pw.print("<a href='/login'>Log In </a>");
            pw.println("<a href='/sign-up'> Sign Up</a>");

            pw.println("<form method='post'>" +
                    "<input type='text' name='email'><br>" +
                    "<input type='password' name='password'><br>" +
                    "<input type='submit' name='submit'>"+
                    "</form>");
            pw.println("<form method='get' action='/search'>" +
                    "<input type='text' name='search'>" +
                    "<input type='submit' name='submit'>" +
                    "</form>");
            pw.println("<a href='/currencies'>Currencies</a>");
            pw.println("<a href='/stocks'>Stocks</a>");
            pw.println("<a href='/news'>News</a>");
            pw.println("<a href='/analysis'>Analysis</a>");
            pw.println("<a href='/crypto'>Crypto</a>");
        }
    }
}
