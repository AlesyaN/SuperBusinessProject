package services;

import dao.LikeDAO;
import entities.Like;
import entities.Post;

import java.util.List;

public class LikeService {
    LikeDAO likeDAO = new LikeDAO();

    public List<Like> getLikesByPost(Post post) {
        return likeDAO.getLikesByPost(post);
    }
}
