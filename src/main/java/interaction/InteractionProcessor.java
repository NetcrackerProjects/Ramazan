package interaction;

import action.Action;
import interaction.rule.BonusTankInteractionRule;
import interaction.rule.InteractionRule;
import interaction.rule.NoInteractionRule;
import interaction.rule.TankBulletInteractionRule;
import object.GameObjectManager;
import object.GameObject;
import object.Type;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InteractionProcessor {

    private final Map<InteractionType, InteractionRule> interactionRuleMap;

    public InteractionProcessor(GameObjectManager gameObjectManager) {
        this.interactionRuleMap = new HashMap<>();
        initializeInteractionsRules(gameObjectManager);
    }

    public Collection<Action> processInteractions(Collection<GameObjectInteraction> interactions) {
        Collection<Action> actions = new HashSet<>();

        for (GameObjectInteraction interaction : interactions) {
            actions.addAll(getActions(interaction));
        }

        return actions;
    }

    private void initializeInteractionsRules(GameObjectManager gameObjectManager) {
        interactionRuleMap.put(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(gameObjectManager));

        interactionRuleMap.put(new InteractionType(Type.TANK, Type.BONUS),
                new BonusTankInteractionRule(gameObjectManager));
    }

    private Collection<Action> getActions(GameObjectInteraction interaction) {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        InteractionRule interactionRule = getInteractionRule(first.getTypeId(), second.getTypeId());
        return interactionRule.getActions(interaction);
    }

    private InteractionRule getInteractionRule(int firstTypeId, int secondTypeId) {
        return interactionRuleMap.getOrDefault(
                new InteractionType(firstTypeId, secondTypeId), NoInteractionRule.instance);
    }
}
