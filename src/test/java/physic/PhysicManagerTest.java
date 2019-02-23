package physic;

import geometry.Vector;
import geometry.Rectangle;
import object.GameObject;
import object.GameObjectManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PhysicManagerTest {

    private GameObjectManager gameObjectManager;
    private PhysicManager physicManager;

    @Before
    public void setup() {
        this.gameObjectManager = new GameObjectManager();
        this.physicManager = new PhysicManager(new Rectangle(new Vector(0, 0), new Vector(10, 10)),
                gameObjectManager);
    }

    @Test
    public void shouldReturnFalseWhenInsertedObjectOutOfBoundary() {
        GameObject gameObject = new GameObject(new Vector(9, 9), new Vector(11, 11));

        boolean canAddObject = physicManager.canAddObject(gameObject);

        assertFalse(canAddObject);
    }

    @Test
    public void shouldReturnFalseWhenInsertedObjectIntersectsOthers() {
        gameObjectManager.addObject(new GameObject(new Vector(1, 1), new Vector(3, 3)));
        GameObject gameObject = new GameObject(new Vector(2, 2), new Vector(4, 4));

        boolean canAddObject = physicManager.canAddObject(gameObject);

        assertFalse(canAddObject);
    }

    @Test
    public void shouldMoveObjectWhenIsUpdated(){
        GameObject gameObject = new GameObject(new Vector(1, 1), new Vector(2, 2));
        gameObject.setSpeed(new Vector(1, 1));
        gameObjectManager.addObject(gameObject);
        Vector expected = new Vector(2, 2);

        physicManager.move();

        assertEquals(expected, gameObject.getPosition());
    }
}