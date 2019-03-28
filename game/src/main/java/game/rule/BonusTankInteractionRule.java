package game.rule;

import engine.action.Action;
import engine.exception.WrongObjectIdException;
import engine.interaction.Interaction;
import engine.interaction.rule.InteractionRule;
import engine.object.GameObject;
import engine.object.manager.ObjectManager;
import game.action.BonusAction;
import game.action.DeleteAction;
import game.object.Bonus;
import game.object.Type;
import game.object.tank.Tank;

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
