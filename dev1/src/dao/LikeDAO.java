package dao;

import entities.Like;
import entities.Post;
import entities.User;
import helpers.Helper;
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

    public List<Like> getLikesByPost(Post post, boolean type) {

        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"like\" where news_id=? and type=?");
            ps.setInt(1, post.getId());
            ps.setBoolean(2, type);
            ResultSet rs = ps.executeQuery();
            return Helper.makeORMListOfLikes(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Like getLikeOnPost(Integer id, User currentUser, boolean type) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"like\" where news_id=? and author_id = ? and type = ?");
            ps.setInt(1, id);
            ps.setInt(2, currentUser.getId());
            ps.setBoolean(3, type);
            ResultSet rs = ps.executeQuery();
            return Helper.makeORMLike(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Like like) {
        try {
            PreparedStatement ps = connection.prepareStatement("delete from \"like\" where id=?");
            ps.setInt(1, like.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(int i, User currentUser, boolean type) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into \"like\"(author_id, news_id, type) values (?, ?, ?)");
            ps.setInt(2, i);
            ps.setInt(1, currentUser.getId());
            ps.setBoolean(3, type);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
