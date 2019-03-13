package interaction;

import action.Action;
import geometry.Vector;
import interaction.rule.TankBulletInteractionRule;
import object.Bullet;
import object.GameObjectFactory;
import object.Tank;
import object.Type;
import object.manager.ObjectManager;
import org.junit.Test;
import physic.PhysicManager;

import java.util.Collection;
import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class InteractionProcessorTest {

    @Test
    public void shouldReturnTwoActionsWhenProcessTankBulletInteraction() {
        PhysicManager physicManager = new PhysicManager(GameObjectFactory.createGameField(new Vector(0, 0),
                new Vector(10, 10)));
        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        Tank tank = GameObjectFactory.createTank(new Vector(1, 1), new Vector(2, 2));
        Bullet bullet = GameObjectFactory.createBullet(new Vector(3, 3), new Vector(4, 4));
        tankObjectManager.addObject(tank);
        bulletObjectManager.addObject(bullet);
        InteractionRuleBase interactionRuleBase = new InteractionRuleBase();
        interactionRuleBase.addRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(tankObjectManager, bulletObjectManager));
        InteractionProcessor interactionProcessor = new InteractionProcessor(interactionRuleBase);
        Collection<Interaction> interactions = new HashSet<>();
        interactions.add(new Interaction(tank, bullet));

        Collection<Action> actions = interactionProcessor.processInteractions(interactions);

        assertEquals(2, actions.size());
    }
}
