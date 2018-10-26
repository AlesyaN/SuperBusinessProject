package servlets;

import entities.Post;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.Helper;
import services.PostService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.HashMap;

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
            Template t = Helper
                    .getConfig(request.getServletContext())
                    .getTemplate("create_post.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            root.put("user", userService.getCurrentUser(request));
            root.put("page", "new-post");
            try {
                t.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}
