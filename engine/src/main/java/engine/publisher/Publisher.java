package engine.publisher;

import engine.object.GameObject;

import java.util.Collection;

public interface Publisher {

    void publish(Collection<GameObject> gameObjects);
}
