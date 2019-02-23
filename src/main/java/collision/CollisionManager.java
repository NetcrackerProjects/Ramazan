package collision;

import object.GameObject;
import object.GameObjectManager;

import java.util.Collection;
import java.util.HashSet;

public class CollisionManager {

    private final Collection<Collision> collisions;
    private final Collection<GameObject> gameObjects;

    public CollisionManager(GameObjectManager gameObjectManager) {
        this.collisions = new HashSet<>();
        this.gameObjects = gameObjectManager.getGameObjects();
    }

    public void processCollisions() {
        registerCollisions();
        intersectCollidedGameObjects();
        collisions.clear();
    }

    private void registerCollisions() {
        for (GameObject gameObject : gameObjects) {
            registerCollision(gameObject);
        }
    }

    private void intersectCollidedGameObjects() {
        for (Collision collision : collisions) {
            collision.intersectGameObjects();
        }
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
}