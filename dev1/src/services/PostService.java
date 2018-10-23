package services;

import dao.PostDAO;
import entities.Post;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public class PostService {
    PostDAO postDAO = new PostDAO();

    public List<Post> getMainPosts() {
        return postDAO.getMainPosts();
    }

    public List<Post> getNews() {
        return postDAO.getNews();
    }

    public List<Post> getAnalysis() {
        return postDAO.getAnalysis();
    }

    public Post getPostById(int id) {
        return postDAO.getPostById(id);
    }

    public int savePost(String title, String text, Part filePart, String path, User currentUser, String fileName) {
        return postDAO.savePost(title, text, filePart, path, currentUser.getId(), fileName);

    }


}
