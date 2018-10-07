package entities;

import java.util.Date;

public class Post {
    private int id;
    private User author;
    private String title;
    private String text;
    private Date date;

    public Post(int id, User author, String title, String text, Date date) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.date = date;
    }
}
