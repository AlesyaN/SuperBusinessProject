package dao;

import entities.User;
import helpers.Helper;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
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
            return Helper.makeORMUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<User> getUsers() {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"user\"");
            ResultSet rs = ps.executeQuery();
            return Helper.makeORMListOfUsers(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void register(HttpServletRequest request, Part filePart, String path) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("insert into \"user\"(surname, name," +
                    "patronymic, date_of_birth, place_of_birth, education," +
                    "position, email, password, scope, experience) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning id");
            ps.setString(1, request.getParameter("surname"));
            ps.setString(2, request.getParameter("name"));
            ps.setString(3, request.getParameter("patronymic"));

            if (!request.getParameter("dayOfBirth").equals("") &&
                    !request.getParameter("monthOfBirth").equals("") &&
                    !request.getParameter("yearOfBirth").equals("")) {
                ps.setDate(4, java.sql.Date.valueOf(request.getParameter("yearOfBirth") + "-" +
                                                                request.getParameter("monthOfBirth") + "-" +
                                                                request.getParameter("dayOfBirth")));
            } else {
                ps.setDate(4, null);
            }
            ps.setString(5, request.getParameter("placeOfBirth"));
            ps.setString(6, request.getParameter("education"));
            ps.setString(7, request.getParameter("position"));
            ps.setString(8, request.getParameter("email"));
            ps.setString(9, request.getParameter("password"));
            ps.setString(10, request.getParameter("scope"));
            if (!request.getParameter("experience").equals("")) {
                ps.setInt(11, Integer.parseInt(request.getParameter("experience")));
            } else {
                ps.setInt(11, 0);
            }
            ResultSet rs = ps.executeQuery();
            rs.next();
            int userId = rs.getInt("id");
            if (filePart != null && filePart.getSize() != 0) {
                savePic(filePart, path, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePic(Part filePart, String path, int userId) {
            File file = new File(path + File.separator + userId);
            file.mkdirs();
            OutputStream out = null;
            InputStream filecontent = null;
            String filename = getFileName(filePart);
        System.out.println(filename);
            String ext = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            String fileName = System.currentTimeMillis() + "";
            String fullpath = path + File.separator +  userId + File.separator + fileName + ext;
            System.out.println(fullpath);
            try {
                try {
                    out = new FileOutputStream(new File(fullpath));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    filecontent = filePart.getInputStream();
                    int read = 0;
                    final byte[] bytes = new byte[1024];

                    while ((read = filecontent.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }

                } catch (FileNotFoundException fne) {
                    fne.printStackTrace();}
                catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                try {
                    if (out != null) {

                        out.close();
                    }
                    if (filecontent != null) {
                        filecontent.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            PreparedStatement ps = null;
            try {
                ps = connection.prepareStatement("update \"user\" set pic_path=? where id=?");
                ps.setString(1, "/files/users/" + userId + "/" + fileName + ext);
                ps.setInt(2, userId);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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

    public boolean emailIsUnique(String email, HttpServletRequest request) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"user\" where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                return true;
            }
            rs.next();
            if (rs.getString("email").equals((new UserService().getCurrentUser(request).getEmail()))) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void edit(HttpServletRequest request, Part filePart, String realPath) {
        List<String> params = new ArrayList<>();
        params.add("email");
        params.add("password");
        params.add("name");
        params.add("surname");
        params.add("patronymic");
        params.add("date_of_birth");
        params.add("place_of_birth");
        params.add("education");
        params.add("experience");
        params.add("scope");
        params.add("position");
        params.add("file");
        try {
            String statement1  = "update \"user\" set ";
            String statement2 = "=? where id=?";
            PreparedStatement ps = null;
            for (String param : params) {
                ps = connection.prepareStatement(statement1 + param + statement2);
                if (param.equals("file")) {
                    if (filePart.getSize() != 0) {
                        savePic(filePart, realPath, (new UserService()).getCurrentUser(request).getId());
                    }
                } else {
                    String value = request.getParameter(param);
                    if (request.getParameter(param) != null &&!request.getParameter(param).equals("")) {
                        if (param.equals("date_of_birth")) {
                            ps.setDate(1, java.sql.Date.valueOf(value));
                        } else if (param.equals("experience")) {
                            ps.setInt(1, Integer.parseInt(value));
                        } else {
                            ps.setString(1, value);
                        }
                        ps.setInt(2, (new UserService()).getCurrentUser(request).getId());
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from \"user\" where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return Helper.makeORMUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
