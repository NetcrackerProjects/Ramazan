package rule;

import action.Action;
import geometry.Vector;
import interaction.Interaction;
import object.GameField;
import object.Tank;
import object.Bullet;
import object.GameObjectFactory;
import object.manager.ObjectManager;
import org.junit.Test;
import physic.PhysicManager;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

public class TankBulletInteractionRuleTest {

    @Test
    public void shouldReturnTwoActionWhenGivenTankBulletInteraction() {
        PhysicManager physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10)));
        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        Tank tank = GameObjectFactory.createTank(new Vector(1, 1), new Vector(2, 2));
        Bullet bullet = GameObjectFactory.createBullet(new Vector(3, 3), new Vector(4, 4));
        tankObjectManager.addObject(tank);
        bulletObjectManager.addObject(bullet);
        Interaction interaction = new Interaction(tank, bullet);
        TankBulletInteractionRule rule = new TankBulletInteractionRule(tankObjectManager, bulletObjectManager);

        Collection<Action> actions = rule.getActions(interaction);

        assertEquals(2, actions.size());
    }
}
