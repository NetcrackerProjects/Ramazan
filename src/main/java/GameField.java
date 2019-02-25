import action.ActionManager;
import event.EventManager;
import exception.ObjectAddException;
import object.GameObject;
import geometry.Vector;
import geometry.Rectangle;
import object.GameObjectManager;
import physic.PhysicManager;

class GameField {

    private final EventManager eventManager;
    private final GameObjectManager gameObjectManager;
    private final PhysicManager physicManager;
    private final ActionManager actionManager;

    GameField(Vector leftTop, Vector rightBottom) {
        this.gameObjectManager = new GameObjectManager();
        this.actionManager = new ActionManager();
        this.eventManager = new EventManager(gameObjectManager);
        this.physicManager = new PhysicManager(new Rectangle(leftTop, rightBottom), gameObjectManager);
    }

    void addObject(GameObject gameObject) throws ObjectAddException {
        if (!physicManager.canAddObject(gameObject)) {
            throw new ObjectAddException();
        }

        gameObjectManager.addObject(gameObject);
    }

    void update() {
        physicManager.move();
        actionManager.processActions(eventManager.getActions());
        gameObjectManager.removeDeletedObjects();
    }
}
