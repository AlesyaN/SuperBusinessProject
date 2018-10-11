package servlets;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            response.getWriter().println(
                    "<b>User #" + user.getId() + "</b><br>" +
                            "<b>Name:</b>" + user.getFullName() + "<br>" +
                            "<b>Date of birth:</b>" + user.getDateOfBirthToString() + "<br>" +
                            "<b>Place of birth:</b>" + user.getPlaceOfBirth() + "<br>" +
                            "<b>Education:</b>" + user.getEducation() + "<br>" +
                            "<b>Experience:</b>" + user.getExperience().toString() + "<br>" +
                            "<b>Position:</b>" + user.getPosition() + "<br>"+
                    "<a href='/edit-profile'>Edit</a>");
        }
    }
}
