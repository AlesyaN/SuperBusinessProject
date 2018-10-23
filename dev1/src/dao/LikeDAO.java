package dao;

import entities.Like;
import entities.Post;
import entities.User;
import services.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO {
    Connection connection = ConnectionSingleton.getConnection();
    UserService userService = new UserService();
    UserDAO userDAO = new UserDAO();
    PostDAO postDAO = new PostDAO();

    public List<Like> getLikesByPost(Post post) {
        List<Like> likes = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"like\" where news_id=?");
            ps.setInt(1, post.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                likes.add(new Like(
                   rs.getInt("id" ),
                        (new UserDAO()).getUserById(rs.getInt("author_id")),
                        (new PostDAO()).getPostById(rs.getInt("news_id"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likes;
    }

    public Like getLikeOnPost(Integer id, User currentUser) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"like\" where news_id=? and author_id = ?");
            ps.setInt(1, id);
            ps.setInt(2, currentUser.getId());
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                return null;

            } else {
                rs.next();
                return new Like(rs.getInt("id"),
                        userDAO.getUserById(rs.getInt("author_id")),
                        postDAO.getPostById(rs.getInt("news_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteLike(Like like) {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from \"like\" where id=?");
            ps.setInt(1, like.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLike(int i, User currentUser) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into \"like\"(author_id, news_id) values (?, ?)");
            ps.setInt(2, i);
            ps.setInt(1, currentUser.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
