package server.connection.visual;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.object.GameObject;
import engine.visualizer.Visualizer;
import server.user.User;
import server.user.UserManager;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

public class VisualSubscriberManager implements Visualizer {

    private final Collection<VisualSubscriber> subscribers;

    private final UserManager userManager;

    public VisualSubscriberManager(UserManager userManager) {
        this.subscribers = new HashSet<>();
        this.userManager = userManager;
    }

    void addVisualSubscriber(int userId, Socket socket) throws IOException {
        User user = userManager.getUser(userId);
        synchronized (subscribers) {
            subscribers.add(new VisualSubscriber(user, socket));
        }
    }

    void terminate() throws IOException {
        synchronized (subscribers) {
            for (VisualSubscriber subscriber : subscribers) {
                subscriber.terminate();
            }
        }
    }

    @Override
    public void draw(Collection<GameObject> gameObjects) {
        synchronized (subscribers) {
            for (VisualSubscriber visualSubscriber : subscribers) {
                User user = visualSubscriber.getUser();

                String message = createMessageForUser(user, gameObjects);

                visualSubscriber.sendMessage(message);
            }
        }
    }

    private String createMessageForUser(User user, Collection<GameObject> gameObjects) {
        Vector pos = user.getPosition();
        Vector monitorSize = user.getMonitorSize();

        Rectangle monitorRectangle = new Rectangle(new Vector(pos.getX() - monitorSize.getX()/2,
                                                            pos.getY() - monitorSize.getY()/2),
                                                new Vector(pos.getX() + monitorSize.getX()/2,
                                                            pos.getY() + monitorSize.getY()/2));

        return pos.getX() + ":" + pos.getY() + ";" +
                createMessage(gameObjects, monitorRectangle);
    }

    private String createMessage(Collection<GameObject> gameObjects, Rectangle monitorRectangle) {
        StringBuilder stringBuilder = new StringBuilder();

        for(GameObject gameObject: gameObjects) {
            if (gameObject.doesIntersect(monitorRectangle)) {
                stringBuilder.append(GameObjectEncoder.encode(gameObject));
                stringBuilder.append(";");
            }
        }

        return stringBuilder.toString();
    }
}
