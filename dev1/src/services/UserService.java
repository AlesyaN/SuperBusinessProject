package services;

import dao.UserDAO;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        return user;
    }

    public User authenticate(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login != null) {
            User user = userDAO.getUserByLogin(login);
            if (user == null) {
                return null;
            }
            String password = request.getParameter("password");
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return null;
            }
        }

        return null;
    }

    public void authorize(User currentUser, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("current_user", currentUser);
    }
}
