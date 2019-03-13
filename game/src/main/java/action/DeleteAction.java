package action;

import object.GameObject;
import object.manager.ObjectManager;

public class DeleteAction<T extends GameObject> implements Action {

    private final T gameObject;
    private final ObjectManager<T> objectManager;

    public DeleteAction(T gameObject, ObjectManager<T> objectManager) {
        this.gameObject = gameObject;
        this.objectManager = objectManager;
    }

    @Override
    public void doAction() {
        objectManager.removeObject(gameObject);
    }
}
