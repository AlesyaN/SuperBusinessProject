package helpers;

import dao.PostDAO;
import dao.UserDAO;
import entities.Comment;
import entities.Like;
import entities.Post;
import entities.User;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    private static Configuration cfg = null;
    private static UserDAO userDAO = new UserDAO();
    private static PostDAO postDAO = new PostDAO();

    public static Configuration getConfig(ServletContext sc) {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_22);
            cfg.setServletContextForTemplateLoading(sc, "/WEB-INF/templates");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        }
        return cfg;

    }

    public static List<Comment> makeORMListOfComments(ResultSet rs) {
        List<Comment> comments = new ArrayList<>();
        try {
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        userDAO.getUserById(rs.getInt("author_id")),
                        postDAO.getPostById(rs.getInt("news_id")),
                        rs.getTimestamp("date"),
                        rs.getString("text")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static List<Like> makeORMListOfLikes(ResultSet rs) {
        List<Like> likes = new ArrayList<>();
        try {
            while (rs.next()) {
                likes.add(new Like(
                        rs.getInt("id"),
                        (new UserDAO()).getUserById(rs.getInt("author_id")),
                        (new PostDAO()).getPostById(rs.getInt("news_id")),
                        rs.getBoolean("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likes;
    }

    public static Like makeORMLike(ResultSet rs) {
        try {
            if (!rs.isBeforeFirst()) {
                return null;

            } else {
                rs.next();
                return new Like(rs.getInt("id"),
                        userDAO.getUserById(rs.getInt("author_id")),
                        postDAO.getPostById(rs.getInt("news_id")),
                        rs.getBoolean("type"));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Post> makeORMListOfPosts(ResultSet rs) {
        List<Post> posts = new ArrayList<>();
        try {
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("id"),
                        userDAO.getUserById(rs.getInt("author_id")),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getTimestamp("date"),
                        rs.getString("theme"),
                        rs.getString("pic_path")
                ));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static Post makeORMPost(ResultSet rs) {
        try {
            rs.next();
            return new Post(
                    rs.getInt("id"),
                    userDAO.getUserById(rs.getInt("author_id")),
                    rs.getString("title"),
                    rs.getString("text"),
                    rs.getTimestamp("date"),
                    rs.getString("theme"),
                    rs.getString("pic_path")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User makeORMUser(ResultSet rs) {
        try {
            rs.next();
            return new User(
                    rs.getInt("id"),
                    rs.getString("surname"),
                    rs.getString("name"),
                    rs.getString("patronymic"),
                    rs.getDate("date_of_birth"),
                    rs.getString("place_of_birth"),
                    rs.getString("education"),
                    rs.getInt("experience"),
                    rs.getString("scope"),
                    rs.getString("position"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("pic_path")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> makeORMListOfUsers(ResultSet rs) {
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronymic"),
                        rs.getDate("date_of_birth"),
                        rs.getString("place_of_birth"),
                        rs.getString("education"),
                        rs.getInt("experience"),
                        rs.getString("scope"),
                        rs.getString("position"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("pic_path")
                ));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
