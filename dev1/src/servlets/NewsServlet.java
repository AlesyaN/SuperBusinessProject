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
        if (request.getPathInfo() == null) {
            Template t = Helper
                    .getConfig(request.getServletContext())
                    .getTemplate("news.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            root.put("user", userService.getCurrentUser(request));
            root.put("posts", postService.getNews());
            root.put("page", "news");
            try {
                t.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } else {
            Template t = Helper
                    .getConfig(request.getServletContext())
                    .getTemplate("article_news.ftl");
            HashMap<String, Object> root = new HashMap<>();
            root.put("form_url", request.getRequestURI());
            root.put("user", userService.getCurrentUser(request));

            String id = request.getPathInfo().substring(1);
            System.out.println(id);
            Post post = postService.getPostById(Integer.parseInt(id));
            root.put("post", post);
            System.out.println(post.getPicPath());
            root.put("likes", likeService.getLikesByPost(post, true).size());
            root.put("dislikes", likeService.getLikesByPost(post, false).size());
            root.put("comments", commentService.getCommentsByPost(post));
            root.put("page", "news");
            try {
                t.process(root, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
}
