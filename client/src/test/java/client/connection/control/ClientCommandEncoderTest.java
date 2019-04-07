package client.connection.control;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientCommandEncoderTest {

    @Test
    public void shouldGetCorrectCodeWhenGetMessageForMoveRight() {
        ClientControlCommandType type = ClientControlCommandType.MOVE_RIGHT;

        String message = ClientCommandEncoder.getCommand(type);

        assertEquals(ClientCommandEncoder.MOVE_RIGHT_CODE, message);
    }

    @Test
    public void shouldGetCorrectCodeWhenGetMessageForMoveUp() {
        ClientControlCommandType type = ClientControlCommandType.MOVE_UP;

        String message = ClientCommandEncoder.getCommand(type);

        assertEquals(ClientCommandEncoder.MOVE_UP_CODE, message);
    }

    @Test
    public void shouldGetCorrectCodeWhenGetMessageForShoot() {
        ClientControlCommandType type = ClientControlCommandType.SHOOT;

        String message = ClientCommandEncoder.getCommand(type);

        assertEquals(ClientCommandEncoder.SHOOT_CODE, message);
    }

    @Test
    public void shouldGetCorrectCodeWhenGetMessageForExit() {
        ClientControlCommandType type = ClientControlCommandType.EXIT;

        String message = ClientCommandEncoder.getCommand(type);

        assertEquals(ClientCommandEncoder.EXIT_CODE, message);
    }
}
