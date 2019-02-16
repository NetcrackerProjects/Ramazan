package physic;

import geometry.Point;
import geometry.Rectangle;
import object.GameObject;
import object.GameObjectManager;

import java.util.Collection;
import java.util.HashSet;

public class PhysicManager {

    private Rectangle field;

    private GameObjectManager gameObjectManager;

    public PhysicManager(Rectangle field, GameObjectManager gameObjectManager) {
        this.field = field;
        this.gameObjectManager = gameObjectManager;
    }

    public void move() {
        for (GameObject gameObject : gameObjectManager.getGameObjects()) {
            if (canMove(gameObject)) {
                gameObject.move();
            }
        }
    }

    public boolean canAddObject(GameObject gameObject) {
        Rectangle gameObjectBody = gameObject.getBody();

        if (isOutOfGameField(gameObjectBody)) {
            return false;
        }

        if (doesIntersectsPermeableGameObjects(gameObjectBody, gameObjectManager.getGameObjects())) {
            return false;
        }

        return true;
    }

    private boolean canMove(GameObject gameObject) {
        if (gameObject.isPermeable()) {
            return true;
        }

        Point speed = gameObject.getSpeed();

        Rectangle objectMovedBody = new Rectangle(gameObject.getBody());
        objectMovedBody.shift(speed);

        if (isOutOfGameField(objectMovedBody)) {
            return false;
        }

        if (doesIntersectsPermeableGameObjectsExceptItself(gameObject)) {
            return false;
        }

        return true;
    }

    private boolean doesIntersectsPermeableGameObjectsExceptItself(GameObject gameObject) {
        Collection<GameObject> gameObjectsWithoutException = new HashSet<>(gameObjectManager.getGameObjects());
        gameObjectsWithoutException.remove(gameObject);

        return doesIntersectsPermeableGameObjects(gameObject.getBody(), gameObjectsWithoutException);
    }

    private boolean doesIntersectsPermeableGameObjects(Rectangle rectangle, Collection<GameObject> objectsToIntersect) {
        for (GameObject gameObject : objectsToIntersect) {
            if (gameObject.isPermeable()) {
                continue;
            }
            if (gameObject.doesIntersect(rectangle)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOutOfGameField(Rectangle objectBody) {
        return !field.covers(objectBody);
    }
}
