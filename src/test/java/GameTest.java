import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GameTest {

    @Test
    public void should_notRun_when_terminated() throws InterruptedException {
        Game game = new Game();
        game.start();

        game.terminate();

        assertFalse(game.isRunning());
    }
}
