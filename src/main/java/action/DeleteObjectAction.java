package action;

import object.GameObject;
import object.GameObjectManager;

public class DeleteObjectAction implements Action {

    private final GameObject gameObject;
    private final GameObjectManager gameObjectManager;

    public DeleteObjectAction(GameObject gameObject, GameObjectManager gameObjectManager) {
        this.gameObject = gameObject;
        this.gameObjectManager = gameObjectManager;
    }

    @Override
    public void doAction() {
        gameObjectManager.removeObject(gameObject);
    }
}
