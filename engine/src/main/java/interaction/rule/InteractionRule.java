package interaction.rule;

import action.Action;
import interaction.Interaction;
import object.GameObject;

import java.util.Collection;

public interface InteractionRule<T extends GameObject, V extends GameObject> {

    Collection<Action> getActions(Interaction interaction);
}
