package server.user;

import database.UserRepository;
import database.exception.RepositoryException;
import game.player.User;
import server.exception.NoSuchUserException;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<Integer, User> usersById;

    private final Map<String, User> usersByName;

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.usersById = new HashMap<>();
        this.usersByName = new HashMap<>();
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        usersById.put(user.getId(), user);
        usersByName.put(user.getName(), user);
    }

    public User getUser(int id) throws NoSuchUserException {
        User user = usersById.get(id);

        if (user == null) {
            throw new NoSuchUserException();
        }

        return user;
    }

    public User getUser(String name) throws NoSuchUserException {
        User user = usersByName.get(name);

        if (user == null) {
            throw new NoSuchUserException();
        }

        return user;
    }

    public boolean exists(String name) {
        User user = usersByName.get(name);

        return user != null;
    }

    public void saveUser(int id) throws NoSuchUserException, RepositoryException {
        User user = getUser(id);
        userRepository.add(user);
    }
}
