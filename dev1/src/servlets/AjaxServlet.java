package servlets;

import entities.Post;
import org.json.JSONArray;
import org.json.JSONObject;
import services.CommentService;
import services.LikeService;
import services.PostService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class AjaxServlet extends HttpServlet {
    UserService userService = new UserService();
    CommentService commentService = new CommentService();
    LikeService likeService = new LikeService();
    PostService postService = new PostService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("ajax").equals("saveComment")) {
            int id = commentService.saveComment(request);
            JSONObject jo = new JSONObject();
            jo.put("authorName", userService.getCurrentUser(request).getName());
            jo.put("authorId", userService.getCurrentUser(request).getId());
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            jo.put("date", df.format(new Date()));
            jo.put("id", id);
            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        } else if (request.getParameter("ajax").equals("deleteComment")) {
            commentService.deleteComment(request);
        } else if (request.getParameter("ajax").equals("like")) {
            boolean like = likeService.toggle(request, true);
            boolean dislike = likeService.delete(request, false);
            JSONObject jo = new JSONObject();
            jo.put("like", like);
            jo.put("dislike", dislike);
            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        } else if (request.getParameter("ajax").equals("dislike")) {
            boolean like = likeService.delete(request, true);
            boolean dislike = likeService.toggle(request, false);
            JSONObject jo = new JSONObject();
            jo.put("like", like);
            jo.put("dislike", dislike);
            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        } else if (request.getParameter("ajax").equals("search")) {
            List<Post> posts = postService.getPostsByTitleMask(request.getParameter("query"));
            JSONArray ja = new JSONArray();
            for (Post post: posts) {
                JSONObject jo = new JSONObject();
                jo.put("picPath", post.getPicPath());
                jo.put("title", post.getTitle());
                jo.put("text", post.getText());
                jo.put("theme", post.getTheme());
                jo.put("id", post.getId());
                ja.put(jo);
            }
            JSONObject jo = new JSONObject();
            jo.put("posts", ja);
            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        } else if (request.getParameter("ajax").equals("allposts")) {
            List<Post> posts = postService.getAllPosts();
            JSONArray ja = new JSONArray();
            for (Post post: posts) {
                JSONObject jo = new JSONObject();
                jo.put("picPath", post.getPicPath());
                jo.put("title", post.getTitle());
                jo.put("text", post.getText());
                jo.put("theme", post.getTheme());
                jo.put("id", post.getId());
                ja.put(jo);
            }
            JSONObject jo = new JSONObject();
            jo.put("posts", ja);
            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
