package physic;

import geometry.Rectangle;
import interaction.Interaction;
import object.GameObject;
import object.GameObjectManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PhysicManager {

    private final Rectangle field;
    private final Collection<GameObject> gameObjects;
    private Set<Interaction> interactions;

    public PhysicManager(Rectangle field, GameObjectManager gameObjectManager) {
        this.field = field;
        this.gameObjects = gameObjectManager.getGameObjects();
    }

    public Collection<Interaction> move() {
        this.interactions = new HashSet<>();
        for (GameObject gameObject : gameObjects) {
            tryMoveOrRegisterInteractions(gameObject);
        }
        return interactions;
    }

    public boolean canAddObject(GameObject gameObject) {
        if (isOutOfGameField(gameObject.getBody())) {
            return false;
        }

        return !doesIntersectsGameObjects(gameObject.getBody(), gameObjects);
    }

    private void tryMoveOrRegisterInteractions(GameObject gameObject) {
        Rectangle objectMovedBody = gameObject.getMovedBody();

        if (isOutOfGameField(objectMovedBody)) {
            return;
        }

        Collection<GameObject> intersectedObjects = getIntersectedGameObjects(gameObject, objectMovedBody);

        if (intersectedObjects.size() == 0) {
            gameObject.move();
            return;
        }

        addNewInteractions(gameObject, intersectedObjects);
    }

    private void addNewInteractions(GameObject gameObject, Collection<GameObject> intersectedObjects) {
        for (GameObject intersectedObject : intersectedObjects) {
            interactions.add(new Interaction(gameObject, intersectedObject));
        }
    }

    private boolean doesIntersectsGameObjects(Rectangle gameBody, Collection<GameObject> objectsToIntersect) {
        for (GameObject object : objectsToIntersect) {
            if (object.doesIntersect(gameBody)) {
                return true;
            }
        }
        return false;
    }

    private Collection<GameObject> getIntersectedGameObjects(GameObject movingObject, Rectangle movedBody) {
        Collection<GameObject> intersected = new HashSet<>();
        for (GameObject object : gameObjects) {
            if (object == movingObject) {
                continue;
            }

            if (object.doesIntersect(movedBody)) {
                intersected.add(object);
            }
        }
        return intersected;
    }

    private boolean isOutOfGameField(Rectangle objectBody) {
        return !field.covers(objectBody);
    }
}