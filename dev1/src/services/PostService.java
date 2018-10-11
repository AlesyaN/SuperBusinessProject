package services;

import dao.PostDAO;
import entities.Post;

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
}
