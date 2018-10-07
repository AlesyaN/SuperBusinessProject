package entities;

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
}
