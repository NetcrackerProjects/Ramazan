import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameFieldTest {

    @Test(expected = OutOfBoundaryException.class)
    public void shouldThrowWhenInsertedObjectIsOutOfBoundary() throws Exception {
        GameField gameField = new GameField(new Point(0,0), new Point(10, 10));

        gameField.addObject(new GameObject(new Point(9, 9), new Point(11, 11)));
    }

    @Test(expected = ObjectIntersectionException.class)
    public void shouldThrowWhenInsertedObjectIntersectsOthers() throws Exception {
        GameField gameField = new GameField(new Point(0, 0), new Point(10, 10));
        gameField.addObject(new GameObject(new Point(4, 4), new Point(7, 9)));

        gameField.addObject(new GameObject(new Point(4, 6), new Point(6, 9)));
    }

    @Test
    public void shouldMoveObjectWhenIsUpdated() throws Exception {
        GameField gameField = new GameField(new Point(0, 0), new Point(10, 10));
        GameObject gameObject = new GameObject(new Point(1, 1), new Point(2, 2));
        gameObject.setSpeed(new Point(1, 1));
        gameField.addObject(gameObject);
        Point expected = new Point(2, 2);

        gameField.update();

        assertEquals(expected, gameObject.getPos());
    }

    @Test
    public void shouldCallIntersectGameObjectWhenCollisionHappened() throws Exception {
        GameField gameField = new GameField(new Point(0, 0), new Point(10, 10));
        GameObject gameObject = new GameObject(new Point(1, 1), new Point(2, 2));
        gameObject.setPermeable();
        gameField.addObject(gameObject);
        Rectangle body = new Rectangle(new Point(0, 0), new Point(1.5, 1.5));
        GameObject mockedGameObject = Mockito.mock(GameObject.class);
        when(mockedGameObject.getBody()).thenReturn(body);
        when(mockedGameObject.getSpeed()).thenReturn(new Point(0, 0));
        gameField.addObject(mockedGameObject);

        gameField.update();

        verify(mockedGameObject, times(1)).intersectGameObject(any(GameObject.class));
    }
}
