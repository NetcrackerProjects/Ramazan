package rule;

import action.Action;
import action.BonusAction;
import action.DeleteAction;
import exception.WrongObjectIdException;
import interaction.Interaction;
import interaction.rule.InteractionRule;
import object.Bonus;
import object.GameObject;
import object.Tank;
import object.Type;
import object.manager.ObjectManager;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class BonusTankInteractionRule implements InteractionRule<Bonus, Tank> {

    private final ObjectManager<Bonus> bonusObjectManager;
    private final ObjectManager<Tank> tankObjectManager;

    public BonusTankInteractionRule(ObjectManager<Bonus> bonusObjectManager,
                                    ObjectManager<Tank> tankObjectManager) {
        this.bonusObjectManager = bonusObjectManager;
        this.tankObjectManager = tankObjectManager;
    }

    @Override
    public Collection<Action> getActions(Interaction interaction) {
        try {
            Bonus bonus = getFirstObject(interaction);
            Tank tank = getSecondObject(interaction);

            Collection<Action> actions = new HashSet<>();
            actions.add(new BonusAction(tank, bonus));
            actions.add(new DeleteAction<>(bonus, bonusObjectManager));

            return actions;
        } catch (WrongObjectIdException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    private Bonus getFirstObject(Interaction interaction) throws WrongObjectIdException {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        if (first.getTypeId() == Type.BONUS) {
            return bonusObjectManager.getObjectById(first.getId());
        } else {
            return bonusObjectManager.getObjectById(second.getId());
        }
    }

    private Tank getSecondObject(Interaction interaction) throws WrongObjectIdException {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        if (first.getTypeId() == Type.TANK) {
            return tankObjectManager.getObjectById(first.getId());
        } else {
            return tankObjectManager.getObjectById(second.getId());
        }
    }
}
