package event;

import object.GameObject;
import object.GameObjectManager;

import java.util.Collection;
import java.util.HashSet;

class EventDetector {

    private final Collection<GameObject> gameObjects;

    EventDetector(GameObjectManager gameObjectManager) {
        this.gameObjects = gameObjectManager.getGameObjects();
    }

    Collection<Event> detectEvents() {
        Collection<Event> events = new HashSet<>();
        for (GameObject gameObject : gameObjects) {
            detectEventsForGameObject(events, gameObject);
        }
        return events;
    }

    private void detectEventsForGameObject(Collection<Event> events, GameObject gameObject) {
        if (gameObject.isDamageable()) {
            detectEventsForDamageable(events, gameObject);
        }

        if (gameObject.isBonus()) {
            detectEventsForBonusHolder(events, gameObject);
        }
    }

    private void detectEventsForDamageable(Collection<Event> events, GameObject damageable) {
        for (GameObject otherGameObject : gameObjects) {
            if (otherGameObject.isProjectile()) {
                if (otherGameObject.doesIntersect(damageable.getBody())) {
                    events.add(new DamageableInteractWithProjectileEvent(damageable, otherGameObject));
                }
            }
        }
    }

    private void detectEventsForBonusHolder(Collection<Event> events, GameObject bonusHolder) {
        for (GameObject otherGameObject : gameObjects) {
            if (otherGameObject.isBonusTolerable()) {
                if (otherGameObject.doesIntersect(bonusHolder.getBody())) {
                    events.add(new GameObjectInteractWithBonusEvent(otherGameObject, bonusHolder));
                }
            }
        }
    }


}
