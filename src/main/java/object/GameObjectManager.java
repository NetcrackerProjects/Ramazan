package object;

import java.util.Collection;
import java.util.HashSet;

public class GameObjectManager {

    private Collection<GameObject> gameObjects;

    public GameObjectManager() {
        gameObjects = new HashSet<>();
    }

    public Collection<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
