package server.user;

import engine.geometry.Vector;
import game.player.UserPlayer;

public class User {

    private final UserPlayer userPlayer;

    private final Vector monitorSize;

    User(UserPlayer userPlayer, Vector monitorSize) {
        this.userPlayer = userPlayer;
        this.monitorSize = monitorSize;
    }

    public int getId() {
        return userPlayer.getId();
    }

    public Vector getMonitorSize() {
        return monitorSize;
    }
}
