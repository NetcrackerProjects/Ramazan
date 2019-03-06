package interaction;

import action.Action;
import geometry.Vector;
import interaction.rule.TankBulletInteractionRule;
import object.Bullet;
import object.GameObjectFactory;
import object.Tank;
import object.Type;
import object.manager.GameObjectManager;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class InteractionProcessorTest {

    @Test
    public void shouldWhen() {
        GameObjectManager gameObjectManager = new GameObjectManager();
        Tank tank = GameObjectFactory.createTank(new Vector(1, 1), new Vector(2, 2));
        Bullet bullet = GameObjectFactory.createBullet(new Vector(3, 3), new Vector(4, 4));
        gameObjectManager.addTank(tank);
        gameObjectManager.addBullet(bullet);
        InteractionRuleBase interactionRuleBase = new InteractionRuleBase();
        interactionRuleBase.addRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(gameObjectManager));
        InteractionProcessor interactionProcessor = new InteractionProcessor(interactionRuleBase);
        Collection<GameObjectInteraction> interactions = new HashSet<>();
        interactions.add(new GameObjectInteraction(tank, bullet));

        Collection<Action> actions = interactionProcessor.processInteractions(interactions);

        assertEquals(2, actions.size());
    }
}
