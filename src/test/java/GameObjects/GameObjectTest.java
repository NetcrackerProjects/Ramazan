package GameObjects;

import Geometry.Point;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GameObjectTest {

    @Test
    public void shouldMoveObjectCorrectlyWhenMoved() {
        GameObject gameObject = new GameObject(new Point(0, 0), new Point(1, 1));
        gameObject.setSpeed(new Point(1, 1));
        Point expected = new Point(1, 1);

        gameObject.move();

        assertEquals(expected, gameObject.getPos());
    }
}
