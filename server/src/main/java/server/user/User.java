package server.user;

import engine.geometry.Vector;
import game.player.UserPlayer;

public class User {

    private final String name;
    private final UserPlayer userPlayer;

    User(String name, UserPlayer userPlayer) {
        this.name = name;
        this.userPlayer = userPlayer;
    }

    public int getId() {
        return userPlayer.getId();
    }

    public Vector getPosition() {
        return userPlayer.getPosition();
    }

    String getName() {
        return name;
    }

    int getHealth() {
        return userPlayer.getTank().getHealth();
    }
}
