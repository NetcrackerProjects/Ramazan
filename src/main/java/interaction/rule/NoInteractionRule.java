package interaction.rule;

import action.Action;
import object.GameObject;

import java.util.Collection;
import java.util.Collections;

public class NoInteractionRule implements InteractionRule {

    public static final NoInteractionRule instance = new NoInteractionRule();

    @Override
    public Collection<Action> getActions(GameObject firstObject, GameObject secondObject) {
        return Collections.emptySet();
    }
}