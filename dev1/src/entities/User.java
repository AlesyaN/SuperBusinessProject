package entities;

import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class User {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String education;
    private Integer experience;
    private String scope;
    private String position;
    private String email;
    private String password;
    private String picPath;

    public User(int id, String surname, String name, String patronymic, Date dateOfBirth, String placeOfBirth, String education, Integer experience, String scope, String position, String email, String password, String picPath) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.education = education;
        this.experience = experience;
        this.scope = scope;
        this.position = position;
        this.email = email;
        this.password = password;
        this.picPath = picPath;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return surname + " " + name + " " + patronymic;
    }

    public int getId() {
        return id;
    }

    public String getDateOfBirthToString() {
        if (dateOfBirth != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.format(dateOfBirth);
        } else {
            return "";
        }
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public Integer getExperience() {
        return experience;
    }

    public String getScope() {
        return scope;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

}
