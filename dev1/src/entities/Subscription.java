package entities;

public class Subscription {
    private int id;
    private User suscriptor;
    private User subscriber;

    public Subscription(int id, User suscriptor, User subscriber) {
        this.id = id;
        this.suscriptor = suscriptor;
        this.subscriber = subscriber;
    }
}
