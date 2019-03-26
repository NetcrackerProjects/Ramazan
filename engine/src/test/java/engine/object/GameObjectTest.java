package engine.object;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameObjectTest {

    private GameObject gameObject;

    @Before
    public void setup() {
        this.gameObject = new GameObject(new Vector(0, 0), new Vector(1, 1), true, 0, 0);
        gameObject.setSpeed(new Vector(1, 1));
    }

    @Test
    public void shouldMoveObjectCorrectlyWhenMoved() {
        Vector expected = new Vector(1, 1);

        gameObject.move();

        assertEquals(expected, gameObject.getPosition());
    }

    @Test
    public void shouldReturnMovedBodyWhenCalled() {
        Rectangle movedBody = gameObject.getMovedBody();
        Rectangle expected = new Rectangle(new Vector(1, 1), new Vector(2, 2));

        assertEquals(expected, movedBody);
    }
}
