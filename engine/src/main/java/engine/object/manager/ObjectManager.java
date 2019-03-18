package engine.object.manager;

import engine.exception.AddObjectException;
import engine.exception.WrongObjectIdException;
import engine.object.GameObject;
import engine.physic.PhysicManager;

import java.util.HashMap;
import java.util.Map;

public class ObjectManager<T extends GameObject> {

    private final PhysicManager physicManager;

    private final Map<Integer, T> gameObjectMap;

    public ObjectManager(PhysicManager physicManager) {
        this.gameObjectMap = new HashMap<>();
        this.physicManager = physicManager;
    }

    public T getObjectById(int id) throws WrongObjectIdException {
        T gameObject = gameObjectMap.get(id);

        if (gameObject == null) {
            throw new WrongObjectIdException();
        }

        return gameObject;
    }

    public void addObject(T object) {
        try {
            physicManager.addPhysicObject(object);
        } catch (AddObjectException e) {
            return;
        }

        gameObjectMap.put(object.getId(), object);
    }

    public void removeObject(GameObject gameObject) {
        physicManager.removePhysicalObject(gameObject);
        gameObjectMap.remove(gameObject.getId());
    }
}
