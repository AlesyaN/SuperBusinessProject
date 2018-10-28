package entities;

public class Like {
    private int id;
    private User user;
    private Post post;
    private boolean type;

    public Like(int id, User user, Post post, boolean type) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.type = type;
    }

    public int getId() {
        return id;
    }
}
