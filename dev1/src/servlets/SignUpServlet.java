package servlets;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUpServlet extends HttpServlet {
    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/main");
        } else {
            if (userService.register(request)) {
                response.sendRedirect("/profile");
            } else {
                response.sendRedirect("/sign-up?id=problem");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/main");
        } else {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<form method='post'>" +
                    "Email:<input type='text' name='email' required><br>" +
                    "Password:<input type='password' name='password' required><br>" +
                    "Surname: <input type='text' name='surname' required><br>" +
                    "Name: <input type='text' name='name' required><br>" +
                    "Patronymic: <input type='text' name='patronymic'><br>" +
                    "Date of birth: <input type='text' name='dateOfBirth' required><br>" +
                    "Place of birth: <input type='text' name='placeOfBirth'><br>" +
                    "Education: <input type='text' name='education'><br>" +
                    "Experience: <input type='text' name='scope1'> <input type='text' name='experience1'><br>" +
                    "<input type='text' name='scope2'> <input type='text' name='experience2'><br>" +
                    "<input type='text' name='scope3'> <input type='text' name='experience3'><br>" +
                    "Position: <input type='text' name='position'><br>" +
                    "<input type='submit' name='submit'>" +
                    "</form>"
                );
        }
    }
}
