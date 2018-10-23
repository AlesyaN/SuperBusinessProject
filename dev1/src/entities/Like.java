package entities;

public class Like {
    private int id;
    private User user;
    private Post post;

    public Like(int id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public int getId() {
        return id;
    }
}
