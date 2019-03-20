package game.player;

import game.object.Tank;

public class Player {

    private final Tank tank;

    private final int id;

    Player(Tank tank, int id) {
        this.tank = tank;
        this.id = id;
    }

    public Tank getTank() {
        return tank;
    }

    int getId() {
        return id;
    }
}
