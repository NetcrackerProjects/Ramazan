package game.rule;

import engine.action.Action;
import engine.geometry.Vector;
import engine.interaction.Interaction;
import engine.object.GameField;
import engine.object.TokenManager;
import engine.object.manager.ObjectManager;
import engine.physic.PhysicManager;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.tank.Tank;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

public class TankBulletInteractionRuleTest {

    @Test
    public void shouldReturnTwoActionWhenGivenTankBulletInteraction() {
        TokenManager tokenManager = new TokenManager();
        PhysicManager physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10), tokenManager.nextId()));
        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        GameObjectFactory gameObjectFactory = new GameObjectFactory(tokenManager);
        Tank tank = gameObjectFactory.createTank(new Vector(1, 1), new Vector(2, 2));
        Bullet bullet = gameObjectFactory.createBullet(new Vector(3, 3), new Vector(4, 4));
        tankObjectManager.addObject(tank);
        bulletObjectManager.addObject(bullet);
        Interaction interaction = new Interaction(tank, bullet);
        TankBulletInteractionRule rule = new TankBulletInteractionRule(tankObjectManager, bulletObjectManager);

        Collection<Action> actions = rule.getActions(interaction);

        assertEquals(2, actions.size());
    }
}
