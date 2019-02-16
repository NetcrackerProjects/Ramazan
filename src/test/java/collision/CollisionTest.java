package collision;

import object.GameObject;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CollisionTest {

    @Test
    public void shouldCallIntersectGameObjectForBothObjectsWhenCarryOut() {
        GameObject firstGameObject = Mockito.mock(GameObject.class);
        GameObject secondGameObject = Mockito.mock(GameObject.class);
        Collision collision = new Collision(firstGameObject, secondGameObject);

        collision.carryOut();

        verify(firstGameObject, times(1)).intersectGameObject();
        verify(secondGameObject, times(1)).intersectGameObject();
    }
}
