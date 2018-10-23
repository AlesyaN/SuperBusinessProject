package entities;

import freemarker.template.SimpleDate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int id;
    private User author;
    private String title;
    private String text;
    private Date date;
    private String theme;
    private String picPath;

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

    public String getDateToString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return df.format(date);
    }

    public Post(int id, User author, String title, String text, Date date, String theme, String picPath) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.date = date;
        this.theme = theme;
        this.picPath = picPath;
    }

    public int getId() {
        return id;
    }

    public String getPicPath() {
        return picPath;
    }
}
