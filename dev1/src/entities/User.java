package entities;

import java.util.Date;

public class User {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private Date dateOfBirth;
    private String placeOfBirth;
    private String education;
    private int experience;
    private String scope;
    private String position;
    private String login;
    private String password;

    public User(int id, String surname, String name, String patronymic, Date dateOfBirth, String placeOfBirth, String education, int experience, String scope, String position, String login, String password) {
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
        this.login = login;
        this.password = password;
    }




}
