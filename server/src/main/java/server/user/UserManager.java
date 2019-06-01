package server.user;

import database.Repository;
import database.UserInformation;
import server.exception.NoSuchUserException;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<Integer, User> users;

    private final Repository repository;

    public UserManager(Repository repository) {
        this.users = new HashMap<>();
        this.repository = repository;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(int id) throws NoSuchUserException {
        User user = users.get(id);

        if (user == null) {
            throw new NoSuchUserException();
        }

        return user;
    }

    public void saveUser(int id) {
        try {
            User user = getUser(id);
            repository.save(new UserInformation(user.getName(), user.getHealth()));
        } catch (NoSuchUserException ignored) {
        }
    }
}
