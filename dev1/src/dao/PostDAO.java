package dao;

import entities.Post;
import helpers.Helper;
import javafx.geometry.Pos;
import javafx.scene.shape.HLineTo;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MultipartConfig
public class PostDAO {
    Connection connection = ConnectionSingleton.getConnection();
    UserDAO userDAO = new UserDAO();

    public List<Post> getMainPosts() {
        List<Post> mainPosts = new ArrayList<>();
        List<Post> posts = getPosts();
        for (int i = posts.size() - 1; i > posts.size() - 6; i--) {
            mainPosts.add(posts.get(i));
        }
        return mainPosts;
    }

    public List<Post> getPosts() {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from news ORDER BY \"date\"");
            ResultSet rs = ps.executeQuery();
            return Helper.makeORMListOfPosts(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> getNews() {
        List<Post> posts = getPosts();
        List<Post> news = new ArrayList<>();
        for (Post post: posts) {
            if (post.getTheme().equals("news")) {
                news.add(post);
            }
        }
        return news;
    }

    public List<Post> getAnalysis() {
        List<Post> posts = getPosts();
        List<Post> analysis = new ArrayList<>();
        for (Post post: posts) {
            if (post.getTheme().equals("analysis")) {
                analysis.add(post);
            }
        }
        return analysis;
    }

    public Post getPostById(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from news where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return Helper.makeORMPost(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer savePost(String title, String text, Part filePart, String path, int author_id, String fileName) {
        path = path + File.separator + "analysis";
        File dir = new File(path);
        dir.mkdirs();
        OutputStream out = null;
        InputStream filecontent = null;
        String ext = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(ext);
        String filename = System.currentTimeMillis() + "";
        String fullpath = path + File.separator + filename + ext;
        try {
            try {
                out = new FileOutputStream(new File(fullpath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (FileNotFoundException fne) {
                fne.printStackTrace();}
        catch (IOException e) {
            e.printStackTrace();
        }
        } finally {
            try {
            if (out != null) {

                    out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try {
            PreparedStatement ps = connection.prepareStatement("insert into news (author_id, title, \"date\", text, theme, pic_path)" +
                    "values (?,?,'now',?,'analysis',?) returning id");
            ps.setInt(1, author_id);
            ps.setString(2, title);
            ps.setString(3, text);
            ps.setString(4, "/files/analysis/" + filename + ext);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst()) {
                rs.next();
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> getPostsByTitleMask(String query) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select * from news where title ilike ?"
            );
            ps.setString(1, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            return Helper.makeORMListOfPosts(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
