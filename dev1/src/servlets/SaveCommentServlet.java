package servlets;

import org.json.JSONObject;
import services.CommentService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class SaveCommentServlet extends HttpServlet {
    UserService userService = new UserService();
    CommentService commentService = new CommentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST THAT");
        commentService.saveComment(request);
        JSONObject jo = new JSONObject();
        jo.put("authorName", userService.getCurrentUser(request).getName());
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        jo.put("date", df.format(new Date()));
        response.setContentType("text/json");
        response.getWriter().println(jo.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
