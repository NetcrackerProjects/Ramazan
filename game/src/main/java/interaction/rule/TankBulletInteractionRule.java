package interaction.rule;

import action.Action;
import action.DamageAction;
import action.DeleteAction;
import exception.WrongObjectIdException;
import interaction.Interaction;
import object.GameObject;
import object.Tank;
import object.Bullet;
import object.Type;
import object.manager.ObjectManager;

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

    @Override
    public Tank getFirstObject(Interaction interaction) throws WrongObjectIdException {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        if (first.getTypeId() == Type.TANK) {
            return tankObjectManager.getObjectById(first.getId());
        } else {
            return tankObjectManager.getObjectById(second.getId());
        }
    }

    @Override
    public Bullet getSecondObject(Interaction interaction) throws WrongObjectIdException {
        GameObject first = interaction.getFirst();
        GameObject second = interaction.getSecond();
        if (first.getTypeId() == Type.BULLET) {
            return bulletObjectManager.getObjectById(first.getId());
        } else {
            return bulletObjectManager.getObjectById(second.getId());
        }
    }
}
