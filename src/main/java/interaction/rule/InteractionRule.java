package interaction.rule;

import action.Action;
import object.GameObject;

import java.util.Collection;

public interface InteractionRule {

    Collection<Action> getActions(GameObject firstObject, GameObject secondObject);
}
