package servlets;

import entities.Comment;
import entities.Post;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.Helper;
import services.CommentService;
import services.PostService;
import services.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class MainServlet extends javax.servlet.http.HttpServlet {
    UserService userService = new UserService();
    PostService postService = new PostService();
    CommentService commentService = new CommentService();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Template t = Helper
                .getConfig(request.getServletContext())
                .getTemplate("main.ftl");
        HashMap<String, Object> root = new HashMap<>();
        root.put("form_url", request.getRequestURI());
        root.put("user", userService.getCurrentUser(request));
        root.put("page", "main");
        root.put("mainPost", postService.getMainPosts().get(0));
        List<Post> mainPosts = postService.getMainPosts().subList(1, postService.getMainPosts().size());
        root.put("posts", mainPosts);

        try {
            t.process(root, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
