package interaction;

import action.Action;
import interaction.rule.BonusTankInteractionRule;
import interaction.rule.InteractionRule;
import interaction.rule.NoInteractionRule;
import interaction.rule.TankBulletInteractionRule;
import object.BonusHolder;
import object.Bullet;
import object.GameObjectManager;
import object.Tank;
import object.GameObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InteractionProcessor {

    private final Map<InteractionType, InteractionRule> interactionRuleMap;

    public InteractionProcessor(GameObjectManager gameObjectManager) {
        interactionRuleMap = new HashMap<>();
        initializeInteractionsRules(gameObjectManager);
    }

    public Collection<Action> processInteractions(Collection<Interaction> interactions) {
        Collection<Action> actions = new HashSet<>();
        for (Interaction interaction : interactions) {
            actions.addAll(getActions(interaction));
        }
        return actions;
    }

    private void initializeInteractionsRules(GameObjectManager gameObjectManager) {
        interactionRuleMap.put(new InteractionType(Tank.TYPE_ID, Bullet.TYPE_ID),
                new TankBulletInteractionRule(gameObjectManager));

        interactionRuleMap.put(new InteractionType(Tank.TYPE_ID, BonusHolder.TYPE_ID),
                new BonusTankInteractionRule(gameObjectManager));
    }

    private Collection<Action> getActions(Interaction interaction) {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        InteractionRule interactionRule = getInteractionRule(first.getTypeId(), second.getTypeId());
        return interactionRule.getActions(first, second);
    }

    private InteractionRule getInteractionRule(int firstTypeId, int secondTypeId) {
        return interactionRuleMap.getOrDefault(
                new InteractionType(firstTypeId, secondTypeId), NoInteractionRule.instance);
    }
}
