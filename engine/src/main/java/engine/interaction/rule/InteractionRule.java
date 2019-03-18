package engine.interaction.rule;

import engine.action.Action;
import engine.interaction.Interaction;
import engine.object.GameObject;

import java.util.Collection;

public interface InteractionRule<T extends GameObject, V extends GameObject> {

    Collection<Action> getActions(Interaction interaction);
}
