package services;

import dao.PostDAO;
import entities.Post;

import java.util.List;

public class PostService {
    PostDAO postDAO = new PostDAO();

    public List<Post> getMainPosts() {
        return postDAO.getMainPosts();
    }
}
