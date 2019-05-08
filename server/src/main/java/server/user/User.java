package server.user;

import engine.geometry.Vector;
import game.player.UserPlayer;

public class User {

    private final UserPlayer userPlayer;

    User(UserPlayer userPlayer) {
        this.userPlayer = userPlayer;
    }

    public int getId() {
        return userPlayer.getId();
    }

    public Vector getPosition() {
        return userPlayer.getPosition();
    }
}
