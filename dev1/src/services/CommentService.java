package services;

import dao.CommentDAO;
import entities.Comment;
import entities.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommentService {
    CommentDAO commentDAO = new CommentDAO();
    UserService userService = new UserService();

    public List<Comment> getCommentsByPost(Post post) {
        return commentDAO.getCommentsByPost(post);
    }

    public void saveComment(HttpServletRequest request) {
        commentDAO.saveComment(
                userService.getCurrentUser(request).getId(),
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("text")
        );
    }
}
