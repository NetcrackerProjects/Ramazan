package engine.interaction.rule;

import engine.action.Action;
import engine.interaction.Interaction;

import java.util.Collection;
import java.util.Collections;

public class NoInteractionRule implements InteractionRule {

    public static final NoInteractionRule instance = new NoInteractionRule();

    @Override
    public Collection<Action> getActions(Interaction interaction) {
        return Collections.emptySet();
    }
}