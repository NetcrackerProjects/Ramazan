package interaction.rule;

import action.Action;
import action.DamageObjectAction;
import action.DeleteObjectAction;
import exception.InteractionRuleException;
import object.Bullet;
import object.GameObject;
import object.GameObjectManager;
import object.Tank;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class TankBulletInteractionRule implements InteractionRule {

    private final GameObjectManager gameObjectManager;

    public TankBulletInteractionRule(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }

    @Override
    public Collection<Action> getActions(GameObject firstObject, GameObject secondObject) {
        try {
            Tank tank = getTank(firstObject, secondObject);
            Bullet bullet = getBullet(firstObject, secondObject);
            Collection<Action> actions = new HashSet<>();
            actions.add(new DamageObjectAction(tank, bullet.getDamage()));
            actions.add(new DeleteObjectAction(bullet, gameObjectManager));
            return actions;
        } catch (InteractionRuleException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    private Tank getTank(GameObject first, GameObject second) throws InteractionRuleException {
        if (first.getTypeId() == Tank.TYPE_ID) {
            return (Tank) first;
        }

        if (second.getTypeId() == Tank.TYPE_ID) {
            return (Tank) second;
        }

        throw new InteractionRuleException();
    }

    private Bullet getBullet(GameObject first, GameObject second) throws InteractionRuleException {
        if (first.getTypeId() == Bullet.TYPE_ID) {
            return (Bullet) first;
        }

        if (second.getTypeId() == Bullet.TYPE_ID) {
            return (Bullet) second;
        }

        throw new InteractionRuleException();
    }
}
