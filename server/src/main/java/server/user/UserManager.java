package server.user;

import server.exception.NoSuchUserException;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<Integer, User> users;

    public UserManager() {
        this.users = new HashMap<>();
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
}
