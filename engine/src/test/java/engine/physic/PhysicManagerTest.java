package engine.physic;

import engine.exception.AddObjectException;
import engine.geometry.Vector;
import engine.interaction.Interaction;
import engine.object.GameField;
import engine.object.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PhysicManagerTest {

    private PhysicManager physicManager;

    @Before
    public void setup() {
        this.physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10), 0));
    }

    @Test(expected = AddObjectException.class)
    public void shouldThrowWhenInsertedObjectOutOfBoundary() throws AddObjectException {
        GameObject gameObject = new GameObject(new Vector(9, 9), new Vector(11, 11), 0, 0);

        physicManager.addPhysicObject(gameObject);
    }

    @Test(expected = AddObjectException.class)
    public void shouldThrowWhenInsertedSolidObjectIntersectsOthersSolidObjects() throws AddObjectException {
        GameObject gameObject = new GameObject(new Vector(1, 1), new Vector(3, 3), 0, 0);
        gameObject.setSolid(true);
        physicManager.addPhysicObject(gameObject);
        GameObject otherObject = new GameObject(new Vector(2, 2), new Vector(4, 4), 0, 0);
        otherObject.setSolid(true);

        physicManager.addPhysicObject(otherObject);
    }

    @Test
    public void shouldNotThrowWhenInsertedObjectIsNotSolid() {
        GameObject gameObject = new GameObject(new Vector(1, 1), new Vector(2, 2), 0, 0);
        gameObject.setSolid(false);

        try {
            physicManager.addPhysicObject(gameObject);

        } catch (AddObjectException e) {
            fail();
        }
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

    @Test
    public void shouldMoveWhenIntersectAndNotSolid() throws AddObjectException {
        GameObject gameObject = new GameObject(new Vector(1, 1), new Vector(2, 2), 0, 0);
        gameObject.setSpeed(new Vector(0.5, 0.5));
        gameObject.setSolid(false);
        physicManager.addPhysicObject(gameObject);
        physicManager.addPhysicObject(new GameObject(new Vector(2.1, 2.1), new Vector(3, 3), 1, 0));
        Vector expected = new Vector(1.5, 1.5);

        physicManager.move();

        assertEquals(expected, gameObject.getPosition());
    }
}