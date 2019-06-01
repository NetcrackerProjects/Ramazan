package database;

public class UserInformation {

    private String name;
    private int health;

    public UserInformation(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    String getName() {
        return name;
    }
}
