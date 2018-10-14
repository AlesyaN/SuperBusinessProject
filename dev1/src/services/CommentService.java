package services;

import dao.CommentDAO;
import entities.Comment;
import entities.Post;

import java.util.List;

public class CommentService {
    CommentDAO commentDAO = new CommentDAO();

    public List<Comment> getCommentsByPost(Post post) {
        return commentDAO.getCommentsByPost(post);
    }
}
