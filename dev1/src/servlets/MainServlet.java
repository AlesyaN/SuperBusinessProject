package servlets;

import entities.Post;
import services.PostService;
import services.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MainServlet extends javax.servlet.http.HttpServlet {
    UserService userService = new UserService();
    PostService postService = new PostService();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
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
        pw.println("<a href='/currencies'>Currencies</a>");
        pw.println("<a href='/stocks'>Stocks</a>");
        pw.println("<a href='/news'>News</a>");
        pw.println("<a href='/analysis'>Analysis</a>");
        pw.println("<a href='/crypto'>Crypto</a><br>");
        pw.flush();
        List<Post> mainPosts = postService.getMainPosts();
        for (Post post: mainPosts) {
            pw.print("<a href='/" + post.getTheme() + "/" + post.getId() +"'>POST #" + post.getId() +" from " + post.getTheme() +"</a><br>" +
            "<b>" + post.getTitle() + " by " + post.getAuthor().getName() +"</b><br>" +
            "<i>" + post.getDate() + "</i><br>" +
            "<p>" + post.getText() + "</p><br>");
            pw.flush();
        }
    }
}
