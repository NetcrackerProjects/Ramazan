package game.action;

import engine.geometry.Vector;
import game.object.tank.Tank;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ChangeTankSpeedActionTest {

    private Tank tank;
    private Vector vector;
    private ChangeTankSpeedAction changeTankSpeedAction;

    @Before
    public void setup() {
        this.tank = mock(Tank.class);
        this.vector = mock(Vector.class);
        this.changeTankSpeedAction = new ChangeTankSpeedAction(tank, vector);
    }

    @Test
    public void shouldCallSetSpeedWhenDoAction() {
        changeTankSpeedAction.doAction();

        verify(tank, times(1)).setSpeed(vector);
    }

    @Test
    public void shouldCallUpdateWeaponDirectionWhenDoAction() {
        changeTankSpeedAction.doAction();

        verify(tank, times(1)).updateWeaponDirection();
    }
}
