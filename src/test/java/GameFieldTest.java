import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameFieldTest {

    @Test(expected = OutOfBoundaryException.class)
    public void shouldThrowOutBoundaryExceptionWhenObjectIsOutOfBoundary() throws OutOfBoundaryException, ObjectIntersectionException {
        GameField gameField = new GameField(new Point(10, 10));

        gameField.addObject(new GameObject(new Point(9, 9), new Point(2, 2)));
    }

    @Test(expected = ObjectIntersectionException.class)
    public void shouldThrowObjectIntersectionExceptionWhenObjectIntersectsOthers() throws OutOfBoundaryException, ObjectIntersectionException {
        GameField gameField = new GameField(new Point(10, 10));
        gameField.addObject(new GameObject(new Point(4, 4), new Point(3, 5)));

        gameField.addObject(new GameObject(new Point(4, 6), new Point(2, 3)));
    }

    @Test
    public void shouldMoveObjectWhenIsUpdated() throws OutOfBoundaryException, ObjectIntersectionException {
        GameField gameField = new GameField(new Point(10, 10));
        GameObject gameObject = new GameObject(new Point(1, 1), new Point(1, 1));
        gameObject.setSpeed(new Point(1, 1));
        gameField.addObject(gameObject);
        Point expected = new Point(2, 2);

        gameField.update(1);

        assertEquals(expected, gameObject.getPos());
    }
}
