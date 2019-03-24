package game.player;

import engine.exception.WrongObjectIdException;
import org.junit.Test;

public class PlayerManagerTest {

    @Test(expected = WrongObjectIdException.class)
    public void shouldThrowWhenWrongId() throws WrongObjectIdException {
        PlayerManager playerManager = new PlayerManager();

        playerManager.getPlayer(-1);
    }
}
