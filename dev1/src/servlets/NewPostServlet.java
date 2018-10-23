package servlets;

import services.PostService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig
public class NewPostServlet extends HttpServlet {
    UserService userService = new UserService();
    PostService postService = new PostService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = getFileName(filePart);

        int id = postService.savePost(request.getParameter("title"),
                request.getParameter("text"),
                filePart,
                getServletContext().getRealPath("/files"),
                userService.getCurrentUser(request),
                fileName);
        response.sendRedirect("/analysis/" + id);
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.getCurrentUser(request) != null) {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            if (userService.getCurrentUser(request) != null) {
                pw.println("<a href='/profile'>Profile</a>");
            } else {
                pw.print("<a href='/login'>Log In </a>");
                pw.println("<a href='/sign-up'> Sign Up</a>");
            }
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
            pw.println("<form method='post' enctype=\"multipart/form-data\">" +
                    "<label for='title'>Title:</label><br>" +
                    "<input type='text' name='title' id='title><br>" +
                    "<label for='text'>Text:</label><br>" +
                    "<textarea rows='10' name='text' id='text'></textarea><br>" +
                    "<input type='file' name='file'><br>" +
                    "<input type='submit'>" +
                    "</form>");
        } else {
            response.sendRedirect("/login");
        }
    }
}
