package server.user;

import game.exception.FailedCreateUserPlayerException;
import game.player.UserPlayer;
import game.player.UserPlayerFactory;
import server.exception.FailedCreateUserException;

public class UserFactory {

    private final UserPlayerFactory userPlayerFactory;

    public UserFactory(UserPlayerFactory userPlayerFactory) {
        this.userPlayerFactory = userPlayerFactory;
    }

    public User createUser() throws FailedCreateUserException {
        try {
            UserPlayer player = userPlayerFactory.createPlayer();
            return new User(player);
        } catch (FailedCreateUserPlayerException e) {
            throw new FailedCreateUserException();
        }
    }
}
