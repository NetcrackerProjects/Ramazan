package server.user;

import engine.geometry.Vector;
import game.exception.FailedCreateUserPlayerException;
import game.player.UserPlayer;
import game.player.UserPlayerFactory;
import server.exception.FailedCreateUserException;

public class UserFactory {

    private final UserPlayerFactory userPlayerFactory;

    public UserFactory(UserPlayerFactory userPlayerFactory) {
        this.userPlayerFactory = userPlayerFactory;
    }

    public User createUser(Vector monitorSize) throws FailedCreateUserException {
        try {
            UserPlayer player = userPlayerFactory.createPlayer();
            return new User(player, monitorSize);
        } catch (FailedCreateUserPlayerException e) {
            throw new FailedCreateUserException();
        }
    }
}
