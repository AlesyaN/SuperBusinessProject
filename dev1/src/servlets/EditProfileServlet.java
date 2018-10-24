package servlets;

import entities.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@MultipartConfig
public class EditProfileServlet extends HttpServlet {
    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendRedirect("/login");
        } else {
            Part filePart = request.getPart("file");
            if (userService.edit(request, filePart, getServletContext().getRealPath("/files/users"))) {
                User currentUser = userService.authenticate(request);
                userService.authorize(currentUser, request);
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/profile?id=problem");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) == null) {
            response.sendRedirect("/login");
        } else {
            User user = userService.getCurrentUser(request);
            PrintWriter pw = response.getWriter();
            pw.println("<a href='/main'>Main</a>");
            pw.print("<a href='/profile'>Profile</a>");
            pw.println("<a href='/currencies'>Currencies</a>");
            pw.println("<a href='/stocks'>Stocks</a>");
            pw.println("<a href='/news'>News</a>");
            pw.println("<a href='/analysis'>Analysis</a>");
            pw.println("<a href='/crypto'>Crypto</a>");
            pw.println("<form method='post' enctype='multipart/form-data'>" +
                    "Email:<input type='text' name='email' value='" + user.getEmail() + "'required><br>" +
                    "Password:<input type='password' name='password' value='" + user.getPassword() + "' required><br>" +
                    "Surname: <input type='text' name='surname' value= '" + user.getSurname() + "'required><br>"  +
                    "Name: <input type='text' name='name' value='" + user.getName() +"' required><br>" +
                    "Patronymic: <input type='text' name='patronymic' value='" + user.getPatronymic() + "'><br>" +
                    "Date of birth: <input type='text' name='date_of_birth' value='" + user.getDateOfBirthToString() +"'><br>" +
                    "Place of birth: <input type='text' name='place_of_birth' value='" + user.getPlaceOfBirth() + "'><br>" +
                    "Education: <input type='text' name='education' value='" + user.getEducation() +"'><br>");
                    pw.println("Scope: <input type='text' name='scope' value='" + user.getScope()+ "'><br>" +
                            "Experience: <input type='text' name='experience' value='" + user.getExperience()+ "'><br>");

                    pw.println("Position: <input type='text' name='position' value='" + user.getPosition() +"'><br>" +
                            "<input type='file' name='file'>" +
                    "<input type='submit' name='submit'>" +
                    "</form>"
            );
        }
    }
}
