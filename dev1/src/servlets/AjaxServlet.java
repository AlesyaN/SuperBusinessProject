package servlets;

import org.json.JSONObject;
import services.CommentService;
import services.LikeService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class AjaxServlet extends HttpServlet {
    UserService userService = new UserService();
    CommentService commentService = new CommentService();
    LikeService likeService = new LikeService();

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
            boolean b = likeService.toggleLike(request);
            JSONObject jo = new JSONObject();
            jo.put("like", b);
            response.setContentType("text/json");
            response.getWriter().println(jo.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
