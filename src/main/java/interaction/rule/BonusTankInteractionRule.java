package interaction.rule;

import action.Action;
import action.BonusAction;
import action.DeleteAction;
import bonus.Bonus;
import exception.WrongObjectIdException;
import interaction.Interaction;
import object.BonusHolder;
import object.GameObject;
import object.Tank;
import object.Type;
import object.manager.ObjectManager;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class BonusTankInteractionRule implements InteractionRule<BonusHolder, Tank> {

    private final ObjectManager<BonusHolder> bonusObjectManager;
    private final ObjectManager<Tank> tankObjectManager;

    public BonusTankInteractionRule(ObjectManager<BonusHolder> bonusObjectManager,
                                    ObjectManager<Tank> tankObjectManager) {
        this.bonusObjectManager = bonusObjectManager;
        this.tankObjectManager = tankObjectManager;
    }

    @Override
    public Collection<Action> getActions(Interaction interaction) {
        try {
            BonusHolder bonusHolder = getFirstObject(interaction);
            Tank tank = getSecondObject(interaction);

            Bonus bonus = bonusHolder.getBonus();

            if (!bonus.canApplyBonus(tank)) {
                return Collections.emptySet();
            }

            Collection<Action> actions = new HashSet<>();
            actions.add(new BonusAction(tank, bonus));
            actions.add(new DeleteAction<>(bonusHolder, bonusObjectManager));

            return actions;
        } catch (WrongObjectIdException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public BonusHolder getFirstObject(Interaction interaction) throws WrongObjectIdException {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        if (first.getTypeId() == Type.BONUS) {
            return bonusObjectManager.getObjectById(first.getId());
        } else {
            return bonusObjectManager.getObjectById(second.getId());
        }
    }

    @Override
    public Tank getSecondObject(Interaction interaction) throws WrongObjectIdException {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        if (first.getTypeId() == Type.TANK) {
            return tankObjectManager.getObjectById(first.getId());
        } else {
            return tankObjectManager.getObjectById(second.getId());
        }
    }
}
