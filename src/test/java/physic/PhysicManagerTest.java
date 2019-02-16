package physic;

import geometry.Point;
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
        this.physicManager = new PhysicManager(new Rectangle(new Point(0, 0), new Point(10, 10)),
                gameObjectManager);
    }

    @Test
    public void shouldReturnFalseWhenInsertedObjectOutOfBoundary() {
        GameObject gameObject = new GameObject(new Point(9, 9), new Point(11, 11));

        boolean canAddObject = physicManager.canAddObject(gameObject);

        assertFalse(canAddObject);
    }

    @Test
    public void shouldReturnFalseWhenInsertedObjectIntersectsOthers() {
        gameObjectManager.addObject(new GameObject(new Point(1, 1), new Point(3, 3)));
        GameObject gameObject = new GameObject(new Point(2, 2), new Point(4, 4));

        boolean canAddObject = physicManager.canAddObject(gameObject);

        assertFalse(canAddObject);
    }

    @Test
    public void shouldMoveObjectWhenIsUpdated(){
        GameObject gameObject = new GameObject(new Point(1, 1), new Point(2, 2));
        gameObject.setSpeed(new Point(1, 1));
        gameObjectManager.addObject(gameObject);
        Point expected = new Point(2, 2);

        physicManager.move();

        assertEquals(expected, gameObject.getPos());
    }
}