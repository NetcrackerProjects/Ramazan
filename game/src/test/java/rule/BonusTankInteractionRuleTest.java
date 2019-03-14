package rule;

import action.Action;
import geometry.Vector;
import interaction.Interaction;
import object.Bonus;
import object.GameField;
import object.GameObjectFactory;
import object.Tank;
import object.manager.ObjectManager;
import org.junit.Test;
import physic.PhysicManager;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

public class BonusTankInteractionRuleTest {

    @Test
    public void shouldReturnTwoActionWhenGivenTankBulletInteraction() {
        PhysicManager physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10)));
        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bonus> bonusObjectManager = new ObjectManager<>(physicManager);
        Tank tank = GameObjectFactory.createTank(new Vector(1, 1), new Vector(2, 2));
        Bonus bonus = GameObjectFactory.createBonus(new Vector(3, 3), new Vector(4, 4));
        tankObjectManager.addObject(tank);
        bonusObjectManager.addObject(bonus);
        Interaction interaction = new Interaction(tank, bonus);
        BonusTankInteractionRule rule = new BonusTankInteractionRule(bonusObjectManager, tankObjectManager);

        Collection<Action> actions = rule.getActions(interaction);

        assertEquals(2, actions.size());
    }
}
