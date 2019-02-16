package collision;

import geometry.Point;
import geometry.Rectangle;
import object.GameObject;
import object.GameObjectManager;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class CollisionManagerTest {

    @Test
    public void shouldCallIntersectGameObjectsWhenProcessCollisions() {
        GameObject gameObject = new GameObject(new Point(0, 0), new Point(2, 2));
        GameObject mockedGameObject = Mockito.mock(GameObject.class);
        when(mockedGameObject.getBody()).thenReturn(new Rectangle(new Point(1, 1), new Point(3, 3)));
        GameObjectManager gameObjectManager = new GameObjectManager();
        gameObjectManager.addObject(gameObject);
        gameObjectManager.addObject(mockedGameObject);
        CollisionManager collisionManager = new CollisionManager(gameObjectManager);

        collisionManager.processCollisions();

        verify(mockedGameObject, times(1)).intersectGameObject();
    }
}
