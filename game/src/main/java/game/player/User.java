package game.player;

import engine.player.Player;

public class User implements Player {

    private final int tankId;

    private final int id;

    private final String name;

    public User(int id, int tankId, String name) {
        this.tankId = tankId;
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getObjectId() {
        return tankId;
    }

    public String getName() {
        return name;
    }
}
