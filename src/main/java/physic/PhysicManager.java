package physic;

import geometry.Rectangle;
import geometry.Vector;
import interaction.Interaction;
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

    public Collection<Interaction> move() {
        Collection<Interaction> events = new HashSet<>();
        for (GameObject gameObject : gameObjects) {

            if (canMove(gameObject)) {
                gameObject.move();
            }
        }
        return events;
    }

    public boolean canAddObject(GameObject gameObject) {
        if (isOutOfGameField(gameObject.getBody())) {
            return false;
        }

        return !doesIntersectsGameObjects(gameObject.getBody(), gameObjects);
    }

    private boolean canMove(GameObject gameObject) {
        Vector speed = gameObject.getSpeed();

        Rectangle objectMovedBody = new Rectangle(gameObject.getBody());
        objectMovedBody.shift(speed);

        if (isOutOfGameField(objectMovedBody)) {
            return false;
        }

        return !doesIntersectsGameObjectsExcept(gameObject, objectMovedBody);
    }

    private boolean doesIntersectsGameObjectsExcept(GameObject gameObject, Rectangle gameBody) {
        Collection<GameObject> gameObjectsWithoutException = new HashSet<>(gameObjects);
        gameObjectsWithoutException.remove(gameObject);

        return doesIntersectsGameObjects(gameBody, gameObjectsWithoutException);
    }

    private boolean doesIntersectsGameObjects(Rectangle gameBody, Collection<GameObject> objectsToIntersect) {
        for (GameObject object : objectsToIntersect) {
            if (object.doesIntersect(gameBody)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOutOfGameField(Rectangle objectBody) {
        return !field.covers(objectBody);
    }
}
