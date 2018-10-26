package servlets;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProfileServlet extends HttpServlet {
    UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request);
        System.out.println("");
        if (user == null) {
            response.sendRedirect("/login");
        } else {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<a href='/profile'>Profile</a>");
            pw.println("<form method='get' action='/search'>" +
                    "<input type='text' name='search'>" +
                    "<input type='submit' name='submit'>" +
                    "</form>");

            pw.println("<a href='/main'>Main</a>");
            pw.println("<a href='/currencies'>Currencies</a>");
            pw.println("<a href='/stocks'>Stocks</a>");
            pw.println("<a href='/news'>News</a>");
            pw.println("<a href='/analysis'>Analysis</a>");
            pw.println("<a href='/crypto'>Crypto</a><br>");
            pw.println(
                    "<b>User #" + user.getId() + "</b><br>" +
                            "<img src='" + user.getPicPath() + "'><br>" +
                            "<b>Name:</b>" + user.getFullName() + "<br>" +
                            "<b>Date of birth:</b>" + user.getDateOfBirthToString() + "<br>" +
                            "<b>Place of birth:</b>" + user.getPlaceOfBirth() + "<br>" +
                            "<b>Education:</b>" + user.getEducation() + "<br>" +
                            "<b>Experience:</b>" + user.getExperience() + "<br>" +
                            "<b>Scope:</b>" + user.getScope() + "<br>" +
                            "<b>Position:</b>" + user.getPosition() + "<br>"+
                    "<a href='/edit-profile'>Edit</a>");
        }
    }
}
