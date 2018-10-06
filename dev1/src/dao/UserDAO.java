package dao;

import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    Connection connection = ConnectionSingleton.getConnection();

    public User getUserByLogin(String login) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"user\" where login=?");
            ps.setString(1, login);
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
              rs.getString("login"),
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
}
