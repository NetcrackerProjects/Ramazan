package game.rule;

import engine.action.Action;
import engine.exception.WrongObjectIdException;
import engine.interaction.Interaction;
import engine.interaction.rule.InteractionRule;
import engine.object.GameObject;
import engine.object.manager.ObjectManager;
import game.action.DamageAction;
import game.action.DeleteAction;
import game.object.Bullet;
import game.object.Type;
import game.object.tank.Tank;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class TankBulletInteractionRule implements InteractionRule<Tank, Bullet> {

    private final ObjectManager<Tank> tankObjectManager;
    private final ObjectManager<Bullet> bulletObjectManager;

    public TankBulletInteractionRule(ObjectManager<Tank> tankObjectManager,
                                     ObjectManager<Bullet> bulletObjectManager) {
        this.tankObjectManager = tankObjectManager;
        this.bulletObjectManager = bulletObjectManager;
    }

    @Override
    public Collection<Action> getActions(Interaction interaction) {
        try {
            Tank tank = getFirstObject(interaction);
            Bullet bullet = getSecondObject(interaction);

            Collection<Action> actions = new HashSet<>();
            actions.add(new DamageAction(tank, bullet.getDamage()));
            actions.add(new DeleteAction<>(bullet, bulletObjectManager));

            return actions;
        } catch (WrongObjectIdException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    private Tank getFirstObject(Interaction interaction) throws WrongObjectIdException {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        if (first.getTypeId() == Type.TANK) {
            return tankObjectManager.getObjectById(first.getId());
        } else {
            return tankObjectManager.getObjectById(second.getId());
        }
    }

    private Bullet getSecondObject(Interaction interaction) throws WrongObjectIdException {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        if (first.getTypeId() == Type.BULLET) {
            return bulletObjectManager.getObjectById(first.getId());
        } else {
            return bulletObjectManager.getObjectById(second.getId());
        }
    }
}
