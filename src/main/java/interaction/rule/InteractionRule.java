package interaction.rule;

import action.Action;
import interaction.GameObjectInteraction;

import java.util.Collection;

public interface InteractionRule {

    Collection<Action> getActions(GameObjectInteraction interaction);
}
