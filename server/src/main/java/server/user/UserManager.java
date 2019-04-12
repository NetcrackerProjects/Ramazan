package server.user;

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

    public User getUser(int id) {
        User user = users.get(id);

        if (user == null) {
            throw new IllegalArgumentException();
        }

        return user;
    }
}
