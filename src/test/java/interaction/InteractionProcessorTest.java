package interaction;

import action.Action;
import exception.GameObjectFactoryException;
import geometry.Vector;
import object.GameObject;
import object.GameObjectFactory;
import object.GameObjectManager;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;

public class InteractionProcessorTest {

    @Test
    public void shouldWhen() throws GameObjectFactoryException {
        GameObjectManager gameObjectManager = new GameObjectManager();
        GameObject tank = GameObjectFactory.create("tank", new Vector(1, 1), new Vector(2, 2));
        GameObject bullet = GameObjectFactory.create("bullet", new Vector(3, 3), new Vector(4, 4));
        gameObjectManager.addObject(tank);
        gameObjectManager.addObject(bullet);
        InteractionProcessor interactionProcessor = new InteractionProcessor(gameObjectManager);
        Collection<GameObjectInteraction> interactions = new HashSet<>();
        interactions.add(new GameObjectInteraction(tank, bullet));

        Collection<Action> actions = interactionProcessor.processInteractions(interactions);

        assertEquals(2, actions.size());
    }
}
