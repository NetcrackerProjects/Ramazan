import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameFieldTest {

    @Test
    public void givenGameObjectOutOfBoundary_whenAddObject_thenCorrect(){
        GameField gameField = new GameField(new Point(10, 10));

        gameField.addObject(new GameObject(new Point(9, 9), new Point(2, 2)));

        Collection<GameObject> gameObjects = gameField.getGameObjects();

        assertTrue(gameObjects.size() == 0);
    }

    @Test
    public void givenIntersectingGameObject_whenAddObject_thenCorrect(){
        GameField gameField = new GameField(new Point(10, 10));

        gameField.addObject(new GameObject(new Point(4, 4), new Point(3, 5)));

        gameField.addObject(new GameObject(new Point(4, 6), new Point(2, 3)));

        Collection<GameObject> gameObjects = gameField.getGameObjects();

        assertTrue(gameObjects.size() == 1);
    }

    @Test
    public void givenGameField_whenUpdate_thenCorrect(){
        GameField gameField = new GameField(new Point(10, 10));
        GameObject gameObject = new GameObject(new Point(1, 1), new Point(1, 1));
        gameObject.setSpeed(new Point(1, 1));
        gameField.addObject(gameObject);

        gameField.update(1);

        Point expected = new Point(2, 2);

        assertEquals(expected, gameObject.getPos());
    }
}
