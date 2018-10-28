package servlets;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import helpers.Helper;
import services.PostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class SearchServlet extends HttpServlet {
    PostService postService = new PostService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Template t = Helper
                .getConfig(request.getServletContext())
                .getTemplate("search.ftl");
        HashMap<String, Object> root = new HashMap<>();
        root.put("form_url", request.getRequestURI());
        root.put("page", "search");
        if (request.getParameter("search") != null && !request.getParameter("search").equals("")) {
            boolean news = request.getParameter("news") != null && request.getParameter("news").equals("on");;
            boolean analysis = request.getParameter("analysis") != null && request.getParameter("analysis").equals("on");;

            root.put("posts", postService.getPostsByTitleMask(request.getParameter("search"),
                    news,
                    analysis));
        } else {
            root.put("posts", postService.getAllPosts());
        }
        try {
            t.process(root, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
