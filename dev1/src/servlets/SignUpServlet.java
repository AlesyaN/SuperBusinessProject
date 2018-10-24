package servlets;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@MultipartConfig
public class SignUpServlet extends HttpServlet {
    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.sendRedirect("/main");
        } else {
            Part filePart = request.getPart("file");
            if (userService.register(request, filePart, getServletContext().getRealPath("/files/users"))) {
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
            pw.println("<a href='/main'>Main</a>");
            pw.print("<a href='/login'>Log In </a>");
            pw.println("<a href='/sign-up'> Sign Up</a>");
            pw.println("<a href='/currencies'>Currencies</a>");
            pw.println("<a href='/stocks'>Stocks</a>");
            pw.println("<a href='/news'>News</a>");
            pw.println("<a href='/analysis'>Analysis</a>");
            pw.println("<a href='/crypto'>Crypto</a>");
            pw.println("<form method='post' enctype='multipart/form-data'>" +
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
                    "Avatar: <input type='file' name='file'><br>" +
                    "<input type='submit' name='submit'>" +
                    "</form>"
                );
        }
    }
}
