package dao;

import entities.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDAO {
    Connection connection = ConnectionSingleton.getConnection();

    public User getUserByEmail(String email) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"user\" where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new User(
              rs.getInt("id"),
              rs.getString("surname"),
              rs.getString("name"),
              rs.getString("patronymic"),
              rs.getDate("date_of_birth"),
              rs.getString("place_of_birth"),
              rs.getString("education"),
              getExperienceByUserId(rs.getInt("id")),
              rs.getString("position"),
              rs.getString("email"),
              rs.getString("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Integer> getExperienceByUserId(int id) {
        Map<String, Integer> experience = new HashMap<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from scope_experience where user_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                experience.put(getScopeById(rs.getInt("scope_id")),
                               rs.getInt("experience"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experience;
    }

    private String getScopeById(int scope_id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from scope where id=?");
            ps.setInt(1, scope_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getLastUserId() {
        List<User> users = getUsers();
        users.sort((o1, o2) -> {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else {
                return -1;
            }
        });
        return users.get(users.size() - 1).getId();
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"user\"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronymic"),
                        rs.getDate("date_of_birth"),
                        rs.getString("place_of_birth"),
                        rs.getString("education"),
                        getExperienceByUserId(rs.getInt("id")),
                        rs.getString("position"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void register(HttpServletRequest request) {
            insertIntoUser(request);
            insertIntoSE(request);
    }

    private void insertIntoSE(HttpServletRequest request) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into " +
                    "scope_experience(user_id, experience, scope_id) " +
                    "values(?,?,?)");
            int i = 1;
            while (!request.getParameter("scope" + i).equals("")) {
                ps.setInt(1, getUserByEmail(request.getParameter("email")).getId());
                ps.setInt(2, Integer.parseInt(request.getParameter("experience" + i)));
                ps.setInt(3, getScopeIdByName(request.getParameter("scope" + i)));
                ps.execute();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int getScopeIdByName(String parameter) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from scope where name=?");
            ps.setString(1, parameter);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void insertIntoUser(HttpServletRequest request) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into \"user\"(surname, name," +
                    "patronymic, date_of_birth, place_of_birth, education," +
                    "position, email, password) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, request.getParameter("surname"));
            ps.setString(2, request.getParameter("name"));
            ps.setString(3, request.getParameter("patronymic"));
            ps.setDate(4, java.sql.Date.valueOf(request.getParameter("dateOfBirth")));
            ps.setString(5, request.getParameter("placeOfBirth"));
            ps.setString(6, request.getParameter("education"));
            ps.setString(7, request.getParameter("position"));
            ps.setString(8, request.getParameter("email"));
            ps.setString(9, request.getParameter("password"));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean emailIsUnique(String email) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"user\" where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
