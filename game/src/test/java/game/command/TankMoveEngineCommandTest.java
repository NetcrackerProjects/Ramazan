package game.command;

import engine.action.Action;
import engine.geometry.Direction;
import game.action.ChangeSpeedAction;
import game.object.tank.Tank;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class TankMoveEngineCommandTest {

    @Test
    public void shouldReturnChangeSpeedActionObjectWhenGetAction() {
        Tank tank = mock(Tank.class);
        Direction.Type type = Direction.Type.DOWN;
        TankMoveEngineCommand command = new TankMoveEngineCommand(tank, type);

        Action action = command.getAction();

        assertTrue(action instanceof ChangeSpeedAction);
    }
}
