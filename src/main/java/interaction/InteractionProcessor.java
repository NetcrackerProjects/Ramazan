package interaction;

import action.Action;
import interaction.rule.InteractionRule;

import java.util.Collection;
import java.util.HashSet;

public class InteractionProcessor {

    private final InteractionRuleBase interactionRuleBase;

    public InteractionProcessor(InteractionRuleBase interactionRuleBase) {
        this.interactionRuleBase = interactionRuleBase;
    }

    public Collection<Action> processInteractions(Collection<GameObjectInteraction> interactions) {
        Collection<Action> actions = new HashSet<>();

        for (GameObjectInteraction interaction : interactions) {
            actions.addAll(getActions(interaction));
        }

        return actions;
    }

    private Collection<Action> getActions(GameObjectInteraction interaction) {
        InteractionRule interactionRule = getInteractionRule(interaction.getInteractionType());
        return interactionRule.getActions(interaction);
    }

    private InteractionRule getInteractionRule(InteractionType interactionType) {
        return interactionRuleBase.getRule(interactionType);
    }
}
