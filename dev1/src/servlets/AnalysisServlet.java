package servlets;

import entities.Comment;
import entities.Post;
import services.CommentService;
import services.PostService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AnalysisServlet extends HttpServlet {
    PostService postService = new PostService();
    UserService userService = new UserService();
    CommentService commentService = new CommentService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        pw.flush();
        if (request.getPathInfo() == null) {
            List<Post> analysis = postService.getAnalysis();
            for (Post post : analysis) {
                pw.print("<a href='/" + post.getTheme() + "/" + post.getId() + "'>POST #" + post.getId() + "</a><br>" +
                        "<b>" + post.getTitle() + " by " + post.getAuthor().getName() + "</b><br>" +
                        "<i>" + post.getDate() + "</i><br>" +
                        "<p>" + post.getText() + "</p><br>");
                pw.flush();
            }
        } else {
            String id = request.getPathInfo().substring(1);
            Post post = postService.getPostById(Integer.parseInt(id));
            pw.println("<b>" + post.getTitle() + " by " + post.getAuthor().getName() + "</b><br>" +
                    "<i>" + post.getDate() + "</i><br>" +
                    "<p>" + post.getText() + "</p><br>");
            List<Comment> comments = commentService.getCommentsByPost(post);
            if (comments.size() > 0) {
                pw.println("  Comments: <br>");
                for (Comment comment : comments) {
                    pw.println(comment.getAuthor().getName() + " on " + comment.getDate() + "<br>");
                    pw.println(comment.getText() + "<br>");
                }
            }
            pw.println("Your comment: <br>" +
                    "<form method='post'>" +
                    "<textarea rows='4' name='text'></textarea><br>" +
                    "<input type='submit' name='submit'>" +
                    "</form>");
        }
    }
}
