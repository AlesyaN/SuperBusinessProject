package entities;

import java.util.Date;

public class Post {
    private int id;
    private User author;
    private String title;
    private String text;
    private Date date;
    private String theme;

    public User getAuthor() {
        return author;
    }

    public String getTheme() {
        return theme;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public Post(int id, User author, String title, String text, Date date, String theme) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.date = date;
        this.theme = theme;
    }

    public int getId() {
        return id;
    }
}
