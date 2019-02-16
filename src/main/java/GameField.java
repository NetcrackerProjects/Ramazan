import collision.Collision;
import exception.ObjectIntersectionException;
import exception.OutOfBoundaryException;
import object.GameObject;
import geometry.Point;
import geometry.Rectangle;

import java.util.Collection;
import java.util.HashSet;

class GameField {

    private Rectangle field;

    private Collection<GameObject> gameObjects;

    private Collection<Collision> collisions;

    GameField(Point leftTop, Point rightBottom) {
        this.field = new Rectangle(leftTop, rightBottom);
        this.gameObjects = new HashSet<>();
        this.collisions = new HashSet<>();
    }

    void addObject(GameObject gameObject) throws Exception {
        Rectangle objectBody = gameObject.getBody();

        if (isOutOfGameField(objectBody)) {
            throw new OutOfBoundaryException();
        }

        if (!gameObject.isPermeable()) {
            if (doesIntersectsPermeableGameObjects(objectBody, gameObjects)) {
                throw new ObjectIntersectionException();
            }
        }

        gameObjects.add(gameObject);
    }

    void update() {
        move();
        processCollisions();
    }

    private void move() {
        for (GameObject gameObject : gameObjects) {
            if (canMove(gameObject)) {
                gameObject.move();
            }
        }
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

        if (doesIntersectsPermeableGameObjectsExcept(objectMovedBody, gameObject)) {
            return false;
        }

        return true;
    }

    private void processCollisions() {
        registerCollisions();
        carryOutRegisteredCollisions();
        clearRegisteredCollisions();
    }

    private void registerCollisions() {
        collisions.clear();
        for (GameObject gameObject : gameObjects) {
            registerCollision(gameObject);
        }
    }

    private void carryOutRegisteredCollisions() {
        for (Collision collision : collisions) {
            collision.carryOut();
        }
    }

    private void clearRegisteredCollisions() {
        collisions.clear();
    }

    private void registerCollision(GameObject gameObject) {
        for (GameObject otherGameObject : gameObjects) {
            if (otherGameObject.equals(gameObject)) {
                continue;
            }

            if (otherGameObject.doesIntersect(gameObject.getBody())) {
                collisions.add(new Collision(gameObject, otherGameObject));
            }
        }
    }

    private boolean doesIntersectsPermeableGameObjectsExcept(Rectangle rectangle, GameObject exception) {
        Collection<GameObject> gameObjectsWithoutException = new HashSet<>(gameObjects);
        gameObjectsWithoutException.remove(exception);

        return doesIntersectsPermeableGameObjects(rectangle, gameObjectsWithoutException);
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
