package game.action;

import engine.geometry.Vector;
import engine.object.GameObject;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ChangeSpeedActionTest {

    @Test
    public void shouldCallSetSpeedWhenDoAction() {
        GameObject gameObject = mock(GameObject.class);
        Vector vector = mock(Vector.class);
        ChangeSpeedAction changeSpeedAction = new ChangeSpeedAction(gameObject, vector);

        changeSpeedAction.doAction();

        verify(gameObject, times(1)).setSpeed(vector);
    }
}
