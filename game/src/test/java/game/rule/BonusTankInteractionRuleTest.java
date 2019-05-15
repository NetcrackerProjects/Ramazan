package game.rule;

import engine.action.Action;
import engine.geometry.Vector;
import engine.interaction.Interaction;
import engine.object.GameField;
import engine.object.TokenManager;
import engine.object.manager.ObjectManager;
import engine.physic.PhysicManager;
import game.object.Bonus;
import game.object.GameObjectFactory;
import game.object.tank.Tank;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

public class BonusTankInteractionRuleTest {

    @Test
    public void shouldReturnTwoActionWhenGivenTankBulletInteraction() {
        TokenManager tokenManager = new TokenManager();
        PhysicManager physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(100, 100), tokenManager.nextId()));
        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bonus> bonusObjectManager = new ObjectManager<>(physicManager);
        GameObjectFactory gameObjectFactory = new GameObjectFactory(tokenManager);
        Tank tank = gameObjectFactory.createTank(new Vector(1, 1));
        Bonus bonus = gameObjectFactory.createBonus(new Vector(53, 53));
        tankObjectManager.addObject(tank);
        bonusObjectManager.addObject(bonus);
        Interaction interaction = new Interaction(tank, bonus);
        BonusTankInteractionRule rule = new BonusTankInteractionRule(bonusObjectManager, tankObjectManager);

        Collection<Action> actions = rule.getActions(interaction);

        assertEquals(2, actions.size());
    }
}
