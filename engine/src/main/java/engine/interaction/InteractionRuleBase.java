package engine.interaction;

import engine.interaction.rule.InteractionRule;
import engine.interaction.rule.NoInteractionRule;

import java.util.HashMap;
import java.util.Map;

public class InteractionRuleBase {

    private final Map<InteractionType, InteractionRule> interactionRules;

    public InteractionRuleBase() {
        this.interactionRules = new HashMap<>();
    }

    public void addRule(InteractionType interactionType, InteractionRule interactionRule) {
        interactionRules.put(interactionType, interactionRule);
    }

    InteractionRule getRule(InteractionType interactionType) {
        return interactionRules.getOrDefault(interactionType, NoInteractionRule.instance);
    }
}
