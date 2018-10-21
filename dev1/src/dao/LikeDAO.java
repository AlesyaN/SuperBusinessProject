package dao;

import entities.Like;
import entities.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO {
    Connection connection = ConnectionSingleton.getConnection();

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
}
