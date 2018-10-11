package servlets;

import entities.Post;
import services.PostService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class NewsServlet extends HttpServlet {
    PostService postService = new PostService();
    UserService userService = new UserService();
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
        List<Post> news = postService.getNews();
        for (Post post: news) {
            pw.print("<a href='/" + post.getTheme() + "/" + post.getId() +"'>POST #" + post.getId() +"</a><br>" +
                    "<b>" + post.getTitle() + " by " + post.getAuthor().getName() +"</b><br>" +
                    "<i>" + post.getDate() + "</i><br>" +
                    "<p>" + post.getText() + "</p><br>");
            pw.flush();
        }
    }
}
