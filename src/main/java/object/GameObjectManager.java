package object;

import java.util.Collection;
import java.util.HashSet;

public class GameObjectManager {

    private final Collection<GameObject> gameObjects;

    public GameObjectManager() {
        this.gameObjects = new HashSet<>();
    }

    public Collection<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
