package dao;

import entities.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    Connection connection = ConnectionSingleton.getConnection();
    UserDAO userDAO = new UserDAO();

    public List<Post> getMainPosts() {
        List<Post> mainPosts = new ArrayList<>();
        List<Post> posts = getPosts();
        for (int i = posts.size() - 1; i > posts.size() - 5; i--) {
            mainPosts.add(posts.get(i));
        }
        return mainPosts;
    }

    public List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from news");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(new Post(
                       rs.getInt("id"),
                        userDAO.getUserById(rs.getInt("author_id")),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getDate("date"),
                        rs.getString("theme")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
