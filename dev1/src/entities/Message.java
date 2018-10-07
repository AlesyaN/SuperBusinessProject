package entities;

import java.util.Date;

public class Message {
    private int id;
    private User sender;
    private User receiver;
    private String text;
    private Date date;

    public Message(int id, User sender, User receiver, String text, Date date) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.date = date;
    }
}
