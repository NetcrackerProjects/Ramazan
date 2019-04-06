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
}
