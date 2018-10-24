package services;

import dao.UserDAO;
import entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        return user;
    }

    public User authenticate(HttpServletRequest request) {
        String email = request.getParameter("email");
        if (email != null) {
            User user = userDAO.getUserByEmail(email);
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

    public boolean register(HttpServletRequest request, Part filePart, String path) {
        if (emailIsCorrect(request.getParameter("email")) && userDAO.emailIsUnique(request.getParameter("email"))) {
            userDAO.register(request, filePart, path);
            authorize(userDAO.getUserByEmail(request.getParameter("email")), request);
            return true;
        } else {
            return false;
        }
    }

    public boolean emailIsCorrect(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.find();
    }

    public boolean edit(HttpServletRequest request, Part filePart, String realPath) {
        if (emailIsCorrect(request.getParameter("email"))
                && userDAO.emailIsUnique(request.getParameter("email"), request)) {
            userDAO.edit(request, filePart, realPath);
            return true;
        } else {
            return false;
        }
    }


}
