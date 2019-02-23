package collision;

import geometry.Vector;
import geometry.Rectangle;
import object.GameObject;
import object.GameObjectManager;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


public class CollisionManagerTest {

    @Test
    public void shouldCallIntersectGameObjectsWhenProcessCollisions() {
        GameObject gameObject = new GameObject(new Vector(0, 0), new Vector(2, 2));
        GameObject mockedGameObject = Mockito.mock(GameObject.class);
        when(mockedGameObject.getBody()).thenReturn(new Rectangle(new Vector(1, 1), new Vector(3, 3)));
        GameObjectManager gameObjectManager = new GameObjectManager();
        gameObjectManager.addObject(gameObject);
        gameObjectManager.addObject(mockedGameObject);
        CollisionManager collisionManager = new CollisionManager(gameObjectManager);

        collisionManager.processCollisions();

        verify(mockedGameObject, times(1)).intersectGameObject();
    }
}
