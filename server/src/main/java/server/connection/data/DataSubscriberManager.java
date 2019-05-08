package server.connection.data;

import engine.geometry.Vector;
import engine.object.GameObject;
import engine.visualizer.Publisher;
import server.exception.NoSuchUserException;
import server.user.User;
import server.user.UserManager;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

public class DataSubscriberManager implements Publisher {

    private final Collection<DataSubscriber> subscribers;

    private final UserManager userManager;

    public DataSubscriberManager(UserManager userManager) {
        this.subscribers = new HashSet<>();
        this.userManager = userManager;
    }

    void addVisualSubscriber(int userId, Socket socket) throws IOException, NoSuchUserException {
        User user = userManager.getUser(userId);
        synchronized (subscribers) {
            subscribers.add(new DataSubscriber(user, socket));
        }
    }

    void terminate() throws IOException {
        synchronized (subscribers) {
            for (DataSubscriber subscriber : subscribers) {
                subscriber.terminate();
            }
        }
    }

    @Override
    public void publish(Collection<GameObject> gameObjects) {
        synchronized (subscribers) {
            for (DataSubscriber dataSubscriber : subscribers) {
                User user = dataSubscriber.getUser();

                String message = createMessageForUser(user, gameObjects);

                dataSubscriber.sendMessage(message);
            }
        }
    }

    private String createMessageForUser(User user, Collection<GameObject> gameObjects) {
        Vector pos = user.getPosition();

        return pos.getX() + ":" + pos.getY() + ";" +
                createMessage(gameObjects);
    }

    private String createMessage(Collection<GameObject> gameObjects) {
        StringBuilder stringBuilder = new StringBuilder();

        for(GameObject gameObject: gameObjects) {
            stringBuilder.append(GameObjectEncoder.encode(gameObject));
            stringBuilder.append(";");
        }

        return stringBuilder.toString();
    }
}
