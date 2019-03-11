package physic;

import exception.AddObjectException;
import geometry.Rectangle;
import geometry.Vector;
import interaction.Interaction;
import object.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class PhysicManagerTest {

    private PhysicManager physicManager;

    @Before
    public void setup() {
        this.physicManager = new PhysicManager(new Rectangle(new Vector(0, 0), new Vector(10, 10)));
    }

    @Test(expected = AddObjectException.class)
    public void shouldThrowWhenInsertedObjectOutOfBoundary() throws AddObjectException {
        GameObject gameObject = new GameObject(new Vector(9, 9), new Vector(11, 11), 0, 0);

        physicManager.addPhysicObject(gameObject);
    }

    @Test(expected = AddObjectException.class)
    public void shouldThrowWhenInsertedObjectIntersectsOthers() throws AddObjectException {
        physicManager.addPhysicObject(new GameObject(new Vector(1, 1), new Vector(3, 3), 0, 0));
        GameObject gameObject = new GameObject(new Vector(2, 2), new Vector(4, 4), 0, 0);

        physicManager.addPhysicObject(gameObject);
    }

    @Test
    public void shouldMoveObjectWhenIsUpdated() throws AddObjectException {
        GameObject gameObject = new GameObject(new Vector(1, 1), new Vector(2, 2), 0, 0);
        gameObject.setSpeed(new Vector(1, 1));
        physicManager.addPhysicObject(gameObject);
        Vector expected = new Vector(2, 2);

        physicManager.move();

        assertEquals(expected, gameObject.getPosition());
    }

    @Test
    public void shouldReturnSingleInteractionWhenObjectsInteract() throws AddObjectException {
        GameObject gameObject = new GameObject(new Vector(1, 1), new Vector(2, 2), 0, 0);
        gameObject.setSpeed(new Vector(0.5, 0.5));
        physicManager.addPhysicObject(gameObject);
        physicManager.addPhysicObject(new GameObject(new Vector(2.1, 2.1), new Vector(3, 3), 1, 0));

        Collection<Interaction> interactions = physicManager.move();

        assertEquals(1, interactions.size());
    }
}