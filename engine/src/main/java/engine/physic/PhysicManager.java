package engine.physic;

import engine.exception.AddObjectException;
import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.interaction.Interaction;
import engine.object.GameField;
import engine.object.GameObject;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PhysicManager {

    private final GameField field;
    private final Collection<GameObject> gameObjects;
    private Set<Interaction> interactions;

    public PhysicManager(GameField field) {
        this.field = field;
        this.gameObjects = new HashSet<>();
    }

    public void applyForces() {
        for (GameObject gameObject : gameObjects) {
            Vector friction = new Vector(gameObject.getSpeed());
            friction.scale(GameField.frictionCoefficient);

            gameObject.accelerate(friction);
        }
    }

    public Collection<Interaction> move() {
        this.interactions = new HashSet<>();

        for (GameObject gameObject : gameObjects) {
            Collection<GameObject> intersectedObjects = moveGameObject(gameObject);
            addNewInteractions(gameObject, intersectedObjects);
        }

        return interactions;
    }

    public void removePhysicalObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void addPhysicObject(GameObject gameObject) throws AddObjectException {
        if (canAddObject(gameObject)) {
            gameObjects.add(gameObject);
            return;
        }

        throw new AddObjectException();
    }

    private boolean canAddObject(GameObject gameObject) {
        if (isOutOfGameField(gameObject.getBody())) {
            return false;
        }

        return !doesIntersectsGameObjects(gameObject.getBody(), gameObjects);
    }

    private Collection<GameObject> moveGameObject(GameObject gameObject) {
        Rectangle objectMovedBody = gameObject.getMovedBody();

        if (isOutOfGameField(objectMovedBody)) {
            return Collections.emptySet();
        }

        Collection<GameObject> intersectedObjects = getIntersectedGameObjects(gameObject, objectMovedBody);

        if (intersectedObjects.isEmpty()) {
            gameObject.move();
        }

        return intersectedObjects;
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
