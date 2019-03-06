package action;

import object.GameObject;
import object.manager.GameObjectManager;

public class DeleteAction implements Action {

    private final GameObject gameObject;
    private final GameObjectManager gameObjectManager;

    public DeleteAction(GameObject gameObject, GameObjectManager gameObjectManager) {
        this.gameObject = gameObject;
        this.gameObjectManager = gameObjectManager;
    }

    @Override
    public void doAction() {
        gameObjectManager.removeObject(gameObject);
    }
}
