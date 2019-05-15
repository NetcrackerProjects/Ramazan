package engine.physic;

import engine.exception.AddObjectException;
import engine.exception.GetFreePositionFailedException;
import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.interaction.Interaction;
import engine.object.GameField;
import engine.object.GameObject;
import engine.utils.GameRandomizer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PhysicManager {


    private static final int MAX_ATTEMPT = 20;

    private final GameField field;
    private final Collection<GameObject> gameObjects;
    private Set<Interaction> interactions;

    public PhysicManager(GameField field) {
        this.field = field;
        this.gameObjects = new HashSet<>();
    }

    public Collection<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void applyForces() {
        for (GameObject gameObject : gameObjects) {
            Vector friction = new Vector(gameObject.getSpeed());
            friction.scale(-GameField.frictionCoefficient);

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

    public Vector getFreePositionForRectangle(Vector size) throws GetFreePositionFailedException {
        int attempts = 0;

        while(attempts < MAX_ATTEMPT) {
            Rectangle space = new Rectangle(GameRandomizer.getRandomVectorAtGameField(field), size);

            if (isFreeSpace(space)) {
                return space.getTopLeft();
            }

            attempts++;
        }

        throw new GetFreePositionFailedException();
    }

    private boolean isFreeSpace(Rectangle rectangle) {
        if (isOutOfGameField(rectangle)) {
            return false;
        }

        Collection<GameObject> intersectedObjects = getIntersectedGameObjects(null, rectangle);

        return intersectedObjects.isEmpty();
    }

    private boolean canAddObject(GameObject gameObject) {
        if (isOutOfGameField(gameObject.getBody())) {
            return false;
        }

        if (!gameObject.isSolid()) {
            return true;
        }

        Collection<GameObject> intersectedObjects = getIntersectedGameObjects(gameObject, gameObject.getBody());

        return !(containsSolid(intersectedObjects) && gameObject.isSolid());
    }

    private Collection<GameObject> moveGameObject(GameObject gameObject) {
        Rectangle objectMovedBody = gameObject.getMovedBody();

        if (isOutOfGameField(objectMovedBody)) {
            return Collections.emptySet();
        }

        Collection<GameObject> intersectedObjects = getIntersectedGameObjects(gameObject, objectMovedBody);

        if (intersectedObjects.isEmpty() ||
                (!containsSolid(intersectedObjects) || !gameObject.isSolid())) {
            gameObject.move();
        }

        return intersectedObjects;
    }

    private boolean containsSolid(Collection<GameObject> gameObjects) {
        for (GameObject gameObject : gameObjects) {
            if (gameObject.isSolid()) {
                return true;
            }
        }

        return false;
    }

    private void addNewInteractions(GameObject gameObject, Collection<GameObject> intersectedObjects) {
        for (GameObject intersectedObject : intersectedObjects) {
            interactions.add(new Interaction(gameObject, intersectedObject));
        }
    }

    private Collection<GameObject> getIntersectedGameObjects(GameObject intersectedObject, Rectangle objectBody) {
        Collection<GameObject> intersected = new HashSet<>();

        for (GameObject object : gameObjects) {
            if (object == intersectedObject) {
                continue;
            }

            if (object.doesIntersect(objectBody)) {
                intersected.add(object);
            }
        }

        return intersected;
    }

    private boolean isOutOfGameField(Rectangle objectBody) {
        return !field.covers(objectBody);
    }
}
