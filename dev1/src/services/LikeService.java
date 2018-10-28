package services;

import dao.LikeDAO;
import entities.Like;
import entities.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LikeService {
    LikeDAO likeDAO = new LikeDAO();
    UserService userService = new UserService();

    public List<Like> getLikesByPost(Post post, boolean type) {
        return likeDAO.getLikesByPost(post, type);
    }

    public boolean toggle(HttpServletRequest request, boolean type) {
        if (userService.getCurrentUser(request) != null) {
            Like like = likeDAO.getLikeOnPost(Integer.parseInt(request.getParameter("post-id")), userService.getCurrentUser(request), type);
            if (like != null) {
                likeDAO.delete(like);
                return false;
            } else {
                likeDAO.add(Integer.parseInt(request.getParameter("post-id")), userService.getCurrentUser(request), type);
                return true;
            }
        }
        return false;
    }

    public boolean delete(HttpServletRequest request, boolean type) {
        if (userService.getCurrentUser(request) != null) {
            Like like = likeDAO.getLikeOnPost(Integer.parseInt(request.getParameter("post-id")), userService.getCurrentUser(request), type);
            if (like != null) {
                likeDAO.delete(like);
                return true;
            } else {
                return false;
            }

        }
        return false;
    }
}
