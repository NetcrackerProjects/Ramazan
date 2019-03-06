import action.Action;
import action.ActionManager;
import exception.ObjectAddException;
import geometry.Rectangle;
import geometry.Vector;
import interaction.GameObjectInteraction;
import interaction.InteractionProcessor;
import object.GameObject;
import object.GameObjectManager;
import physic.PhysicManager;

import java.util.Collection;

class GameField {

    private final InteractionProcessor interactionProcessor;
    private final GameObjectManager gameObjectManager;
    private final PhysicManager physicManager;
    private final ActionManager actionManager;

    GameField(Vector leftTop, Vector rightBottom) {
        this.gameObjectManager = new GameObjectManager();
        this.actionManager = new ActionManager();
        this.interactionProcessor = new InteractionProcessor(gameObjectManager);
        this.physicManager = new PhysicManager(new Rectangle(leftTop, rightBottom), gameObjectManager);
    }

    void addObject(GameObject gameObject) throws ObjectAddException {
        if (!physicManager.canAddObject(gameObject)) {
            throw new ObjectAddException();
        }

        gameObjectManager.addObject(gameObject);
    }

    void update() {
        Collection<GameObjectInteraction> interactions = physicManager.move();
        Collection<Action> actions = interactionProcessor.processInteractions(interactions);
        actionManager.processActions(actions);
    }
}
