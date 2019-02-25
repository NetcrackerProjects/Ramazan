package event;

import action.Action;
import action.ApplyBonusToObjectAction;
import action.DeleteObjectAction;
import object.GameObject;

import java.util.Collection;
import java.util.HashSet;

class GameObjectInteractWithBonusEvent implements Event{

    private final GameObject gameObject;
    private final GameObject bonusHolder;

    GameObjectInteractWithBonusEvent(GameObject gameObject, GameObject bonusHolder) {
        this.gameObject = gameObject;
        this.bonusHolder = bonusHolder;
    }

    @Override
    public Collection<Action> getActions() {
        Collection<Action> actions = new HashSet<>();
        actions.add(new ApplyBonusToObjectAction(gameObject, bonusHolder.getBonus()));
        actions.add(new DeleteObjectAction(bonusHolder));
        return actions;
    }
}
