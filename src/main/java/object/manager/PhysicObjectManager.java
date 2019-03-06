package object.manager;

import object.GameObject;

import java.util.Collection;
import java.util.HashSet;

public class PhysicObjectManager {

    private final Collection<GameObject> gameObjects;

    public PhysicObjectManager() {
        this.gameObjects = new HashSet<>();
    }

    public Collection<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    void removeObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }
}