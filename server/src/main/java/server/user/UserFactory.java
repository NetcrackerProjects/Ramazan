package server.user;

import database.Repository;
import database.UserInformation;
import database.exception.FailedDataBaseQueryException;
import game.exception.FailedCreateUserPlayerException;
import game.player.UserPlayer;
import game.player.UserPlayerFactory;
import server.exception.FailedCreateUserException;

public class UserFactory {

    private final UserPlayerFactory userPlayerFactory;

    private final Repository repository;

    public UserFactory(UserPlayerFactory userPlayerFactory, Repository repository) {
        this.userPlayerFactory = userPlayerFactory;
        this.repository = repository;
    }

    public User createUser(String name) throws FailedCreateUserException {
        try {
            if (repository.exists(name)) {
                UserInformation userInformation = repository.getByName(name);
                UserPlayer player = userPlayerFactory.createPlayer(userInformation.getHealth());
                return new User(name, player);
            }
            else {
                UserPlayer player = userPlayerFactory.createPlayer();
                repository.save(new UserInformation(name, player.getTank().getHealth()));

                return new User(name, player);
            }
        } catch (FailedCreateUserPlayerException | FailedDataBaseQueryException e) {
            throw new FailedCreateUserException();
        }
    }
}
