package servlets;

import entities.Comment;
import entities.Post;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.Helper;
import services.CommentService;
import services.LikeService;
import services.PostService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class NewsServlet extends HttpServlet {
    PostService postService = new PostService();
    UserService userService = new UserService();
    CommentService commentService = new CommentService();
    LikeService likeService = new LikeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("same servlet");
        if (request.getPathInfo() == null) {
            Template t = Helper
                    .getConfig(request.getServletContext())
                    .getTemplate("news_list.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            root.put("user", userService.getCurrentUser(request));
            root.put("posts", postService.getNews());
            try {
                t.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } else {
            Template t = Helper
                    .getConfig(request.getServletContext())
                    .getTemplate("news_article.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            root.put("user", userService.getCurrentUser(request));

            String id = request.getPathInfo().substring(1);
            Post post = postService.getPostById(Integer.parseInt(id));
            root.put("post", post);
            root.put("likes", likeService.getLikesByPost(post).size());
            root.put("comments", commentService.getCommentsByPost(post));
            try {
                t.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
