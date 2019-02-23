package physic;

import geometry.Vector;
import geometry.Rectangle;
import object.GameObject;
import object.GameObjectManager;

import java.util.Collection;
import java.util.HashSet;

public class PhysicManager {

    private final Rectangle field;

    private final Collection<GameObject> gameObjects;

    public PhysicManager(Rectangle field, GameObjectManager gameObjectManager) {
        this.field = field;
        this.gameObjects = gameObjectManager.getGameObjects();
    }

    public void move() {
        for (GameObject gameObject : gameObjects) {
            if (canMove(gameObject) || gameObject.isPermeable()) {
                gameObject.move();
            }
        }
    }

    public boolean canAddObject(GameObject gameObject) {
        if (isOutOfGameField(gameObject.getBody())) {
            return false;
        }

        if (doesIntersectsGameObjects(gameObject, gameObjects)) {
            return false;
        }

        return true;
    }

    private boolean canMove(GameObject gameObject) {
        Vector speed = gameObject.getSpeed();

        Rectangle objectMovedBody = new Rectangle(gameObject.getBody());
        objectMovedBody.shift(speed);

        if (isOutOfGameField(objectMovedBody)) {
            return false;
        }

        if (doesIntersectsGameObjectsExcept(gameObject)) {
            return false;
        }

        return true;
    }

    private boolean doesIntersectsGameObjectsExcept(GameObject gameObject) {
        Collection<GameObject> gameObjectsWithoutException = new HashSet<>(gameObjects);
        gameObjectsWithoutException.remove(gameObject);

        return doesIntersectsGameObjects(gameObject, gameObjectsWithoutException);
    }

    private boolean doesIntersectsGameObjects(GameObject gameObject, Collection<GameObject> objectsToIntersect) {
        for (GameObject object : objectsToIntersect) {
            if (object.isPermeable()) {
                continue;
            }
            if (object.doesIntersect(gameObject.getBody())) {
                return true;
            }
        }
        return false;
    }

    private boolean isOutOfGameField(Rectangle objectBody) {
        return !field.covers(objectBody);
    }
}
