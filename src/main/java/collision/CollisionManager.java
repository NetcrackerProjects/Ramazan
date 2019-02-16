package collision;

import object.GameObject;
import object.GameObjectManager;

import java.util.Collection;
import java.util.HashSet;

public class CollisionManager {

    private Collection<Collision> collisions;
    private GameObjectManager gameObjectManager;

    public CollisionManager(GameObjectManager gameObjectManager) {
        this.collisions = new HashSet<>();
        this.gameObjectManager = gameObjectManager;
    }

    public void processCollisions() {
        registerCollisions();
        intersectCollidedGameObjects();
        clearRegisteredCollisions();
    }

    private void registerCollisions() {
        collisions.clear();
        for (GameObject gameObject : gameObjectManager.getGameObjects()) {
            registerCollision(gameObject);
        }
    }

    private void intersectCollidedGameObjects() {
        for (Collision collision : collisions) {
            collision.intersectGameObjects();
        }
    }

    private void clearRegisteredCollisions() {
        collisions.clear();
    }

    private void registerCollision(GameObject gameObject) {
        for (GameObject otherGameObject : gameObjectManager.getGameObjects()) {
            if (otherGameObject.equals(gameObject)) {
                continue;
            }

            if (otherGameObject.doesIntersect(gameObject.getBody())) {
                collisions.add(new Collision(gameObject, otherGameObject));
            }
        }
    }


}
