package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private int id;
    private User author;
    private Post post;
    private Date date;
    private String text;

    public Comment(int id, User author, Post post, Date date, String text) {
        this.id = id;
        this.author = author;
        this.post = post;
        this.date = date;
        this.text = text;
    }

    public String getDateToString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return df.format(date);
    }

    public int getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Post getPost() {
        return post;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
