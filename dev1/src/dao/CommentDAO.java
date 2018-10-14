package dao;

import entities.Comment;
import entities.Post;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDAO {
    Connection connection = ConnectionSingleton.getConnection();
    UserDAO userDAO = new UserDAO();
    PostDAO postDAO = new PostDAO();
    UserService userService = new UserService();

    public List<Comment> getCommentsByPost(Post post) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from comment where news_id=?");
            ps.setInt(1, post.getId());
            ResultSet rs = ps.executeQuery();
            List<Comment> comments = new ArrayList<>();
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        userDAO.getUserById(rs.getInt("author_id")),
                        postDAO.getPostById(rs.getInt("news_id")),
                        rs.getDate("date"),
                        rs.getString("text")
                ));
            }
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveComment(Integer authorId, Integer newsId, String text) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into comment (author_id, news_id, text, \"date\")" +
                    " values(?,?,?, 'now')");
            ps.setInt(1, authorId);
            ps.setInt(2, newsId);
            ps.setString(3,  text);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
