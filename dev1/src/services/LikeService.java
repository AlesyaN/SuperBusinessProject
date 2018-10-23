package services;

import dao.LikeDAO;
import entities.Like;
import entities.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LikeService {
    LikeDAO likeDAO = new LikeDAO();
    UserService userService = new UserService();

    public List<Like> getLikesByPost(Post post) {
        return likeDAO.getLikesByPost(post);
    }

    public boolean toggleLike(HttpServletRequest request) {
        Like like = likeDAO.getLikeOnPost(Integer.parseInt(request.getParameter("post-id")), userService.getCurrentUser(request));
        if (like != null) {
            likeDAO.deleteLike(like);
            return false;
        } else {
            likeDAO.addLike(Integer.parseInt(request.getParameter("post-id")), userService.getCurrentUser(request));
            return true;
        }
    }
}
