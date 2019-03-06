package interaction.rule;

import action.Action;
import action.DamageAction;
import action.DeleteAction;
import exception.InteractionRuleException;
import interaction.GameObjectInteraction;
import object.GameObject;
import object.GameObjectManager;
import object.Tank;
import object.Bullet;
import object.Type;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class TankBulletInteractionRule implements InteractionRule {

    private final GameObjectManager gameObjectManager;

    public TankBulletInteractionRule(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
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
        } catch (InteractionRuleException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    private Tank getTank(GameObject first, GameObject second) throws InteractionRuleException {
        if (first.getTypeId() == Type.TANK) {
            return (Tank) first;
        }

        if (second.getTypeId() == Type.TANK) {
            return (Tank) second;
        }

        throw new InteractionRuleException();
    }

    private Bullet getBullet(GameObject first, GameObject second) throws InteractionRuleException {
        if (first.getTypeId() == Type.BULLET) {
            return (Bullet) first;
        }

        if (second.getTypeId() == Type.BULLET) {
            return (Bullet) second;
        }

        throw new InteractionRuleException();
    }
}
