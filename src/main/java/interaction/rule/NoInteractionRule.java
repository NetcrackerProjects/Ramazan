package interaction.rule;

import action.Action;
import interaction.Interaction;
import object.GameObject;

import java.util.Collection;
import java.util.Collections;

public class NoInteractionRule implements InteractionRule {

    public static final NoInteractionRule instance = new NoInteractionRule();

    @Override
    public Collection<Action> getActions(Interaction interaction) {
        return Collections.emptySet();
    }

    @Override
    public GameObject getFirstObject(Interaction interaction) {
        return null;
    }

    @Override
    public GameObject getSecondObject(Interaction interaction) {
        return null;
    }
}