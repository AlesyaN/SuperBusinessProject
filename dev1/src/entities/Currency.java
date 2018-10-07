package entities;

public class Currency {
    private int id;
    private String name;
    private Double value;
    private boolean status;

    public Currency(int id, String name, Double value, boolean status) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.status = status;
    }
}
