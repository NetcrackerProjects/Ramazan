package interaction.rule;

import action.Action;
import action.DamageAction;
import action.DeleteAction;
import exception.InteractionRuleException;
import exception.WrongObjectIdException;
import interaction.GameObjectInteraction;
import object.GameObject;
import object.manager.BulletObjectManager;
import object.manager.GameObjectManager;
import object.Tank;
import object.Bullet;
import object.Type;
import object.manager.TankObjectManager;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class TankBulletInteractionRule implements InteractionRule {

    private final GameObjectManager gameObjectManager;
    private final TankObjectManager tankObjectManager;
    private final BulletObjectManager bulletObjectManager;

    public TankBulletInteractionRule(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
        this.tankObjectManager = gameObjectManager.getTankObjectManager();
        this.bulletObjectManager = gameObjectManager.getBulletObjectManager();
    }

    @Override
    public Collection<Action> getActions(GameObjectInteraction interaction) {
        try {
            GameObject firstObject = interaction.getFirst();
            GameObject secondObject = interaction.getSecond();

            Tank tank = getTank(firstObject, secondObject);
            Bullet bullet = getBullet(firstObject, secondObject);

            Collection<Action> actions = new HashSet<>();
            actions.add(new DamageAction(tank, bullet.getDamage()));
            actions.add(new DeleteAction(bullet, gameObjectManager));

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

    private Bullet getBullet(GameObject first, GameObject second) throws InteractionRuleException, WrongObjectIdException {
        if (first.getTypeId() == Type.BULLET) {
            return bulletObjectManager.getObjectById(first.getId());
        }

        if (second.getTypeId() == Type.BULLET) {
            return bulletObjectManager.getObjectById(second.getId());
        }

        throw new InteractionRuleException();
    }
}
