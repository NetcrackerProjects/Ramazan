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

    public Collection<Action> processInteractions(Collection<Interaction> interactions) {
        Collection<Action> actions = new HashSet<>();

        for (Interaction interaction : interactions) {
            actions.addAll(getActions(interaction));
        }

        return actions;
    }

    @SuppressWarnings("unchecked")
    private Collection<Action> getActions(Interaction interaction) {
        InteractionRule interactionRule = getInteractionRule(interaction.getInteractionType());
        return interactionRule.getActions(interaction);
    }

    private InteractionRule getInteractionRule(InteractionType interactionType) {
        return interactionRuleBase.getRule(interactionType);
    }
}
