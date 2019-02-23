package object;

import geometry.Vector;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameObjectTest {

    @Test
    public void shouldMoveObjectCorrectlyWhenMoved() {
        GameObject gameObject = new GameObject(new Vector(0, 0), new Vector(1, 1));
        gameObject.setSpeed(new Vector(1, 1));
        Vector expected = new Vector(1, 1);

        gameObject.move();

        assertEquals(expected, gameObject.getPosition());
    }
}
