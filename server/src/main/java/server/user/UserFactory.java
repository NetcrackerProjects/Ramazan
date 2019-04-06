package server.user;

import engine.geometry.Vector;
import game.player.UserPlayer;
import game.player.UserPlayerFactory;

public class UserFactory {

    private final UserPlayerFactory userPlayerFactory;

    public UserFactory(UserPlayerFactory userPlayerFactory) {
        this.userPlayerFactory = userPlayerFactory;
    }

    public User createUser(Vector monitorSize) {
        UserPlayer player = userPlayerFactory.createPlayer();

        return new User(player, monitorSize);
    }
}
