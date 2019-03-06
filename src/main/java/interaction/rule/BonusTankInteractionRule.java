package interaction.rule;

import action.Action;
import action.BonusAction;
import action.DeleteAction;
import bonus.Bonus;
import exception.InteractionRuleException;
import exception.WrongObjectIdException;
import interaction.GameObjectInteraction;
import object.BonusHolder;
import object.GameObject;
import object.manager.BonusObjectManager;
import object.manager.GameObjectManager;
import object.Tank;
import object.Type;
import object.manager.TankObjectManager;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class BonusTankInteractionRule implements InteractionRule {

    private final BonusObjectManager bonusObjectManager;
    private final TankObjectManager tankObjectManager;
    private final GameObjectManager gameObjectManager;

    public BonusTankInteractionRule(GameObjectManager gameObjectManager) {
        this.bonusObjectManager = gameObjectManager.getBonusObjectManager();
        this.tankObjectManager = gameObjectManager.getTankObjectManager();
        this.gameObjectManager = gameObjectManager;
    }

    @Override
    public Collection<Action> getActions(GameObjectInteraction interaction) {
        try {
            GameObject firstObject = interaction.getFirst();
            GameObject secondObject = interaction.getSecond();

            Tank tank = getTank(firstObject, secondObject);
            BonusHolder bonusHolder = getBonusHolder(firstObject, secondObject);

            Bonus bonus = bonusHolder.getBonus();

            if (!bonus.canApplyBonus(tank)) {
                return Collections.emptySet();
            }

            Collection<Action> actions = new HashSet<>();
            actions.add(new BonusAction(tank, bonus));
            actions.add(new DeleteAction(bonusHolder, gameObjectManager));

            return actions;
        } catch (InteractionRuleException | WrongObjectIdException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }


    private Tank getTank(GameObject first, GameObject second) throws InteractionRuleException, WrongObjectIdException {
        if (first.getTypeId() == Type.TANK) {
            return tankObjectManager.getObjectById(first.getId());
        }

        if (second.getTypeId() == Type.TANK) {
            return tankObjectManager.getObjectById(second.getId());
        }

        throw new InteractionRuleException();
    }

    private BonusHolder getBonusHolder(GameObject first, GameObject second) throws InteractionRuleException, WrongObjectIdException {
        if (first.getTypeId() == Type.BONUS) {
            return bonusObjectManager.getObjectById(first.getId());
        }

        if (second.getTypeId() == Type.BONUS) {
            return bonusObjectManager.getObjectById(second.getId());
        }

        throw new InteractionRuleException();
    }
}
